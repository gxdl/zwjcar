package com.gxdl.service;

import com.gxdl.domain.Role;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zwj
 * @since 2019-10-21
 */
public interface RoleService extends IService<Role> {


	/**
	 * 根据角色ID查询当前角色拥有的所有的权限或菜单ID
	 * @param roleId
	 * @return
	 */
	List<Integer> queryRolePermissionIdsByRid(Integer roleId);

	
	/**
	 * 保存角色和菜单权限之间的关系
	 * @param roleId
	 * @param ids
	 */
	void saveRolePermission(Integer roleId, Integer[] ids);

	/**
	 * 查询用户当前拥有角色id的集合
	 * @param id
	 * @return
	 */
	List<Integer> queryUserRoleIdsByUid(Integer id);
	
	
}
