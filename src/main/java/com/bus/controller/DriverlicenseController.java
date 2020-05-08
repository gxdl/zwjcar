package com.bus.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bus.domain.Driverlicense;
import com.bus.service.DriverlicenseService;
import com.bus.vo.DriverlicenseVo;
import com.gxdl.common.DataGridView;
import com.gxdl.common.ResultObj;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zwj
 * @since 2020-04-23
 */
@RestController
@RequestMapping("/driverlicense")
public class DriverlicenseController {

	@Autowired
	private DriverlicenseService driverlicenseService;
	/**
	 *查询
	 */
	@RequestMapping("loadAllDriverlicense")
	public DataGridView loadAllDriverlicense(DriverlicenseVo driverlicenseVo) {
		IPage<Driverlicense> page = new Page<>(driverlicenseVo.getPage(),driverlicenseVo.getLimit());
		QueryWrapper<Driverlicense> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(driverlicenseVo.getName()), "name",driverlicenseVo.getName());
		queryWrapper.like(StringUtils.isNotBlank(driverlicenseVo.getSex()), "sex",driverlicenseVo.getSex());
		queryWrapper.like(StringUtils.isNotBlank(driverlicenseVo.getIdcard()), "idcard",driverlicenseVo.getIdcard());
		queryWrapper.like(StringUtils.isNotBlank(driverlicenseVo.getDrivingtype()), "drivingtype",driverlicenseVo.getDrivingtype());
		this.driverlicenseService.page(page,queryWrapper);
		System.out.println(page.getTotal());
		
		return new DataGridView(page.getTotal(),page.getRecords());
		
	}
	/**
	 * 添加
	 */
	@RequestMapping("addDriverlicense")
	public ResultObj addDriverlicense(DriverlicenseVo driverlicenseVo) {
		try {
			
			this.driverlicenseService.save(driverlicenseVo);
			System.out.println(driverlicenseVo);
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
	@RequestMapping("updateDriverlicense")
	public ResultObj updateDriverlicense(DriverlicenseVo driverlicenseVo) {
		try {
			this.driverlicenseService.updateById(driverlicenseVo);
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
	@RequestMapping("deleteDriverlicense")
	public ResultObj deleteDriverlicense(DriverlicenseVo driverlicenseVo) {
		try {
			this.driverlicenseService.removeById(driverlicenseVo);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
}

