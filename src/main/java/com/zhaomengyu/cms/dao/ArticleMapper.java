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
import org.hibernate.validator.constraints.Mod10Check;

import com.zhaomengyu.cms.entity.Article;
import com.zhaomengyu.cms.entity.Term;

/**
 * @author zhaomengyu
 *
 */
public interface ArticleMapper {

	/**
	 * 频道id必须大于0，分类id可以为0，当分类id为0的时候，查询该频道下的所有分类的文章
	 * 是否在查询该分类下的文章
	 * @param chnId 频道id
	 * @param catId 分类id
	 * @return
	 */
	List<Article> list(@Param("chnId")Integer chnId,@Param("catId")Integer catId);
	
	/**
	 * 获取热门文章
	 */
	List<Article> listHot();
	
	
	/**
	 * 获取最新文章
	 */
	List<Article> listLast(int sum);
	
	/**
	 * 
	 */
	Article findById(Integer articleId);
	
	
	/**
	 * 添加文章
	 */
	int add(Article article);
	
	/**
	 * 
	 */
	List<Article> listByUserId(Integer userId);
	
	/**
	 * 根据文章id删除文章
	 */
	@Update("update cms_article set deleted=1 where id=#{value}")
	int deleteById(Integer id);
	
	/**
	 * 修改文章
	 */
	@Update("update cms_article set title=#{title},content=#{content},"
			+ "picture=#{picture},"
			+ "channel_id=#{channelId},category_id=#{categoryId},"
			+ "updated=now() where id=#{id}")
	int update(Article article);
	
	
	/**
	 * 获取需要管理的文章
	 * @return
	 */
	List<Article> listAdmin(@Param("status")Integer status);
	
	/**
	 * 修改文章的状态
	 * @param articleId
	 * @param status
	 * @return
	 */
	@Update("update cms_article set status=#{status},updated=now()"
			+ " where id=#{articleId}")
	int updateStatus(@Param("articleId")Integer articleId,@Param("status")int status);
	
	/**
	 * 修改文章热门状态
	 * @param articleId
	 * @param status
	 * @return
	 */
	@Update("update cms_article set hot=#{status},updated=now() "
			+ "where id=#{articleId}")
	int updateHot(@Param("articleId")Integer articleId,@Param("status")int status);
	
	/**
	 * 根据标签名称获取标签对象
	 * @param tag
	 * @return
	 */
	@Select("select * from cms_term where unique_name=#{value} limit 1")
	Term findTermByName(String term);
	
	/**
	 * 增加Tag实体备案
	 * @param tagBean
	 * @return
	 */
	int addTerm(Term termBean);
	
	/**
	 * 增加数据到文章标签中间表
	 * @param articleId
	 * @param tagId
	 */
	@Insert("insert into cms_article_term values (#{articleId},#{tagId})")
	void addArticleTerm(@Param("articleId")Integer articleId,@Param("tagId")Integer tagId);
	
	/**
	 * 删除中间表
	 * @param articleId
	 * @return
	 */
	@Delete("delete from cms_article_term where aid=#{value}")
	int delTermsByArticleId(Integer articleId);
	
	
	
	
	/**
	 * 根据主题id获取文章列表
	 * @param id
	 * @return
	 */
	@Select("SELECT a.id,a.title,a.created FROM cms_special_article  "
			+ " sa JOIN cms_article  a ON sa.aid=a.id "
			+ " WHERE sa.sid=#{value}")
	List<Article> findBySepecailId(Integer id);

	@Select("SELECT count(1) FROM cms_special_article  "
			+ " sa JOIN cms_article  a ON sa.aid=a.id "
			+ " WHERE sa.sid=#{value}")
	Integer getArticleNum(Integer id);

	/**
	 * @param id
	 * @return
	 */
	@Update("update cms_article set hits = hits + 1 where id=#{id}")
	int increaseHits(Integer id);
	
	
	
}
