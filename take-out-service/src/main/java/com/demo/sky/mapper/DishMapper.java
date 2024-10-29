package com.demo.sky.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.sky.dao.Dish;
import com.demo.sky.dao.SetmealDish;
import com.demo.sky.dto.DishPageQueryDTO;
import com.demo.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(Page<DishVO> page, @Param("dto") DishPageQueryDTO dishPageQueryDTO);


    /**
     * 根据分类id查询菜品数量
     * @param categoryId 分类ID
     * @return 菜品数量
     */
    Integer countByCategoryId(@Param("categoryId") Long categoryId);


    /**
     * 根据分类id查询菜品列表
     * @param categoryId 分类ID
     * @return 菜品列表
     */
    List<Dish> listByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 根据套餐id查询菜品
     * @param setmealId 套餐ID
     * @return 菜品列表
     */
    default List<Dish> getBySetmealId(Long setmealId) {
        return selectList(new QueryWrapper<SetmealDish>().eq("setmeal_id", setmealId));
    }

    List<Dish> selectList(@Param("ew") Wrapper<Dish> queryWrapper);
    List<Dish> selectList(QueryWrapper<SetmealDish> setmealId);

    /**
     * 根据条件统计菜品数量
     * @param map map 条件映射
     * @return 菜品数量
     */
    Integer countByMap(Map<String, Object> map);
}
