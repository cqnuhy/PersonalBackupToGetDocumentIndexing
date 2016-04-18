package com.dl.utils.factory;

import com.dl.service.AutoIndexingService;
import com.dl.service.EnterpriseInfoService;
import com.dl.service.LEDService;
import com.dl.service.MultiIndexingService;
import com.dl.service.WordDetailService;
import com.dl.service.impl.AutoIndexingServiceImpl;
import com.dl.service.impl.EnterpriseInfoServiceImpl;
import com.dl.service.impl.LEDServiceImpl;
import com.dl.service.impl.MultiIndexingServiceImpl;
import com.dl.service.impl.WordDetailServiceImpl;


/**
 * 
 * 文件名称：ServiceFactory.java<br>
 * 摘要：在servlet（Controller）中解耦合业务层调用<br>
 * -------------------------------------------------------<br>
 * 作者：胡毅<br>
 * 完成日期：2015年6月29日<br>
 */
public class ServiceFactory {

	public static WordDetailService getWordDetailService(){
		return new WordDetailServiceImpl();
	}

	public static AutoIndexingService getAutoIndexingService() {
		return new AutoIndexingServiceImpl();
	}

	public static MultiIndexingService getMultiIndexingService() {
		return new MultiIndexingServiceImpl();
	}

	public static EnterpriseInfoService getEnterpriseInfoService() {
		return new EnterpriseInfoServiceImpl();
	}

	public static LEDService getLEDService() {
		return new LEDServiceImpl();
	}
}
