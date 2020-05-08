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
import com.bus.domain.File;
import com.bus.service.FileService;
import com.bus.vo.FileVo;
import com.gxdl.common.DataGridView;
import com.gxdl.common.ResultObj;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zwj
 * @since 2020-02-16
 */
@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;
	/**
	 *查询
	 */
	@RequestMapping("loadAllFile")
	public DataGridView loadAllFile(FileVo fileVo) {
		IPage<File> page = new Page<>(fileVo.getPage(),fileVo.getLimit());
		QueryWrapper<File> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(fileVo.getName()), "driver",fileVo.getName());
		queryWrapper.like(StringUtils.isNotBlank(fileVo.getNumber()), "fileid",fileVo.getNumber());
		this.fileService.page(page,queryWrapper);
		
		return new DataGridView(page.getTotal(),page.getRecords());
		
	}
	/**
	 * 添加
	 */
	@RequestMapping("addFile")
	public ResultObj addFile(FileVo fileVo) {
		try {
			
			this.fileService.save(fileVo);
			System.out.println(fileVo);
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
	@RequestMapping("updateFile")
	public ResultObj updateFile(FileVo fileVo) {
		try {
			this.fileService.updateById(fileVo);
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
	@RequestMapping("deleteFile")
	public ResultObj deleteFile(FileVo fileVo) {
		try {
			this.fileService.removeById(fileVo);
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
	@RequestMapping("batchDeleteFile")
	public ResultObj batchDeleteFile(FileVo fileVo) {
		try {
			
			Collection< Serializable> idList = new ArrayList<Serializable>();
			for (Integer id : fileVo.getIds()) {
				idList.add(id);
			}
			this.fileService.removeByIds(idList);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
		
	}
}


