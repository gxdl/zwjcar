package com.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus")
public class BusinessController {

	
	/**
	 * 跳转到车辆管理
	 */
	@RequestMapping("toCarManager")
	public String toCarManager(){
		return "business/car/carManager";
		
	}
	

	/**
	 * 跳转到司机管理
	 */
	@RequestMapping("toDriverManager")
	public String toDriverManager(){
		return "business/car/driverManager";
		
	}
	

	/**
	 * 跳转到维修保修管理
	 */
	@RequestMapping("toMaintenanceManager")
	public String toMaintenanceManager(){
		return "business/car/maintenanceManager";
		
	}
	

	/**
	 * 跳转到违法管理
	 */
	@RequestMapping("toLawManager")
	public String toLawManager(){
		return "business/car/lawManager";
		
	}
	
	/**
	 * 跳转到驾驶证管理
	 */
	@RequestMapping("toDriverLicenseManager")
	public String toDriverLicenseManager(){
		return "business/car/driverLicenseManager";
		
	}
	
	/**
	 * 跳转到行驶证管理
	 */
	@RequestMapping("toCarLicenseManager")
	public String toCarLicenseManager(){
		return "business/car/carLicenseManager";
		
	}
	
	/**
	 *跳转到文件与图片管理
	 */
	@RequestMapping("toFileManager")
	public String toFileManager() {
		return "business/car/toFileManager";
	}
}
