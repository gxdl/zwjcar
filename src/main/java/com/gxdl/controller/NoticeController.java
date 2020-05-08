package com.gxdl.controller;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxdl.common.DataGridView;
import com.gxdl.common.ResultObj;
import com.gxdl.common.WebUtils;
import com.gxdl.domain.Notice;
import com.gxdl.domain.User;
import com.gxdl.service.NoticeService;
import com.gxdl.vo.NoticeVo;




/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zwj
 * @since 2019-10-12
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("loadAllNotice")
	public DataGridView loadAllNotice(NoticeVo noticeVo) {
		
		IPage<Notice> page = new Page<>(noticeVo.getPage(), noticeVo.getLimit()) ;
		
		QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
		
		queryWrapper.like(StringUtils.isNotBlank(noticeVo.getTitle()),"title",noticeVo.getTitle());
		
		queryWrapper.like(StringUtils.isNotBlank(noticeVo.getOpername()), "opername",noticeVo.getOpername());
		
		queryWrapper.ge(noticeVo.getStartTime()!=null, "createtime",noticeVo.getCreatetime());
		
		queryWrapper.le(noticeVo.getEndTime()!=null, "endtime",noticeVo.getEndTime());
		
		queryWrapper.orderByDesc("createtime");
		
		this.noticeService.page(page, queryWrapper);
		
		return new DataGridView(page.getTotal(),page.getRecords());
	}

	/**
	 * 添加
	 */
	@RequestMapping("addNotice")
	public ResultObj addNotice(NoticeVo noticeVo) {
		
		try {
			noticeVo.setCreatetime(new Date());
			User user = (User) WebUtils.getSession().getAttribute("user");
			noticeVo.setOpername(user.getName());
			this.noticeService.save(noticeVo);
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
	@RequestMapping("updateNotice")
	public ResultObj updateNotice(NoticeVo noticeVo) {
		
		
		try {
			
			this.noticeService.updateById(noticeVo);
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
	@RequestMapping("deleteNotice")
	
	public ResultObj deleteNotice(Integer id) {
		try {
			this.noticeService.removeById(id);
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
 	@RequestMapping("batchDeleteNotice")
 	
 	public ResultObj batchDeleteNotice(NoticeVo noticeVo) {
 		
 		try {
			Collection<Serializable> idList = new ArrayList<Serializable>();
			for (Integer id : noticeVo.getIds()) {
				idList.add(id);
			}
			this.noticeService.removeByIds(idList);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
 		
 	}
	
}

