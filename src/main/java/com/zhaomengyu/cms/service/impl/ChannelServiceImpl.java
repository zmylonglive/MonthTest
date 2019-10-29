/**
 * 
 */
package com.zhaomengyu.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaomengyu.cms.dao.ChannelMapper;
import com.zhaomengyu.cms.entity.Channel;
import com.zhaomengyu.cms.service.ChannelService;

/**
 * @author zhaomengyu
 *
 */
@Service
public class ChannelServiceImpl implements ChannelService{

	@Autowired
	ChannelMapper cm;
	
	/* (non-Javadoc)
	 * @see com.zhaomengyu.cms.service.ChannelService#getAllChnls()
	 */
	@Override
	public List<Channel> getAllChnls() {
		// TODO Auto-generated method stub
		return cm.listAll();
	}

}
