package com.demo.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.sky.dao.Category;
import com.demo.sky.dao.Employee;
import com.demo.sky.dto.CategoryPageQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 分页查询
     * @param page 分页对象
     * @param categoryPageQueryDTO 查询条件
     * @return IPage<Category> 分页结果
     */
    IPage<Category> pageCategory(Page<Category> page, @Param("query") CategoryPageQueryDTO categoryPageQueryDTO);


    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    List<Category> list(Integer type);

}
