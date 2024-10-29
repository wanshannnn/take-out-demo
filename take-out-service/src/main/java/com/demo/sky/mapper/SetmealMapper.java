package com.demo.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.sky.dao.Setmeal;
import com.demo.sky.dto.SetmealPageQueryDTO;
import com.demo.sky.vo.DishItemVO;
import com.demo.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {

    /**
     * 根据分类id查询套餐的数量
     * @param categoryId
     * @return
     */
    Integer countByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据条件统计套餐数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    IPage<SetmealVO> pageQuery(Page<SetmealVO> page, @Param("query") SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根据套餐 ID 查询菜品选项
     * @param setmealId 套餐 ID
     * @return 菜品选项列表
     */
    List<DishItemVO> getDishItemBySetmealId(@Param("setmealId") Long setmealId);

}
