package com.dl.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.xml.sax.SAXException;

import com.dl.test.query.QueryEnterpriseInfo;
import com.dl.test.query.QueryFromExcel;
import com.dl.test.query.QueryFromExcel2;
import com.dl.test.query.QueryMulitEntity;
import com.dl.test.query.QuerySSLED;
import com.dl.test.query.ReadBigExcel;
import com.dl.utils.Const;

/**
* Copyright (c) 2016,HUYI<br>
* All rights reserved.<br>
* 
* 文件名称：MainClient.java<br>
* 摘要：程序执行入口类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：HUYI<br>
* 完成日期：2016-3-9<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：HUYI<br>
* 完成日期：2016-3-9<br>
 */
public class MainClient {
	
//	private static final String RUN_TYPE = "jar";
	public static final int MENU1 = 1;
	public static final int MENU2 = 2;
	public static final int MENU3 = 3;
	public static final int MENU4 = 4;
	public static final int MENU5 = 5;
	public static final int MENU6 = 6;
	public static final int MENU7 = 7;
	public static final int MENU8 = 8;
	public static final int MENU9 = 9;
	public static final int MENU0 = 0;
	
	public static void main(String[] args) {
		// 初始化配置
		try {
			new Const(System.getProperty("user.dir")+"\\config.properties");
			System.out.println("当前目录："+System.getProperty("user.dir"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			showMenu();
			selectMenu(new Scanner(System.in));
		}
		
	}
	
	private static void showMenu(){
		System.out.println("请选择启动方式：");
		System.out.println(MENU1+"、获取联营企业标引");
		System.out.println(MENU2+"、获取母公司标引");
		System.out.println(MENU3+"、获取企业基本信息标引");
		System.out.println(MENU4+"、获取重点项目标引");
		System.out.println(MENU5+"、获取子公司标引");
		System.out.println(MENU6+"、获取沪市LED专利标引");
		System.out.println(MENU7+"、获取深市LED专利标引");
		System.out.println(MENU8+"、风险标引");
//		System.out.println(MENU9+"、清洗-万得EXCEL标引");
		System.out.println(MENU9+"、DL标引EXCEL");
		System.out.println(MENU0+"、退出程序");
	}

	private static void selectMenu(Scanner sc) {
		int menu = sc.nextInt();
		if(menu==MENU3){
			new Thread(new QueryEnterpriseInfo()).start();
		}else if(menu==MENU7){
			new QuerySSLED("SSLED").runing();
		}else if(menu==MENU6){
			new QuerySSLED("HSLED").runing();
		}else if(menu==MENU8){
			new QueryFromExcel("C:\\Users\\pc\\Desktop\\风险.xlsx");
		}else if(menu==MENU9){
//			new QueryFromExcel2("C:\\Users\\Administrator\\Desktop\\产品名称清洗_万得.xlsx");
//			new QueryFromExcel2("C:\\Users\\Administrator\\Desktop\\output5w.xlsx");
			try {
				new ReadBigExcel("C:\\Users\\Administrator\\Desktop\\output5w.xlsx");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (OpenXML4JException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
//			new QueryFromExcel2("C:\\Users\\Administrator\\Desktop\\给丁亮标注.xlsx");
		}else{
			new Thread(new QueryMulitEntity(menu)).start();
		}
	}
}
