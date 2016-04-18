package com.dl.test.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dl.entity.indexing.EnterpriseInfoEntity;
import com.dl.service.EnterpriseInfoService;
import com.dl.utils.CommonUtil;
import com.dl.utils.factory.ServiceFactory;

public class QueryEnterpriseInfo implements Runnable {

	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		EnterpriseInfoService eis = ServiceFactory.getEnterpriseInfoService();
		List<EnterpriseInfoEntity> list = eis.findAll();
		String tipStr = "";
		if(null!=list){
			int totalcount = 0;
			int index  = 1;
			for (EnterpriseInfoEntity ai : list) {
				String result ="";String concepts="";String words="";
				Map<String,Object> map = new HashMap<String, Object>();
				// 设置项目重点数据
				result = new com.dl.webservice.DlWebService().getAutoIndexing("",replaceBlank(ai.getR_DProjectKeypoint()),"HTTP","POST");
				map = CommonUtil.parseXMLByDOM4J(result,"HTTP","autoIndexing");
				concepts = ((List<String>)map.get("IndexingConcepts")).toString();
				ai.setR_DProjectKeypointIC(concepts.substring(1, concepts.length()-1));
				words = ((List<String>)map.get("IndexingWords")).toString();
				ai.setR_DProjectKeypointIW(words.substring(1,words.length()-1));
				// 设置关键技术数据
				result = new com.dl.webservice.DlWebService().getAutoIndexing("",replaceBlank(ai.getCoreTechnology()),"HTTP","POST");
				map = CommonUtil.parseXMLByDOM4J(result,"HTTP","autoIndexing");
				concepts = ((List<String>)map.get("IndexingConcepts")).toString();
				ai.setCoreTechnologyIC(concepts.substring(1, concepts.length()-1));
				words = ((List<String>)map.get("IndexingWords")).toString();
				ai.setCoreTechnologyIW(words.substring(1,words.length()-1));
				// 设置核心竞争力数据
				result = new com.dl.webservice.DlWebService().getAutoIndexing("",replaceBlank(ai.getCoreCompetitive()),"HTTP","POST");
				map = CommonUtil.parseXMLByDOM4J(result,"HTTP","autoIndexing");
				concepts = ((List<String>)map.get("IndexingConcepts")).toString();
				ai.setCoreCompetitiveIC(concepts.substring(1, concepts.length()-1));
				words = ((List<String>)map.get("IndexingWords")).toString();
				ai.setCoreCompetitiveIW(words.substring(1,words.length()-1));
				// 
				result = new com.dl.webservice.DlWebService().getAutoIndexing("",replaceBlank(ai.getCoreProduct()),"HTTP","POST");
				map = CommonUtil.parseXMLByDOM4J(result,"HTTP","autoIndexing");
				concepts = ((List<String>)map.get("IndexingConcepts")).toString();
				ai.setCoreProductIC(concepts.substring(1, concepts.length()-1));
				words = ((List<String>)map.get("IndexingWords")).toString();
				ai.setCoreProductIW(words.substring(1,words.length()-1));
				// 
				result = new com.dl.webservice.DlWebService().getAutoIndexing("",replaceBlank(ai.getBusinessScope()),"HTTP","POST");
				map = CommonUtil.parseXMLByDOM4J(result,"HTTP","autoIndexing");
				concepts = ((List<String>)map.get("IndexingConcepts")).toString();
				ai.setBusinessScopeIC(concepts.substring(1, concepts.length()-1));
				words = ((List<String>)map.get("IndexingWords")).toString();
				ai.setBusinessScopeIW(words.substring(1,words.length()-1));
				try {
					System.out.println("开始保存第 "+ index +"条"+tipStr+"记录");
					int count = eis.saveOrUpate(ai);
					totalcount += count;
					index ++;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println("需要更新记录"+list.size()+"条，成功更新记录"+totalcount+"条");
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
