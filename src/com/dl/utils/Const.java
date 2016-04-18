package com.dl.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
* 文件名称：Const.java<br>
* 摘要：常量类，读取配置文件中的配置信息<br>
* -------------------------------------------------------<br>
* 作者：胡毅<br>
* 完成日期：2015年7月23日<br>
 */
public class Const {

	public static Properties applicationConst = new Properties();
	public static String SERVER_IP = Const.applicationConst.getProperty("SERVER_IP","127.0.0.1");
	
	// 加载配置文件中的变量
	public Const(String path) throws FileNotFoundException, IOException{
		applicationConst.load(new FileInputStream(path));
	}
}
