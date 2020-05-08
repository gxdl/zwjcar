package com.gxdl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sys")
public class SystemController {
	
	/**
	 * 跳转到登陆页面
	 */
	@RequestMapping("toLogin")
	public String toLgoin() {
		return "system/index/login";
		
	}
	/**
	 * 跳转到首页
	 */
	@RequestMapping("index")
	public String login() {
		return "system/index/index";
	}
	
	/**
	 * 跳转到desk
	 */
	@RequestMapping("toDeskManager")
	public String toDeskManager() {
		return "system/index/deskManager";
	}
	
	/**
	 * 跳转到日志管理
	 * 
	 */
	@RequestMapping("toLoginfoManager")
	public String toLoginfoManager() {
		return "system/loginfo/loginfoManager";
	}
	
	/**
	 * 跳转到公告
	 */
	@RequestMapping("toNoticeManager")
	public String toNoticeManager() {
		return "system/notice/noticeManager";
	}
	
	/**
	 * 跳转到部门管理
	 */
	@RequestMapping("toDeptManager")
	public String toDeptManager() {
		return "system/dept/toDeptManager";
	}
	
	/**
	 * 跳转到部门管理left
	 */
	@RequestMapping("toDeptLeft")
	public String toDeptLeft() {
		return "system/dept/toDeptLeft";
	}
	
	/**
	 * 跳转到部门管理right
	 */
	@RequestMapping("toDeptRight")
	public String toDeptRight() {
		return "system/dept/toDeptRight";
	}
	
	
	/**
	 * 跳转到菜单管理
	 */
	@RequestMapping("toMenuManager")
	public String toMenuManager() {
		return "system/menu/menuManager";
	}
	
	/**
	 * 跳转到菜单管理left
	 */
	@RequestMapping("toMenuLeft")
	public String toMenuLeft() {
		return "system/menu/menuLeft";
	}
	
	/**
	 * 跳转到菜单管理right
	 */
	@RequestMapping("toMenuRight")
	public String toMenuRight() {
		return "system/menu/menuRight";
	}
	
	/**
	 * 跳转到权限管理
	 */
	@RequestMapping("toPermissionManager")
	public String toPermissionManager() {
		return "system/permission/permissionManager";
	}
	
	/**
	 * 跳转到权限管理left
	 */
	@RequestMapping("toPermissionLeft")
	public String toPermissionLeft() {
		return "system/permission/permissionLeft";
	}
	
	/**
	 * 跳转到权限管理right
	 */
	@RequestMapping("toPermissionRight")
	public String toPermissionRight() {
		return "system/permission/permissionRight";
		
	}
	
	/**
	 * 跳转到角色管理
	 */
	@RequestMapping("toRoleManager")
	public String toRoleManager() {
		return "system/role/roleManager";
	}
	
	/**
	 * 跳转到用户管理
	 */
	@RequestMapping("toUserManager")
	public String toUserManager() {
		return "system/user/userManager";
	}
}
