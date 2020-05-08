package com.bus.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bus.domain.Carlicense;
import com.bus.service.CarlicenseService;
import com.bus.vo.CarlicenseVo;
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
@RequestMapping("/carlicense")
public class CarlicenseController {

	
	@Autowired
	private CarlicenseService carlicenseService;
	/**
	 *查询
	 */
	@RequestMapping("loadAllCarlicense")
	public DataGridView loadAllCarlicense(CarlicenseVo carlicenseVo) {
		IPage<Carlicense> page = new Page<>(carlicenseVo.getPage(),carlicenseVo.getLimit());
		QueryWrapper<Carlicense> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(carlicenseVo.getOwner()), "owner",carlicenseVo.getOwner());
		queryWrapper.like(StringUtils.isNotBlank(carlicenseVo.getCarid()), "carid",carlicenseVo.getCarid());
		this.carlicenseService.page(page,queryWrapper);
		System.out.println(page.getTotal());
		
		return new DataGridView(page.getTotal(),page.getRecords());
		
	}
	/**
	 * 添加
	 */
	@RequestMapping("addCarlicense")
	public ResultObj addCarlicense(CarlicenseVo carlicenseVo) {
		try {
			
			this.carlicenseService.save(carlicenseVo);
			System.out.println(carlicenseVo);
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
	@RequestMapping("updateCarlicense")
	public ResultObj updateCarlicense(CarlicenseVo carlicenseVo) {
		try {
			this.carlicenseService.updateById(carlicenseVo);
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
	@RequestMapping("deleteCarlicense")
	public ResultObj deleteCarlicense(CarlicenseVo carlicenseVo) {
		try {
			this.carlicenseService.removeById(carlicenseVo);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
}

