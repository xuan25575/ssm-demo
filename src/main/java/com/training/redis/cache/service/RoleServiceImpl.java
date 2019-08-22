package com.training.redis.cache.service;

import com.training.redis.cache.Role;
import com.training.redis.cache.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @date 2019/8/21
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper mapper;

    /**
     * 使用@Cacheable定义缓存策略 当缓存中有值，则返回缓存数据，否则访问方法得到数据
     * 通过value引用缓存管理器，通过key定义键
     * @param id    角色编号
     * @return 角色
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Cacheable(value = "redisCacheManager", key = "'redis_role_'+#id")
    public Role getRole(Long id) {
        return mapper.getRole(id);
    }


    /**
     * 使用@CachePut则表示无论如何都会执行方法，最后将方法的返回值再保存到缓存中
     * 使用在插入数据的地方，则表示保存到数据库后，会同期插入到Redis缓存中
     * @param role 角色对象
     * @return 角色对象（会回填主键）
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CachePut(value = "redisCacheManager", key = "'redis_role_'+#result.id")
    public Role insertRole(Role role) {
        mapper.insert(role);
        return role;
    }

    /**
     * 使用@CachePut，表示更新数据库数据的同时，也会同步更新缓存
     * @param role  角色对象
     * @return  int  条数
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CachePut(value = "redisCacheManager", key = "'redis_role_'+#role.id")
    public int updateRole(Role role) {
       // return mapper.updateRole(role);
        return 0;
    }


    /**
     * CacheEvict 删除缓存对应的key
     * @param id id
     * @return int
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CacheEvict(value = "redisCacheManager", key = "'redis_role_'+#id")
    public int deleteRole(Long id) {
        return mapper.deleteRole(id);
    }

    @Override
    public List<Role> findRoles(String roleName, String note) {
        return null;
    }

    @Override
    public int insertRoles(List<Role> roleList) {
        return 0;
    }


}
