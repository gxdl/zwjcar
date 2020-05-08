package com.gxdl.service.impl;

import com.gxdl.domain.Permission;
import com.gxdl.mapper.PermissionMapper;
import com.gxdl.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
	/**
	 * 根据菜单id删除权限菜单关系表的pid数据
	 */
	@Override
	public boolean removeById(Serializable id) {
		PermissionMapper permissionMapper = this.getBaseMapper();
		//根据权限或菜单ID删除权限表各和角色的关系表里面的数据
		
		permissionMapper.deleteRolePermissionByPid(id);
		return super.removeById(id);//删除 权限表的数据
	}
}
