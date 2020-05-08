package com.bus.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zwj
 * @since 2020-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_carlicense")
public class Carlicense implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String carid;

    private String cartype;

    private String vin;

    private String enginenumber;

    private String owner;

    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date recorddate;

    private Integer available;
    
    private String img;


}
