package com.demo.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.sky.dao.Dish;
import com.demo.sky.dto.DishPageQueryDTO;
import com.demo.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    IPage<DishVO> pageDish(Page<DishVO> page, DishPageQueryDTO dishPageQueryDTO);


    /**
     * 根据分类id查询菜品数量
     * @param categoryId 分类ID
     * @return 菜品数量
     */
    Integer countByCategoryId(Long categoryId);


    /**
     * 根据分类id查询菜品列表
     * @param categoryId 分类ID
     * @return 菜品列表
     */
    List<Dish> listByCategoryId(Long categoryId);


    /**
     * 根据套餐id查询菜品
     * @param setmealId 套餐ID
     * @return 菜品列表
     */
     List<Dish> getBySetmealId(Long setmealId);


    /**
     * 根据条件统计菜品数量
     * @param map map 条件映射
     * @return 菜品数量
     */
    Integer countByMap(Map<String, Object> map);
}
