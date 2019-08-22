package com.training.redis.cache.mapper;

import com.training.redis.cache.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description RoleMapper
 * @date 2019/8/21
 */
@Repository
public interface RoleMapper {

     Role getRole(Long id);

     int deleteRole(Long id);

     int insert(Role role);

     int updateRole(Role role);

     List<Role> findRoles(@Param("roleName") String roleName, @Param("note") String note);

}
