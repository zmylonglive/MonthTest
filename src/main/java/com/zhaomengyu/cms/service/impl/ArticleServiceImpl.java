/**
 * 
 */
package com.zhaomengyu.cms.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaomengyu.cms.dao.ArticleMapper;
import com.zhaomengyu.cms.entity.Article;
import com.zhaomengyu.cms.entity.Term;
import com.zhaomengyu.cms.service.ArticleService;
import com.zhaomengyu.utils.StringUtils;

/**
 * @author zhaomengyu
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	ArticleMapper am;
	
	
	@Override
	public PageInfo<Article> list(Integer chnId, Integer catId, Integer page) {
		// 设置页码值
		PageHelper.startPage(page,10); 
		//查询指定页码数据 并返回页面信息
		return new PageInfo(am.list(chnId, catId));
	}

	
	@Override
	public PageInfo<Article> hostList(Integer page) {
		//设置页码
		PageHelper.startPage(page,10);
		//查询指定页码数据 并返回页面信息
		return new PageInfo(am.listHot());
	}

	
	@Override
	public List<Article> last(int sum) {
		
		return am.listLast(sum);
	}

	
	@Override
	public Article findById(Integer articleId) {
		// TODO Auto-generated method stub
		return am.findById(articleId);
	}

	
	@Override
	public int add(Article article) throws Exception {
		// TODO Auto-generated method stub
		int result = am.add(article);
		processTerm(article);
		return result;
	}


	/**
	 * 处理文章的标签
	 * @param article
	 * @throws Exception 
	 */
	private void processTerm(Article article) throws Exception {
		
		if(article.getTerms() == null) {
			return ;
		}
		String[] terms = article.getTerms().split(",");
		for (String term : terms) {
			//判断这个tag在数据库当中是否存在
			Term termBean = am.findTermByName(term);
			if(termBean == null) {
				String uniqueTerm = StringUtils.toUniqueTerm(term);
				termBean = new Term(term,uniqueTerm);
				am.addTerm(termBean);
			}
			//插入中间表
			try {
				
				am.addArticleTerm(article.getId(), termBean.getId());
			}catch (Exception e) {
				System.out.println("插入失败");
			}
		}
	}
	
	
	public int update(Article article) throws Exception {
		int result = am.update(article);
		//删除中间表中的
		am.delTermsByArticleId(article.getId());
		processTerm(article);
		return result;
	}
	
	@Override
	public PageInfo<Article> listArticleByUserId(Integer userId, Integer page) {
		PageHelper.startPage(page,10);
//		List<Article> listByUserId = am.listByUserId(userId);
//		for (Article article : listByUserId) {
//			System.out.println(article);
//		}
		return new PageInfo<Article>(am.listByUserId(userId));
	}


	
	@Override
	public int remove(Integer id) {
		// TODO Auto-generated method stub
		int result = am.deleteById(id);
		//删除中间表
		am.delTermsByArticleId(id);
		return result;
	}


	
	

	
	@Override
	public PageInfo<Article> getAdminArticles(Integer page,Integer status) {
		System.out.println("========= page is " + page);
		PageHelper.startPage(page,10);
		
		return new PageInfo<Article>(am.listAdmin(status));
		
	}


	

	
	@Override
	public int updateStatus(Integer articleId, int status) {
		// TODO Auto-generated method stub
		return am.updateStatus(articleId, status);
	}


	
	@Override
	public int updateHot(Integer articleId, int status) {
		// TODO Auto-generated method stub
		return am.updateHot(articleId, status);
	}


	
	@Override
	public int addHits(Integer id) {
		// TODO Auto-generated method stub
		return am.increaseHits(id);
	}

}
