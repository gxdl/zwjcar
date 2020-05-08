package com.gxdl.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxdl.domain.Dept;
import com.gxdl.mapper.DeptMapper;
import com.gxdl.service.DeptService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zwj
 * @since 2019-10-14
 */
@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
	
	
	@Override
	public boolean updateById(Dept entity) {
		// TODO Auto-generated method stub
		return super.updateById(entity);
	}
	
	@Override
	public boolean removeById(Serializable id) {
		// TODO Auto-generated method stub
		return super.removeById(id);
	}
	
	@Override
	public Dept getById(Serializable id) {
		// TODO Auto-generated method stub
		return super.getById(id);
	}
	
	@Override
	public boolean save(Dept entity) {
		// TODO Auto-generated method stub
		return super.save(entity);
	}
	
}
