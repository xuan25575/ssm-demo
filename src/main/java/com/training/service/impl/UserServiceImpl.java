package com.training.service.impl;

import com.training.dto.UserInfo;
import com.training.mapper.UserInfoMapper;
import com.training.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findUserById(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id.longValue());
    }

    @Override
    public void update(UserInfo userInfo) {
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public void delete(Integer id) {
        userInfoMapper.deleteByPrimaryKey(id.longValue());
    }

    @Override
    public List<UserInfo> getUserInfoList() {
        return userInfoMapper.getUserInfoList();
    }

    @Override
    public void add(UserInfo userInfo) {
        userInfoMapper.insertSelective(userInfo);
    }
}
