package com.gxdl.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxdl.domain.User;
import com.gxdl.mapper.RoleMapper;
import com.gxdl.mapper.UserMapper;
import com.gxdl.service.UserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zwj
 * @since 2019-10-11
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public User getById(Serializable id) {
		
		return super.getById(id);
	}
	
	@Override
	public boolean save(User entity) {
	
		return super.save(entity);
	}
	
	@Override
	public boolean updateById(User entity) {
		
		return super.updateById(entity);
	}
	@Override
	public boolean removeById(Serializable id) {
	//删除用户中间表数据
		roleMapper.deleteRoleUserByUid(id);
		return super.removeById(id);
	}
	
	
	//保存用户和角色之间的关系
	@Override
	public void saveUserRole(Integer uid, Integer[] ids) {
		//根据用户id删除sys_role_user表里数据
		this.roleMapper.deleteRoleUserByUid(uid);
		if (null!=ids&&ids.length>0) {
			for (Integer rid : ids) {
				this.roleMapper.insertUserRole(uid,rid);
			}
		}
		
		
	}
}

