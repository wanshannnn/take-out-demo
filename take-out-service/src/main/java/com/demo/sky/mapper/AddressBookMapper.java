package com.demo.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.sky.dao.AddressBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

    /**
     * 条件查询
     * @param addressBook
     * @return
     */
    List<AddressBook> list(AddressBook addressBook);


    /**
     * 根据用户ID更新是否为默认地址
     * @param userId 用户ID
     */
    @Update("UPDATE address_book SET is_default = 0 WHERE user_id = #{userId} AND is_default = 1")
    void updateIsDefaultByUserId(@Param("userId") Long userId);
}
