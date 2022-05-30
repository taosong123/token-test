package com.ts.tokentest.mapper;


import com.ts.tokentest.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User login(User user);
}
