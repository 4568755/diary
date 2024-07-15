package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> selectPage(Integer pageSize,Integer page,String username);
}
