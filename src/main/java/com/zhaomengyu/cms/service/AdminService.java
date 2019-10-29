/**
 * 
 */
package com.zhaomengyu.cms.service;

import com.github.pagehelper.PageInfo;
import com.zhaomengyu.cms.entity.User;

/**
 * @author zhaomengyu
 *
 */
public interface AdminService {

	/**
	 * 获取所有用户的列表
	 * @param pageNum
	 * @param name
	 * @return
	 */
	PageInfo<User> UserList(Integer pageNum,String name);
	
	/**
	 * 修改用户状态
	 * @param id
	 * @param locked
	 * @return
	 */
	int modifyUserStatus(Integer id,Integer locked);
}
