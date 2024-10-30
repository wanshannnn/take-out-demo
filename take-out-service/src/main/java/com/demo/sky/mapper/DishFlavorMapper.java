package com.demo.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.sky.dao.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {

    /**
     * 批量插入口味数据
     * @param flavors 口味数据列表
     * @return 插入成功的行数
     */
    int insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品id删除数据
     * @param dishId
     * @return
     */
    void deleteByDishId(Long dishId);

    /**
     * 根据菜品id查询口味
     * @param dishId
     * @return
     */
    List<DishFlavor> getByDishId(Long dishId);
}
