/**
 * 
 */
package com.zhaomengyu.cms.comon;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhaomengyu
 *
 */
public class CmsControllerAdvice {

	
	@ResponseBody
	@ExceptionHandler(value = CmsException.class)
	//使用@ExceptionHandler修饰后会作用在所有的@RequestMapping上
	public ResultMsg myErrorHandler(CmsException ex) {
		return new ResultMsg(ex.hashCode(), ex.getMessage(), "");
	}
	
}
