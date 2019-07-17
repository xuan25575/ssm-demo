package com.training.service;

import com.training.dto.UserInfo;

import java.util.List;

public interface IUserService {

    UserInfo findUserById(Integer id);

    void update(UserInfo userInfo);

    void delete(Integer id);

    List<UserInfo> getUserInfoList();

    void add(UserInfo userInfo);
}
