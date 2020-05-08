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
import com.bus.domain.Car;
import com.bus.service.CarService;
import com.bus.vo.CarVo;
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
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;
	/**
	 *查询
	 */
	@RequestMapping("loadAllCar")
	public DataGridView loadAllCar(CarVo carVo) {
		IPage<Car> page = new Page<>(carVo.getPage(),carVo.getLimit());
		QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(carVo.getDriver()), "driver",carVo.getDriver());
		queryWrapper.like(StringUtils.isNotBlank(carVo.getCarid()), "carid",carVo.getCarid());
		this.carService.page(page,queryWrapper);
		System.out.println(page.getTotal());
		
		return new DataGridView(page.getTotal(),page.getRecords());
		
	}
	/**
	 * 添加
	 */
	@RequestMapping("addCar")
	public ResultObj addCar(CarVo carVo) {
		try {
			System.out.println(carVo.getProductdate());
			carVo.setProductdate(carVo.getProductdate());
			this.carService.save(carVo);
			System.out.println(carVo);
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
	@RequestMapping("updateCar")
	public ResultObj updateCar(CarVo carVo) {
		try {
			this.carService.updateById(carVo);
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
	@RequestMapping("deleteCar")
	public ResultObj deleteCar(CarVo carVo) {
		try {
			this.carService.removeById(carVo);
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
	@RequestMapping("batchDeleteCar")
	public ResultObj batchDeleteCar(CarVo carVo) {
		try {
			
			Collection< Serializable> idList = new ArrayList<Serializable>();
			for (Integer id : carVo.getIds()) {
				idList.add(id);
			}
			this.carService.removeByIds(idList);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
		
	}
}


