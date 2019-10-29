/**
 * 
 */
package com.zhaomengyu.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.zhaomengyu.cms.entity.Channel;

/**
 * @author zhaomengyu
 *
 */
public interface ChannelMapper {

	/**
	 * 获取所有的频道
	 */
	
	@Select("select * from cms_channel order by id")
	List<Channel> listAll();
	
	/**
	 * 根据id获取对应的频道
	 * @param id
	 * @return
	 */
	Channel findById(Integer id);
}
