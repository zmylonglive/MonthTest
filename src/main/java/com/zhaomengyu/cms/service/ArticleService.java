/**
 * 
 */
package com.zhaomengyu.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhaomengyu.cms.entity.Article;

/**
 * @author zhaomengyu
 *
 */
public interface ArticleService {
	
	/**
	 * 
	 * @param chnId 频道id
	 * @param catId 分类id
	 * @param page 页码
	 * @return
	 */

	PageInfo<Article> list(Integer chnId,Integer catId,Integer page);
	
	/**
	 * page
	 */
	
	PageInfo<Article> hostList(Integer page);
	/**
	 * 获取最新文章
	 */
	
	List<Article> last(int sum);
	
	/**
	 * 根据文章的主键获取文章的内容
	 */
	
	Article findById(Integer articleId);
	
	int add(Article article) throws Exception;
	
	
	/**
	 * 根据用户id查找文章列表
	 */
	PageInfo<Article> listArticleByUserId(Integer id,Integer page);
	
	
	/**
	 * 删除文章
	 */
	int remove(Integer id);
	
	
	/**
	 * 修改文章
	 * @throws Exception 
	 */
	int update(Article article) throws Exception;
	
	/**
	 * 
	 * @param page 页码
	 * @param status 审核的状态
	 * @return
	 */
	PageInfo<Article> getAdminArticles(Integer page,Integer status);
	
	
	/**
	 * 审核文章
	 * @param articleId
	 * @param status 要审核的状态
	 * @return
	 */
	int updateStatus(Integer articleId,int status);
	
	
	/**
	 * 修改热门
	 * @param articleId
	 * @param status
	 * @return
	 */
	int updateHot(Integer articleId,int status);

	/**
	 * 增加文章的点击次数
	 * @param id
	 * @return
	 */
	int addHits(Integer id);
	
}
