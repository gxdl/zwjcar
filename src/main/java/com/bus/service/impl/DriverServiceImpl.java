package com.bus.service.impl;

import com.bus.domain.Driver;
import com.bus.mapper.DriverMapper;
import com.bus.service.DriverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zwj
 * @since 2020-03-02
 */
@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements DriverService {

}
