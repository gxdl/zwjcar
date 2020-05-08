package com.bus.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bus.domain.Driver;
import com.bus.service.DriverService;
import com.bus.vo.DriverVo;
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
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	/**
	 *查询
	 */
	@RequestMapping("loadAllDriver")
	public DataGridView loadAllDriver(DriverVo driverVo) {
		IPage<Driver> page = new Page<>(driverVo.getPage(),driverVo.getLimit());
		QueryWrapper<Driver> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(driverVo.getName()), "name",driverVo.getName());
		queryWrapper.like(StringUtils.isNotBlank(driverVo.getNumber()), "number",driverVo.getNumber());
		queryWrapper.like(StringUtils.isNotBlank(driverVo.getIdcard()), "idcard",driverVo.getIdcard());
		this.driverService.page(page,queryWrapper);
		System.out.println(page.getTotal());
		return new DataGridView(page.getTotal(),page.getRecords());
		
	}
	/**
	 * 添加
	 */
	@RequestMapping("addDriver")
	public ResultObj addDriver(DriverVo driverVo) {
		try {
			this.driverService.save(driverVo);
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
	@RequestMapping("updateDriver")
	public ResultObj updateDriver(DriverVo driverVo) {
		try {
			this.driverService.updateById(driverVo);
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
	@RequestMapping("deleteDriver")
	public ResultObj deleteDriver(DriverVo driverVo) {
		try {
			this.driverService.removeById(driverVo);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping("batchDeleteDriver")
	public ResultObj batchDeleteCar(DriverVo driverVo) {
		try {
			
			Collection< Serializable> idList = new ArrayList<Serializable>();
			for (Integer id : driverVo.getIds()) {
				idList.add(id);
			}
			this.driverService.removeByIds(idList);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
		
	}
}

