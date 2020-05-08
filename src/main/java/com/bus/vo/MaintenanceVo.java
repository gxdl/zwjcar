package com.bus.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bus.domain.Maintenance;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class MaintenanceVo extends Maintenance {
private static final long serialVersionUID = 1L;

	
	private Integer page=1;
	private Integer limit=10;
	private Integer [] ids;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
}
