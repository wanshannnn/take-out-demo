package com.demo.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.sky.dto.EmployeePageQueryDTO;
import com.demo.sky.dao.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    Employee getByUsername(@Param("username") String username);

    /**
     * 员工分页查询
     * @param page 分页对象
     * @param employeePageQueryDTO 查询条件
     * @return
     */
    IPage<Employee> pageEmployee(Page<Employee> page, @Param("query") EmployeePageQueryDTO employeePageQueryDTO);

}
