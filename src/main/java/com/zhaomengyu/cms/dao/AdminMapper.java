/**
 * 
 */
package com.zhaomengyu.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.zhaomengyu.cms.entity.User;

/**
 * @author zhaomengyu
 *
 */
public interface AdminMapper {

	//获取用户列表
	List<User> userList(@Param("name")String name);
	
	//修改用户的状态
	@Update("update cms_user set locked=#{locked} where id=#{id}")
	int modifyUserStatus(@Param("id")Integer id,@Param("locked")Integer locked);
}
