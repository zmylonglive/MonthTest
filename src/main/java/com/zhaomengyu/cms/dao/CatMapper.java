/**
 * 
 */
package com.zhaomengyu.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.zhaomengyu.cms.entity.Cat;

/**
 * @author zhaomengyu
 *
 */
@Mapper
public interface CatMapper {
	/**
	 * 
	 * @param chnlId 频道主键id
	 * @return
	 */
	@Select("select id,name,channel_id channelId from cms_category where channel_id=#{value}")
	List<Cat> selectBuChnId(Integer id);
	
	/**
	 * 根据id获取对应的分类
	 * @param id
	 * @return
	 */
	Cat findById(Integer id);
	
}
