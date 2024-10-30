package com.demo.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.sky.context.BaseContext;
import com.demo.sky.dao.AddressBook;
import com.demo.sky.mapper.AddressBookMapper;
import com.demo.sky.service.AddressBookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

    private final AddressBookMapper addressBookMapper;

    public AddressBookServiceImpl(AddressBookMapper addressBookMapper) {
        this.addressBookMapper = addressBookMapper;
    }

    /**
     * 条件查询
     * @param addressBook
     * @return
     */
    @Override
    public List<AddressBook> list(AddressBook addressBook) {
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        if (addressBook.getUserId() != null) {
            queryWrapper.eq(AddressBook::getUserId, addressBook.getUserId());
        }
        if (addressBook.getIsDefault() != null) {
            queryWrapper.eq(AddressBook::getIsDefault, addressBook.getIsDefault());
        }
        // 其他条件可以按需添加
        return addressBookMapper.selectList(queryWrapper);
    }

    /**
     * 新增地址
     *
     * @param addressBook
     * @return
     */
    @Override
    public boolean save(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(0);
        addressBookMapper.insert(addressBook);
        return false;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public AddressBook getById(Long id) {
        return addressBookMapper.selectById(id);
    }

    /**
     * 根据id修改地址
     * @param addressBook
     */
    @Override
    public void update(AddressBook addressBook) {
        addressBookMapper.updateById(addressBook);
    }

    /**
     * 设置默认地址
     * @param addressBook
     */
    @Transactional
    @Override
    public void setDefault(AddressBook addressBook) {
        // 1. 取消当前用户的所有默认地址
        Long currentUserId = BaseContext.getCurrentId(); // 获取当前用户ID
        addressBookMapper.updateIsDefaultByUserId(currentUserId); // 取消默认

        // 2. 设置当前地址为默认地址
        addressBook.setIsDefault(1);
        addressBookMapper.updateById(addressBook);
    }


    /**
     * 根据id删除地址
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        addressBookMapper.deleteById(id);
    }
}
