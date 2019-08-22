package com.training.redis.cache.service;

import com.training.redis.cache.Role;

import java.util.List;

/**
 * @Description TODO
 * @date 2019/8/21
 */
public interface RoleService {

     Role getRole(Long id);

     int deleteRole(Long id);

     Role insertRole(Role role);

     int updateRole(Role role);

     List<Role> findRoles(String roleName, String note);

     int insertRoles(List<Role> roleList);

}
