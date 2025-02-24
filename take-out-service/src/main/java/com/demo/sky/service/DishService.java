package com.demo.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.sky.dto.DishDTO;
import com.demo.sky.dto.DishPageQueryDTO;
import com.demo.sky.dao.Dish;
import com.demo.sky.result.PageResult;
import com.demo.sky.vo.DishVO;

import java.util.List;

public interface DishService extends IService<Dish> {
    /**
     * 新增菜品
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 菜品批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 修改菜品
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);

    /**
     * 缓存数据库中查询菜品和口味
     * @param categoryId
     * @return
     */
    List<DishVO> listWithFlavorByCategory(Long categoryId);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);

    /**
     * 菜品起售停售
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}