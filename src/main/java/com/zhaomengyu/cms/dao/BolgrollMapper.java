/**
 * 
 */
package com.zhaomengyu.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zhaomengyu.cms.entity.Blogroll;

/**
 * @author zhaomengyu
 *
 */
public interface BolgrollMapper {

	/**
	 * @param blogroll
	 * @return
	 */
	
	@Insert("insert into cms_blogroll (text,url,created) values (#{text},#{url},now())")
	int add(Blogroll blogroll);

	/**
	 * @return
	 */
	@Select("SELECT * FROM cms_blogroll ORDER BY created DESC ")
	List<Blogroll> list();

}
