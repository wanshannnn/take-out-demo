package com.demo.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.sky.dao.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {

    /**
     * 判断当前菜品是否被套餐关联了
     * @param ids
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> ids);

    /**
     * 保存套餐和菜品的关联关系
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 删除套餐餐品关系表中的数据
     * @param setmealId
     * @return 删除记录数
     */
    int deleteBySetmealId(@Param("setmealId") Long setmealId);

    /**
     * 根据套餐信息查询菜品信息
     * @param setmealId
     * @return 菜品信息列表
     */
    List<SetmealDish> getBySetmealId(@Param("setmealId") Long setmealId);

}
