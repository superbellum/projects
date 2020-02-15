package com.emeldi.demo.service;

import com.emeldi.demo.entity.User;
import com.emeldi.demo.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
    User findByUserName(String userName);

    void save(CrmUser crmUser);
}
