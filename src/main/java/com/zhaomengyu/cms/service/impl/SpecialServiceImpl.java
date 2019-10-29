/**
 * 
 */
package com.zhaomengyu.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaomengyu.cms.dao.ArticleMapper;
import com.zhaomengyu.cms.dao.SpecialMapper;
import com.zhaomengyu.cms.entity.Special;
import com.zhaomengyu.cms.service.SpecialService;

/**
 * @author zhaomengyu
 *
 */
@Service
public class SpecialServiceImpl implements SpecialService{

	
	@Autowired
	SpecialMapper specialMapper;
	
	@Autowired
	ArticleMapper articleMapper;
	
	
	
	
	@Override
	public List<Special> list() {
		List<Special> list = specialMapper.list();
		for (Special special : list) {
			special.setArticleNum(articleMapper.getArticleNum(special.getId()));
		}
		return list;
	}

	
	@Override
	public int add(Special special) {
		// TODO Auto-generated method stub
		return specialMapper.add(special);
	}

	
	@Override
	public Special findById(Integer id) {
		Special special = specialMapper.findById(id);
		special.setArticleList(articleMapper.findBySepecailId(id));
		return special;
	}

	
	@Override
	public int addArticle(Integer specId, Integer articleId) {
		// TODO Auto-generated method stub
		return specialMapper.addArticle(specId, articleId);
	}

	
	@Override
	public int removeArticle(Integer specId, Integer articleId) {
		// TODO Auto-generated method stub
		return specialMapper.removeArticle(specId, articleId);
	}

	
	@Override
	public int update(Special special) {
		// TODO Auto-generated method stub
		return specialMapper.update(special);
	}

}
