/**
 * 
 */
package com.zhaomengyu.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaomengyu.cms.dao.AdminMapper;
import com.zhaomengyu.cms.entity.User;
import com.zhaomengyu.cms.service.AdminService;

/**
 * @author zhaomengyu
 *
 */
@Service
public class AdminServiceImpl implements AdminService{

	
	@Autowired
	AdminMapper am;
	
	//获取用户列表
	@Override
	public PageInfo<User> UserList(Integer pageNum, String name) {
		PageHelper.startPage(pageNum,10);
		List<User> userList = am.userList(name);
		return new PageInfo<>(userList);
		
	}

	//修改用户的状态
	@Override
	public int modifyUserStatus(Integer id, Integer locked) {
		int res = am.modifyUserStatus(id, locked);
		return res;
	}

}
