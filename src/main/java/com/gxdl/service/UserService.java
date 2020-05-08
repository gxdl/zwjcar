package com.gxdl.service;

import com.gxdl.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zwj
 * @since 2019-10-11
 */
public interface UserService extends IService<User> {
	/**
	 * 保存用户和角色之间的关系
	 * @param uid
	 * @param ids
	 */
	void saveUserRole(Integer uid, Integer[] ids);

}
