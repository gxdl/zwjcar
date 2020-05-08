package com.gxdl.mapper;

import com.gxdl.domain.Role;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zwj
 * @since 2019-10-21
 */
public interface RoleMapper extends BaseMapper<Role> {
	//根据角色ID删除sys_role_permission中的数据
	void deleteRolePermissionByRid(Serializable id);
	//根据角色ID删除sys_role_user中的数据
	void deleteRoleUserByRid(Serializable id);
	
	/**
	 * 根据角色ID查询当前角色拥有的所有的权限或菜单ID
	 * @param roleId
	 * @return
	 */
	List<Integer> queryRolePermissionIdsByRid(Integer roleId);
	
	/**
	 * 保存角色和菜单权限之间的关系
	 * @param rid
	 * @param pid
	 */
	void saveRolePermission(@Param("rid")Integer rid,@Param("pid") Integer pid);
	
	/**
	 * //删除用户中间表数据
	 * @param id
	 */
	
	void deleteRoleUserByUid(Serializable id);
	
	/**
	 * 查询用户当前拥有角色id的集合
	 * @param id
	 * @return
	 */
	List<Integer> queryUserRoleIdsByUid(Integer id);
	
	/**
	 * 保存角色和用户的关系
	 * @param uid
	 * @param ids
	 */
	void insertUserRole(@Param("uid")Integer uid, @Param("rid")Integer rid);
	
	

}
