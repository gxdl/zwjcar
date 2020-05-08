package com.gxdl.service.impl;

import com.gxdl.domain.Notice;
import com.gxdl.mapper.NoticeMapper;
import com.gxdl.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zwj
 * @since 2019-10-12
 */
@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
