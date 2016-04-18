package com.dl.test.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dl.entity.indexing.MultiIndexingEntity;
import com.dl.service.MultiIndexingService;
import com.dl.test.MainClient;
import com.dl.utils.CommonUtil;
import com.dl.utils.factory.ServiceFactory;

public class QueryMulitEntity implements Runnable {
	
	private int menu=0;
	
	public QueryMulitEntity(int menu) {
		this.menu=menu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {

		List<MultiIndexingEntity> list = new ArrayList<MultiIndexingEntity>();
		MultiIndexingService mis = ServiceFactory.getMultiIndexingService();
		Map<String,String> pars = new HashMap<String, String>();
		String tipStr = "";
		switch (menu) {
		case MainClient.MENU1:
			tipStr =  "联营企业";
			pars.put("menu", String.valueOf(MainClient.MENU1));
			list = mis.findJointEnterpriseData(pars);
			break;
		case MainClient.MENU2:
			tipStr =  "母公司";
			pars.put("menu", String.valueOf(MainClient.MENU2));
			list  = mis.findParentCompanyData(pars);
			break;
		case MainClient.MENU4:
			tipStr =  "研发项目重点";
			pars.put("menu", String.valueOf(MainClient.MENU4));
			list  = mis.findKeypointProjectData(pars);
			break;
		case MainClient.MENU5:
			tipStr =  "子公司";
			pars.put("menu", String.valueOf(MainClient.MENU5));
			list  = mis.findSubCompanyData(pars);
			break;
		default:
			break;
		}
		if(null!=list){
			int totalcount = 0;
			int index  = 1;
			for (MultiIndexingEntity ai : list) {
				String result = new com.dl.webservice.DlWebService()
								.getAutoIndexing("",(MainClient.MENU4==menu?replaceBlank(ai.getName()):replaceBlank(ai.getBusiness())),"HTTP","POST");
				Map<String,Object> map = CommonUtil.parseXMLByDOM4J(result,"HTTP","autoIndexing");
				String concepts = ((List<String>)map.get("IndexingConcepts")).toString();
				ai.setIndexingConcepts(concepts.substring(1, concepts.length()-1));
				String words = ((List<String>)map.get("IndexingWords")).toString();
				ai.setIndexingWords(words.substring(1,words.length()-1));
				try {
					System.out.println("开始保存第 "+ index +"条"+tipStr+"记录");
					int count = mis.saveOrUpate(ai,menu);
					totalcount += count;
					index ++;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println("需要更新记录"+list.size()+"条，成功更新记录"+totalcount+"条");
		}
	}
	

	private  String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
