/**
 * 
 */
package com.zhaomengyu.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaomengyu.cms.dao.UserMapper;
import com.zhaomengyu.cms.entity.User;
import com.zhaomengyu.cms.service.UserService;
import com.zhaomengyu.utils.Md5Utils;

/**
 * @author zhaomengyu
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;

	
	@Override
	public int register(User user) {
		// TODO Auto-generated method stub
		User existUser = userMapper.findByName(user.getUsername());
		if(existUser!=null) {
			return -1;
		}
		user.setPassword(Md5Utils.md5(user.getPassword(),user.getUsername()));
		return userMapper.add(user);
	}

	
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		String pwdStr = Md5Utils.md5(user.getPassword(),user.getUsername());
		User loginUser = userMapper.findByName(user.getUsername());
		if(loginUser != null && pwdStr.equals(loginUser.getPassword())) {
			return loginUser;
		}
		return null;
	}

	
	@Override
	public boolean checkUserExist(String username) {
		// TODO Auto-generated method stub
		return null!=userMapper.findByName(username);
	}


	/* (non-Javadoc)
	 * @see com.zhaomengyu.cms.service.UserService#addPortrait(com.zhaomengyu.cms.entity.User)
	 */
	@Override
	public int addPortrait(User user) {
		// TODO Auto-generated method stub
		return userMapper.addPortrait(user);
	}
	
	
}
