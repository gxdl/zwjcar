package com.gxdl.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxdl.common.Constast;
import com.gxdl.common.DataGridView;
import com.gxdl.common.ResultObj;
import com.gxdl.common.TreeNode;
import com.gxdl.domain.Permission;
import com.gxdl.domain.Role;
import com.gxdl.service.PermissionService;
import com.gxdl.service.RoleService;
import com.gxdl.vo.RoleVo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zwj
 * @since 2019-10-21
 */
@RestController
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	
	
	/**
	 * 查询
	 */
	@RequestMapping("loadAllRole")
	public DataGridView loadAllRole(RoleVo roleVo) {
		IPage<Role> page=new Page<>(roleVo.getPage(), roleVo.getLimit());
		QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(roleVo.getName()), "name", roleVo.getName());
		queryWrapper.like(StringUtils.isNotBlank(roleVo.getRemark()), "remark", roleVo.getRemark());
		queryWrapper.eq(roleVo.getAvailable()!=null, "available", roleVo.getAvailable());
		queryWrapper.orderByDesc("createtime");
		this.roleService.page(page, queryWrapper);
		return new DataGridView(page.getTotal(), page.getRecords());
	}
	
	
	/**
	 * 添加
	 */
	@RequestMapping("addRole")
	public ResultObj addRole(RoleVo roleVo) {
		try {
			roleVo.setCreatetime(new Date());
			this.roleService.save(roleVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 修改
	 */
	@RequestMapping("updateRole")
	public ResultObj updateRole(RoleVo roleVo) {
		try {
			this.roleService.updateById(roleVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("deleteRole")
	public ResultObj deleteRole(Integer id) {
		try {
			this.roleService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	

	@RequestMapping("initPermissionByRoleId")
	public DataGridView initPermissionByRoleId(Integer roleId) {
		//查询可用的菜单和权限
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
		List<Permission> allPermission = permissionService.list(queryWrapper);
		/**
		 * 1,根据角色ID查询当前角色拥有的所有的权限或菜单ID
		 * 2,根据查询出来的菜单ID查询权限和菜单数据
		 */
		List<Integer> currentRolePermissions=this.roleService.queryRolePermissionIdsByRid(roleId);
		List<Permission> carrentPermissions=null;
		if (currentRolePermissions.size()>0) {
			queryWrapper.in("id", currentRolePermissions);
			carrentPermissions = permissionService.list(queryWrapper);
		}else {
			carrentPermissions = new ArrayList<>();
		}
		
		//构造 List<TreeNode>
		List<TreeNode> nodes = new ArrayList<>();
		for (Permission p1 : allPermission) {
			String checkArr = "0";
			for (Permission p2 : carrentPermissions) {
				if (p1.getId()==p2.getId()) {
					checkArr="1";
					break;
				}
			}
			Boolean spread = (p1.getOpen()==null||p1.getOpen()==1)?true:false;
			nodes.add(new TreeNode(p1.getId(), p1.getPid(), p1.getTitle(), spread, checkArr));
		}
		return new DataGridView (nodes);
		
	}
	
	@RequestMapping("saveRolePermission")
	public ResultObj saveRolePermission(Integer rid,Integer[] ids) {
		try {
			this.roleService.saveRolePermission(rid,ids);
			System.out.println(rid);
			System.out.println(ids);
			return ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DISPATCH_ERROR;
		}
	}
}

