/**
 * 
 */
package com.zhaomengyu.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhaomengyu.cms.entity.Special;

/**
 * @author zhaomengyu
 *
 */
public interface SpecialMapper {

	/**
	 * 获取专题的列表
	 * @return
	 */
	@Select("select id,title,abstract as digest,created from cms_special order by id desc")
	List<Special> list();
	
	/**
	 * 添加专题
	 * @param special
	 * @return
	 */
	@Insert("insert into cms_special (title,abstract,created) values (#{title},#{digest},now())")
	int add(Special special);
	
	@Select("select id,title,abstract as digest,created from cms_special where id=#{value}")
	Special findById(Integer id);
	
	@Insert("insert into cms_special_article(sid,aid) values(#{sid},#{aid})")
	int addArticle(@Param("sid")Integer specId,@Param("aid")Integer articleId);
	
	@Delete("delete from cms_special_article where sid=#{sid} and aid=#{aid}")
	int removeArticle(@Param("sid")Integer specId,@Param("aid")Integer articleId);
	
	@Update("update cms_special set title=#{title},abstract=#{digest}"
			+ "where id=#{id}")
	int update(Special special);
	
	
}
