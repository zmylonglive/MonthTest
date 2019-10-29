/**
 * 
 */
package com.zhaomengyu.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaomengyu.cms.dao.CatMapper;
import com.zhaomengyu.cms.entity.Cat;
import com.zhaomengyu.cms.service.CatService;

/**
 * @author zhaomengyu
 *
 */
@Service
public class CatServiceImpl implements CatService{

	@Autowired
	CatMapper cm;

	/* (non-Javadoc)
	 * @see com.zhaomengyu.cms.service.CatService#getListByChnId(java.lang.Integer)
	 */
	@Override
	public List<Cat> getListByChnId(Integer id) {
		// TODO Auto-generated method stub
		return cm.selectBuChnId(id);
	}
	
	
}
