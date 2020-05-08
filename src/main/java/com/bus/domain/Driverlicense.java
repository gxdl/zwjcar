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
@TableName("bus_driverlicense")
public class Driverlicense implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String sex;

    private String nationality;

    private String address;

    private String idcard;

    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthdate;

    private String drivingtype;

    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date startdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date enddate;

    private Integer available;


}
