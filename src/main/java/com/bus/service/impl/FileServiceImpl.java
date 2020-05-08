package com.bus.service.impl;

import com.bus.domain.File;
import com.bus.mapper.FileMapper;
import com.bus.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zwj
 * @since 2020-04-28
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}
