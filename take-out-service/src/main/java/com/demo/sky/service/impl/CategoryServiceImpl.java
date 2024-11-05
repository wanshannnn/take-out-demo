package com.demo.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.sky.constant.StatusConstant;
import com.demo.sky.dto.CategoryDTO;
import com.demo.sky.dto.CategoryPageQueryDTO;
import com.demo.sky.dao.Category;
import com.demo.sky.exception.DeletionNotAllowedException;
import com.demo.sky.exception.ErrorCode;
import com.demo.sky.mapper.CategoryMapper;
import com.demo.sky.mapper.DishMapper;
import com.demo.sky.mapper.SetmealMapper;
import com.demo.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * 分类业务层
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final DishMapper dishMapper;
    private final SetmealMapper setmealMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper,DishMapper dishMapper,SetmealMapper setmealMapper) {
        this.categoryMapper = categoryMapper;
        this.dishMapper = dishMapper;
        this.setmealMapper = setmealMapper;
    }

    /**
     * 新增分类
     * @param categoryDTO
     */
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setStatus(StatusConstant.DISABLE); // 分类状态默认为禁用状态0
        this.save(category);
    }

    /**
     * 分页查询
     * @param categoryPageQueryDTO 查询条件
     * @return IPage<Category> 分页结果
     */
    public IPage<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        Page<Category> page = new Page<>(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        IPage<Category> categoryPage = categoryMapper.pageCategory(page, categoryPageQueryDTO);
        return categoryPage;
    }

    /**
     * 根据id删除分类
     * @param id
     */
    public void deleteById(Long id) {

        //查询当前分类是否关联了菜品，如果关联了就抛出业务异常
        Integer count = dishMapper.countByCategoryId(id);
        if(count > 0){
            //当前分类下有菜品，不能删除
            HashMap<String, Object> data = new HashMap<>();
            data.put("category_id", id);
            data.put("timestamp", LocalDateTime.now());
            throw new DeletionNotAllowedException(ErrorCode.CATEGORY_BE_RELATED_BY_DISH, data);
        }

        //查询当前分类是否关联了套餐，如果关联了就抛出业务异常
        count = setmealMapper.countByCategoryId(id);
        if(count > 0){
            //当前分类下有菜品，不能删除
            HashMap<String, Object> data = new HashMap<>();
            data.put("category_id", id);
            data.put("timestamp", LocalDateTime.now());
            throw new DeletionNotAllowedException(ErrorCode.CATEGORY_BE_RELATED_BY_SETMEAL,data);
        }

        this.removeById(id);
    }

    /**
     * 修改分类
     * @param categoryDTO
     */
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        this.updateById(category);
    }

    /**
     * 启用、禁用分类
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Category::getId, id)
                .set(Category::getStatus, status);
        categoryMapper.update(updateWrapper);
    }

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    public List<Category> list(Integer type) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getType, type);
        return this.list(queryWrapper);
    }
}
