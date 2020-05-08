package com.gxdl.controller;


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
import com.gxdl.common.DataGridView;
import com.gxdl.common.ResultObj;
import com.gxdl.domain.Loginfo;
import com.gxdl.service.LoginfoService;
import com.gxdl.vo.LoginfoVo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 老雷
 * @since 2019-09-21
 */
@RestController
@RequestMapping("/loginfo")
public class LoginfoController {
	
	@Autowired
	private LoginfoService loginfoService;
	
	/**
	 * 全查询
	 */
	@RequestMapping("loadAllLoginfo")
	public DataGridView loadAllLoginfo(LoginfoVo loginfoVo) {
		IPage<Loginfo> page=new Page<>(loginfoVo.getPage(), loginfoVo.getLimit());
		QueryWrapper<Loginfo> queryWrapper=new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginname()),"loginname", loginfoVo.getLoginname());
		queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginip()), "loginip",loginfoVo.getLoginip());
		queryWrapper.ge(loginfoVo.getStartTime()!=null, "logintime", loginfoVo.getStartTime());
		queryWrapper.le(loginfoVo.getEndTime()!=null, "logintime", loginfoVo.getEndTime());
		queryWrapper.orderByDesc("logintime");
		this.loginfoService.page(page, queryWrapper);
		long total = page.getTotal();
		System.out.println(total);
		return new DataGridView(total, page.getRecords());
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping("deleteLoginfo")
	public ResultObj deleteLoginfo(Integer id) {
		try {
			this.loginfoService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	
	/**
	 * 批量删除
	 */
	@RequestMapping("batchDeleteLoginfo")
	public ResultObj batchDeleteLoginfo(LoginfoVo loginfoVo) {
		try {
			Collection<Serializable> idList=new ArrayList<Serializable>();
			for (Integer id : loginfoVo.getIds()) {
				idList.add(id);
			}
			this.loginfoService.removeByIds(idList);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}

}

