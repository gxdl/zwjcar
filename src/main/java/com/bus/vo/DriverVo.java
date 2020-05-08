package com.bus.vo;

import com.bus.domain.Driver;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class DriverVo extends Driver {
private static final long serialVersionUID = 1L;

	
	private Integer page=1;
	private Integer limit=10;
	private Integer [] ids;
}
