package com.dl.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.dl.entity.AutoIndexing;
import com.dl.entity.WordDetail;
import com.dl.service.AutoIndexingService;
import com.dl.service.WordDetailService;
import com.dl.utils.CommonUtil;
import com.dl.utils.Const;
import com.dl.utils.factory.ServiceFactory;
import com.dl.webservice.client.DlWebService;
import com.dl.webservice.client.DlWebServiceImpl;

/**
* 文件名称：MainTest.java<br>
* 摘要：调用测试类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：丁亮<br>
* 完成日期：2016年2月24日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：HUYI<br>
* 完成日期：2016年3月9日<br>
 */
public class MainTest {
	
	private static final String RUN_TYPE = "jar";
	
	public static void main(String[] args) {
		// 初始化配置
		try {
			new Const(System.getProperty("user.dir")+"\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("请选择启动方式：");
		System.out.println("1、从数据库读取参数启动");
		System.out.println("2、从文件中读取参数启动");
		System.out.println("0、退出应用程序");
		selectMenu(new Scanner(System.in));
	}

	private static void selectMenu(Scanner sc) {
		int num = sc.nextInt();
		try {
			if(num>2){
				System.out.println("请输入提示的选项数字！");
				selectMenu(new Scanner(System.in));
			}else{
				switch (num) {
				case 0:
					sc.close();
					System.exit(0);
					break;
				case 1:
					sc.close();
					doTest(RUN_TYPE);
					break;
				case 2:
					Scanner scc = new Scanner(System.in);
					String floder1 = selectFloder(scc,1);
					String floder2 = selectFloder(scc,2);
					scc.close();
					sc.close();
					doTest(RUN_TYPE,new String[]{floder1,floder2});
					break;
					
				default:
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println("请输入提示的选项数字！");
			selectMenu(sc);
		}
	}

	private static String selectFloder(Scanner scc,int index) {
		if(index==1){
			System.out.println("请输入包含概念查询参数文件的文件夹路径：");
		}
		if(index==2){
			System.out.println("请输入包含摘要标引参数文件的文件夹路径：");
		}
		String filePath = scc.nextLine();
		if(filePath.equals("0")){
			scc.close();
			System.exit(0);
		}
		if(filePath.endsWith(":")){
			filePath+="\\";
		}
		File floder = new File(filePath);
		if(floder.isDirectory()){
			return filePath;
		}else{
			System.out.println("无法找到输入的文件夹，请重新输入：");
			selectFloder(scc,index);
		}
		return null;
	}

	public static void doTest(String type,String... filePath) {
		System.out.println("开始执行采集程序...");
		if(type.equals("jar")){
			testGetWordDetail(null,filePath);	
			testGetAutoIndexing(null,filePath);
		}else{
			DlWebService dlws = new DlWebServiceImpl().getDlWebServicePort();
			testGetWordDetail(dlws,filePath);	
			testGetAutoIndexing(dlws,filePath);
		}
	}

	@SuppressWarnings("unchecked")
	private static void testGetAutoIndexing(DlWebService dlws,String... filePath) {
		List<AutoIndexing> list = new ArrayList<AutoIndexing>();
		AutoIndexingService ais = ServiceFactory.getAutoIndexingService();
		if(null == filePath || filePath.length==0){
			list = ais.findAll();
		}else{
			File floder  = new File(filePath[1]);
			File[] files = floder.listFiles();
			for (File file : files) {
				if(file.isFile()){
					String context = CommonUtil.getFileContextStr(file.getAbsolutePath());
					if(context.length()>0){
						AutoIndexing ai = new AutoIndexing();
						ai.setId(System.currentTimeMillis()+""+((int)(Math.random()*9999)));
						System.out.println(System.currentTimeMillis()+"|"+(int)(Math.random()*9999));
						ai.setTitle("");
						ai.set_abstract(context);
						list.add(ai);
					}
				}
			}
		}
		if(null!=list){
			int totalcount = 0;
			for (AutoIndexing ai : list) {
//				String result = dlws.getAutoIndexing(ai.getTitle(),ai.get_abstract(),"HTTP","POST");
				String result = new com.dl.webservice.DlWebService().getAutoIndexing(ai.getTitle(),ai.get_abstract(),"HTTP","POST");
				Map<String,Object> map = CommonUtil.parseXMLByDOM4J(result,"HTTP","autoIndexing");
				ai.setResult(map.get("Result").equals("true")?1:0);
				ai.setMessage(map.get("Message")+"");
				String concepts = ((List<String>)map.get("IndexingConcepts")).toString();
				ai.setIndexingConcepts(concepts.substring(1, concepts.length()-1));
				String words = ((List<String>)map.get("IndexingWords")).toString();
				ai.setIndexingWords(words.substring(1,words.length()-1));
				String numbers = ((List<String>)map.get("ClcNumbers")).toString();
				ai.setClcNumbers(numbers.substring(1,numbers.length()-1));
				try {
					int count = ais.saveOrUpate(ai);
					totalcount += count;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println("需要更新记录"+list.size()+"条，成功更新记录"+totalcount+"条");
		}
	}

	private static void testGetWordDetail(DlWebService dlws,String... filePath) {
		List<WordDetail> list = new ArrayList<WordDetail>();
		WordDetailService wds = ServiceFactory.getWordDetailService();
		if(null == filePath|| filePath.length==0){
			list = wds.findAll();
		}else{
			File floder  = new File(filePath[0]);
			File[] files = floder.listFiles();
			for (File file : files) {
				if(file.isFile()){
					String context = CommonUtil.getFileContextStr(file.getAbsolutePath());
					if(context.length()>0){
						WordDetail wd = new WordDetail();
						wd.setId(System.currentTimeMillis()+""+((int)(Math.random()*9999)));
						wd.setWord(context);
						list.add(wd);
					}
				}
			}
		}
		if(null!=list){
			int totalcount = 0;
			for (WordDetail wd : list) {
				// 调用后台接口，传入参数
//				String result = dlws.getWordDetail(wd.getWord(),"HTTP","POST");
				String result = new com.dl.webservice.DlWebService().getWordDetail(wd.getWord(),"HTTP","POST");
				// TODO 若要使用SOAP 需要CommonUtil中添加解析 SOAP返回结果的方法
//				String result = new com.dl.webservice.DlWebService().getWordDetail(wd.getWord(),"SOAP","12");
				Map<String,Object> map = CommonUtil.parseXMLByDOM4J(result,"HTTP","wordDetail");
				wd.setWordF(map.get("WordF")+"");
				wd.setWordC(map.get("WordC")+"");
				wd.setWordD(map.get("WordD")+"");
				wd.setWordEnglish(map.get("WordEnglish")+"");
				try {
					int count = wds.saveOrUpate(wd);
					totalcount += count;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println("需要更新记录"+list.size()+"条，成功更新记录"+totalcount+"条");
		}
		
	}

}
