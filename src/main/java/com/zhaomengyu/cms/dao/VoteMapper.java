/**
 * 
 */
package com.zhaomengyu.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zhaomengyu.cms.entity.Voted;
import com.zhaomengyu.cms.entity.VotedBig;

/**
 * @author zhaomengyu
 *
 */
public interface VoteMapper {

	/**
	 * @return
	 */
	@Select("select * from cms_article_vote order by id desc limit 10")
	List<VotedBig> list();

	/**
	 * @param votebig
	 * @return
	 */
	@Insert("INSERT INTO `cms`.`cms_article_vote` (content,title) VALUES (#{content},#{title})")
	int publish(VotedBig votebig);

	/**
	 * @param arId
	 * @return
	 */
	 @Select("select * from cms_article_vote where id=#{value}")
	VotedBig findById(int arId);

	/**
	 * @param arId
	 * @return
	 */
	 @Select("select  count(1) as voteNum , `option` as optionKey  "
	            + " FROM cms_vote  "
	            + " where article_id=#{value} "
	            + " GROUP BY `option`")
	List<Voted> getVoteStatics(int arId);

	/**
	 * @param articleId
	 * @param option
	 * @return
	 */
	@Insert("insert into cms_vote(article_id,`option`) "
            + "values(#{articleId},#{option})")
	int vote(@Param("articleId")Integer articleId, @Param("option")Character option);

}
