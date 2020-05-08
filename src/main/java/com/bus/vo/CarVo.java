package com.bus.vo;

import com.bus.domain.Car;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class CarVo extends Car {
private static final long serialVersionUID = 1L;

	
	private Integer page=1;
	private Integer limit=10;
	private Integer [] ids;
	
	
	
}
