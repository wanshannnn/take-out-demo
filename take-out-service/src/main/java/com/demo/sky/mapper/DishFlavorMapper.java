package com.demo.sky.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.sky.dao.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.Db.list;

@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {

    /**
     * 删除菜单关联的口味数据
     *
     * @param dish_id 菜品ID
     * @return 是否成功
     */
    default int deleteByDishId(Long dish_id) {
        return delete(new QueryWrapper<DishFlavor>().eq("dish_id", dish_id));
    }

    /**
     * 根据菜品id查询口味数据
     * @param dish_id 菜品ID
     * @return 口味数据列表
     */
    default List<DishFlavor> getByDishId(Long dish_id) {
        return list(new QueryWrapper<DishFlavor>().eq("dish_id", dish_id));
    }


    /**
     * 批量插入口味数据
     * @param flavors 口味数据列表
     * @return 插入成功的行数
     */
    int insertBatch(@Param("flavors") List<DishFlavor> flavors);

}
