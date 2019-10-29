/**
 * 
 */
package com.zhaomengyu.cms.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhaomengyu.cms.entity.User;


/**
 * @author zhaomengyu
 *
 */
public interface UserMapper {

	@Insert("insert into cms_user (username,password,gender,create_time)"
			+ "values(#{username},#{password},#{gender},now())")
	int add(User user);
	
	@Select("select id,username,password,role,locked from cms_user where username=#{value} limit 1")
	User findByName(String username);

	/**
	 * @param user
	 * @return
	 */
	@Update("update cms_user set picture=#{picture} where id=#{id}")
	int addPortrait(User user);
	
}
