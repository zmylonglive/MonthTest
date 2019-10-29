/**
 * 
 */
package com.zhaomengyu.cms.service;

import com.zhaomengyu.cms.entity.User;

/**
 * @author zhaomengyu
 *
 */
public interface UserService {

	int register(User user);
	
	User login(User user);
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	boolean checkUserExist(String username);

	/**
	 * @param user
	 * @return
	 */
	int addPortrait(User user);
}
