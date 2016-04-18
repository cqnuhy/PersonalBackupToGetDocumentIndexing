package com.dl.test.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dl.entity.indexing.LEDEntity;
import com.dl.service.LEDService;
import com.dl.utils.CommonUtil;
import com.dl.utils.factory.ServiceFactory;

/**
* Copyright (c) 2016,HUYI<br>
* All rights reserved.<br>
* 
* 文件名称：QuerySSLED.java<br>
* 摘要：用于获取深市LED专利标引<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：HUYI<br>
* 完成日期：2016-3-11<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：HUYI<br>
* 完成日期：2016-3-11<br>
 */
public class QuerySSLED  {
	
	private String queryType;
	
	public QuerySSLED(String queryType) {
		this.queryType=queryType;
	}
	
	@SuppressWarnings("unchecked")
	public void runing() {
		LEDService eis = ServiceFactory.getLEDService();
		List<LEDEntity> list = eis.findAll(queryType);
		List<Integer> faileds = new ArrayList<Integer>();
		boolean f = queryType.equals("SSLED");
		String tipStr = f?"深市LED":"沪市LED";
		if(null!=list){
			int index = 1,total=0;
			long start = System.currentTimeMillis();
			for (LEDEntity ai : list) {
				Map<String,Object> map = new HashMap<String, Object>();
				String title = f?ai.getInventionName():ai.getPatentName();
				String _abstract = ai.get_abstract();
				// 设置项目重点数据
				String result = "";
				try {
					result = new com.dl.webservice.DlWebService().getAutoIndexing(title,replaceBlank(_abstract),"HTTP","POST");
				} catch (Exception e) {
					e.printStackTrace();
				}
				map = CommonUtil.parseXMLByDOM4J(result,"HTTP","autoIndexing");
				String concepts = ((List<String>)map.get("IndexingConcepts")).toString();
				ai.setIndexingConcepts(concepts.substring(1, concepts.length()-1));
				String words = ((List<String>)map.get("IndexingWords")).toString();
				ai.setIndexingWords(words.substring(1,words.length()-1));
//				if(index%10==0){
				System.out.println(tipStr+"请求更新第"+index+"条标引结果...");
					try {
//						// list下标是从0开始的，十条数据既是0-9，10-19
//						int count = eis.saveOrupate(list.subList(index-10, index-1),queryType);
						int count = eis.saveOrupate(ai, queryType);
						total += count;
					} catch (Exception e) {
						faileds.add(index);
					}
//				}
				index++;
			}
			System.out.println(tipStr+"结束请求标引结果需要更新"+list.size()+"实际更新"+total+",耗时："+(System.currentTimeMillis()-start)/60000+"分钟");
			for (Integer i : faileds) {
				System.out.println("第"+i+"条数据更新失败");
			}
		}
	}
	
	private String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

}
