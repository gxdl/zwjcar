package com.bus.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bus.domain.Maintenance;
import com.bus.service.MaintenanceService;
import com.bus.vo.MaintenanceVo;
import com.gxdl.common.DataGridView;
import com.gxdl.common.ResultObj;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zwj
 * @since 2020-03-02
 */
@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

	@Autowired
	private MaintenanceService maintenanceService;
	
	/**
	 *查询
	 */
	@RequestMapping("loadAllMaintenance")
	public DataGridView loadAllMaintenance(MaintenanceVo maintenanceVo) {
		IPage<Maintenance> page = new Page<>(maintenanceVo.getPage(),maintenanceVo.getLimit());
		QueryWrapper<Maintenance> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(maintenanceVo.getWxcarid()),"wxcarid", maintenanceVo.getWxcarid());
		queryWrapper.ge(maintenanceVo.getStartTime()!=null, "wxtime", maintenanceVo.getStartTime());
		queryWrapper.le(maintenanceVo.getEndTime()!=null, "wxtime", maintenanceVo.getEndTime());
		queryWrapper.orderByDesc("wxtime");
		this.maintenanceService.page(page,queryWrapper);
		
		return new DataGridView(page.getTotal(),page.getRecords());
		
	}
	/**
	 * 添加
	 */
	@RequestMapping("addMaintenance")
	public ResultObj addMaintenance(MaintenanceVo maintenanceVo) {
		try {
			this.maintenanceService.save(maintenanceVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
		
	}
	/**
	 * 更新
	 */
	@RequestMapping("updateMaintenance")
	public ResultObj updateMaintenance(MaintenanceVo maintenanceVo) {
		try {
			this.maintenanceService.updateById(maintenanceVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping("deleteMaintenance")
	public ResultObj deleteMaintenance(MaintenanceVo maintenanceVo) {
		try {
			this.maintenanceService.removeById(maintenanceVo);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
}

