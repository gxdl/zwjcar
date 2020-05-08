package com.bus.service.impl;

import com.bus.domain.Car;
import com.bus.mapper.CarMapper;
import com.bus.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zwj
 * @since 2020-02-16
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

}
