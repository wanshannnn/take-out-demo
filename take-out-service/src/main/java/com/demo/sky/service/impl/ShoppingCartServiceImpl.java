package com.demo.sky.service.impl;

import com.demo.sky.context.BaseContext;
import com.demo.sky.dao.Dish;
import com.demo.sky.dao.Setmeal;
import com.demo.sky.dao.ShoppingCart;
import com.demo.sky.dto.ShoppingCartDTO;
import com.demo.sky.mapper.DishMapper;
import com.demo.sky.mapper.SetmealMapper;
import com.demo.sky.mapper.ShoppingCartMapper;
import com.demo.sky.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final String CART_PREFIX = "cart:";

    private final ShoppingCartMapper shoppingCartMapper;
    private final DishMapper dishMapper;
    private final SetmealMapper setmealMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper,
                                   DishMapper dishMapper,
                                   SetmealMapper setmealMapper,
                                   RedisTemplate<String, Object> redisTemplate) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.dishMapper = dishMapper;
        this.setmealMapper = setmealMapper;
        this.redisTemplate = redisTemplate;
    }


    /**
     * 添加购物车
     * @param shoppingCartDTO
     */
    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {

        Long dishId = shoppingCartDTO.getDishId();
        Long setmealId = shoppingCartDTO.getSetmealId();
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        shoppingCart.setUserId(userId); // 只能查询自己的购物车数据

        // 加入 Redis 数据库
        String cartKey = CART_PREFIX + userId; // 缓存用户的键
        String field = dishId != null ? "dish:" + dishId : "setmeal:" + setmealId; // 缓存商品or套餐的键
        redisTemplate.opsForHash().increment(cartKey, field, 1);  // 如已有该商品or套餐则自增，如没有则设为默认值1

        // 加入 MySQL 数据库
        List<ShoppingCart> shoppingCartsList = shoppingCartMapper.list(shoppingCart);
        if (shoppingCartsList != null && !shoppingCartsList.isEmpty()) {
            // 如果已有该商品or套餐，则数量+1
            shoppingCart = shoppingCartsList.get(0);
            shoppingCart.setNumber(shoppingCart.getNumber() + 1); // 数量+1
            shoppingCartMapper.updateById(shoppingCart); // 加入 MySQL 数据库
        } else {
            // 如果本来没有该商品or套餐
            if (dishId != null) {
                // 新加入一件商品
                Dish dish = dishMapper.selectById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            } else {
                // 新加入一件套餐
                Setmeal setmeal = setmealMapper.selectById(setmealId);
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice());
            }
            shoppingCart.setNumber(1); // 设置商品数量为 1
            shoppingCartMapper.insert(shoppingCart); // 加入 MySQL 数据库
        }
    }


    /**
     * 查看购物车，从 Redis 中获取数据
     * @return
     */
    @Override
    public List<ShoppingCart> showShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        String cartKey = CART_PREFIX + userId;

        // 从 Redis 中获取购物车信息
        Map<Object, Object> cartItems = redisTemplate.opsForHash().entries(cartKey);

        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : cartItems.entrySet()) {
            String field = (String) entry.getKey(); // 商品or套餐

            // 解析商品ID和类型
            String[] parts = field.split(":");
            if ("dish".equals(parts[0])) {
                Long dishId = Long.parseLong(parts[1]);
                ShoppingCart cart = shoppingCartMapper.selectById(dishId);
                shoppingCarts.add(cart);
            } else {
                Long setmealId = Long.parseLong(parts[1]);
                ShoppingCart cart = shoppingCartMapper.selectById(setmealId);
                shoppingCarts.add(cart);
            }
        }
        return shoppingCarts;
    }

    /**
     * 清空购物车商品
     */
    @Override
    public void cleanShoppingCart() {

        // 删除 Redis 数据库中的缓存，直接删除用户 Id 对应的全部购物车缓存
        Long userId = BaseContext.getCurrentId();
        String cartKey = CART_PREFIX + userId;
        redisTemplate.delete(cartKey);

        // 删除 MySQL 数据库中的数据
        shoppingCartMapper.deleteByUserId(BaseContext.getCurrentId());
    }

    /**
     * 删除购物车中一个商品
     * @param shoppingCartDTO
     */
    @Override
    public void subShoppingCart(ShoppingCartDTO shoppingCartDTO) {

        // 更改/删除 Redis 数据库中的购物车缓存
        Long userId = BaseContext.getCurrentId();
        String cartKey = CART_PREFIX + userId;
        Long dishId = shoppingCartDTO.getDishId();
        String field = dishId != null ? "dish:" + dishId : "setmeal:" + shoppingCartDTO.getSetmealId();
        Integer currentQuantity = (Integer) redisTemplate.opsForHash().get(cartKey, field);
        if (currentQuantity == 1) {
            // 删除商品
            redisTemplate.opsForHash().delete(cartKey, field);
        } else {
            // 减少商品数量
            redisTemplate.opsForHash().increment(cartKey, field, -1);
        }

        // 修改 MySQL 数据库
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);

        // 设置查询条件，查询当前登录用户的购物车数据
        shoppingCart.setUserId(BaseContext.getCurrentId());

        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);
        if (list != null && !list.isEmpty()) {
            shoppingCart = list.get(0);
            Integer number = shoppingCart.getNumber();
            if (number == 1) {
                // 删除商品
                shoppingCartMapper.deleteById(shoppingCart.getId());
            } else {
                // 减少商品数量
                shoppingCart.setNumber(shoppingCart.getNumber() - 1);
                shoppingCartMapper.updateById(shoppingCart);
            }
        }
    }


}

