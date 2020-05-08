package com.gxdl.mapper;

import com.gxdl.domain.Permission;

import java.io.Serializable;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zwj
 * @since 2019-10-11
 */

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

	/**
	 * /根据权限或菜单ID删除权限表各和角色的关系表里面的数据
	 * @param id
	 */
	int deleteRolePermissionByPid(@Param("id")Serializable id);

	

}
