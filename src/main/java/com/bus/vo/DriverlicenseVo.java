package com.bus.vo;

import com.bus.domain.Driverlicense;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class DriverlicenseVo extends Driverlicense {
private static final long serialVersionUID = 1L;

	
	private Integer page=1;
	private Integer limit=10;
	private Integer [] ids;
	
	
	
}
