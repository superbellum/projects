package com.emeldi.demo.dao;

import com.emeldi.demo.entity.User;

public interface UserDao
{
    User findByUserName(String userName);
    
    void save(User user);
}
