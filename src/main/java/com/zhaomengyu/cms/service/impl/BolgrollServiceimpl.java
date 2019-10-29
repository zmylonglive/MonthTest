/**
 * 
 */
package com.zhaomengyu.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaomengyu.cms.dao.BolgrollMapper;
import com.zhaomengyu.cms.entity.Blogroll;
import com.zhaomengyu.cms.service.BolgrollService;
import com.zhaomengyu.utils.StringUtils;

/**
 * @author zhaomengyu
 *
 */
@Service
public class BolgrollServiceimpl implements BolgrollService{

	
	 @Autowired
	 BolgrollMapper bolgrollMapper;
	 
	 
	
	@Override
	public List<Blogroll> list() {
		// TODO Auto-generated method stub
		return bolgrollMapper.list();
	}

	
	@Override
	public int add(Blogroll blogroll) {
		if(StringUtils.isUrl(blogroll.getUrl())) {
			return bolgrollMapper.add(blogroll);
		}else {
			
			return 0;
		}
		
	}

}
