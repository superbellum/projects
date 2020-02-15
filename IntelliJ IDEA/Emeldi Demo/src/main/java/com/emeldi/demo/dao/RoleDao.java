package com.emeldi.demo.dao;

import com.emeldi.demo.entity.Role;

public interface RoleDao
{
	Role findRoleByName(String theRoleName);
}
