package com.ts.tokentest.service;


import com.ts.tokentest.entity.User;
import com.ts.tokentest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        User result = userMapper.login(user);
        if (result!=null){
            return result;
        }
        throw new RuntimeException("登录失败");
    }
}
