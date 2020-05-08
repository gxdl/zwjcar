package com.gxdl.service.impl;

import com.gxdl.domain.Loginfo;
import com.gxdl.mapper.LoginfoMapper;
import com.gxdl.service.LoginfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zwj
 * @since 2019-10-15
 */
@Service
@Transactional
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService {

}
