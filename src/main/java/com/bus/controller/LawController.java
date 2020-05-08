package com.bus.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bus.domain.Law;
import com.bus.service.LawService;
import com.bus.vo.LawVo;
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
@RequestMapping("/law")
public class LawController {

	@Autowired
	private LawService lawService;
	
	/**
	 *查询
	 */
	@RequestMapping("loadAllLaw")
	public DataGridView loadAllLaw(LawVo lawVo) {
		IPage<Law> page = new Page<>(lawVo.getPage(),lawVo.getLimit());
		QueryWrapper<Law> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(lawVo.getDriver()), "driver",lawVo.getDriver());
		queryWrapper.like(StringUtils.isNotBlank(lawVo.getCarid()),"carid", lawVo.getCarid());
		this.lawService.page(page,queryWrapper);
		System.out.println(page.getTotal());
		return new DataGridView(page.getTotal(),page.getRecords());
		
	}
	/**
	 * 添加
	 */
	@RequestMapping("addLaw")
	public ResultObj addLaw(LawVo lawVo) {
		try {
			this.lawService.save(lawVo);
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
	@RequestMapping("updateLaw")
	public ResultObj updateLaw(LawVo lawVo) {
		try {
			this.lawService.updateById(lawVo);
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
	@RequestMapping("deleteLaw")
	public ResultObj deleteLaw(LawVo lawVo) {
		try {
			this.lawService.removeById(lawVo);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
}

