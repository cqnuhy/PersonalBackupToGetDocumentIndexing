package com.dl.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.dl.utils.CommonUtil;

/**
 * 文件名称：DlWebService.java<br>
 * 摘要：webservice类，当不需要发布webservcie时可以通过直接创建该类对象进行方法访问<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：丁亮<br>
 * 完成日期：2016年2月23日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：丁亮<br>
 * 完成日期：2016年2月23日<br>
 */
@WebService
public class DlWebService {

	// 内网接口IP和调用接口
	private final String WLAN_SERVICE_IP = "http://168.160.16.250:9999/ConceptService.asmx";
	private final String WORDDETAIL = "/GetWordDetail";
	private final String AUTOINDEXING = "/GetAutoIndexing";

	/**
	 * 概念查询接口
	 * @date 2016年2月23日 下午10:53:20
	 * @author 丁亮
	 * @param word 检索词
	 * @param type  协议
	 * @param action=post、get 当type = soap， action表示 协议版本
	 * @return
	 */
	@WebMethod
	public String getWordDetail(String word,String type,String action) {
		String result = "";
		if (null == word ) {
			result = "检索词不能为空!";
		} else {
			if("".equals(word.trim())){
				result = "检索词不能为空!";
			}else{
				if(type.equals("HTTP")){
					result = getWordDetailByHTTP(word,action);
				}
				if(type.equals("SOAP")){
					result = getWordDetailBySOAP(action,word);
				}
			}
		}
		return result;
	}

	private String getWordDetailBySOAP(String version,String word) {
		String result = "";
		String urlStr = WLAN_SERVICE_IP;
		String soapAction = "http://temp.uri"+WORDDETAIL;
		BufferedReader in = null;
		HttpURLConnection connection;
		try {
//			String xml = DlWebService.class.getClassLoader().getResource("soaprequest1.xml").getFile();
//			String soapStr = CommonUtil.getFileContextStr(xml);
			String soapStr = CommonUtil.getSOAPXML("wordDetail",version,null, word);
			System.out.println(soapStr);
			byte[] b = soapStr.getBytes();
			connection = CommonUtil.getHttpConnection(urlStr,"POST");
			connection.setRequestProperty("Content-Length",String.valueOf(b.length));
			connection.setRequestProperty("Content-Type", "1.1".equals(version)?"text/xml; charset=utf-8":"application/soap+xml");
			if("1.1".equals(version)){
				connection.setRequestProperty("soapActionString", soapAction);
			}
			
			// 写入SOAP内容作为参数
			connection.setDoOutput(true); 
			connection.setDoInput(true);
			// 建立实际的连接
	        connection.connect();
			OutputStream out = connection.getOutputStream();
	        out.write(b);
	        out.close();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line ="";
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	private String getWordDetailByHTTP(String word,String action) {
		String result = "";
		// 调用内网接口
		// 默认HTTP GET请求
		String param = "word=" + word;
		boolean ispost = "POST".equals(action);
		String urlStr = WLAN_SERVICE_IP + WORDDETAIL+"?" + param ;
		if(ispost){
			urlStr = WLAN_SERVICE_IP + WORDDETAIL;
		}
		BufferedReader in = null;
		HttpURLConnection connection;
		try {
			connection = CommonUtil.getHttpConnection(urlStr,action);
			if(ispost){
				connection.setRequestProperty("Content-Length",String.valueOf(param.length()));
			}
			connection.setRequestProperty("Content-Type",ispost?"application/x-www-form-urlencoded":"text/xml; charset=utf-8");
			connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			if(ispost){
				connection.setDoOutput(true);
				connection.setDoInput(true);
			}
			// 建立实际的连接
	        connection.connect();
	        if(ispost){
	        	OutputStream out = connection.getOutputStream();
	        	out.write(param.getBytes("utf-8"));
	        	out.close();
	        }
			// 定义 BufferedReader输入流来读取URL的响应
	        InputStream is = connection.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			String line ="";
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 文摘标引接口
	 * @date 2016年2月23日 下午10:53:52
	 * @author 丁亮
	 * @param title 标题
	 * @param _abstract 摘要
	 * @param type 协议
	 * @param action=post、get 当type = soap， action表示 协议版本
	 * @return
	 */
	@WebMethod
	public String getAutoIndexing(String title, String _abstract,String type,String action) {
		
		String result = "";
		if (type.equals("HTTP")) {
			result = getAutoIndexingByHTTP(title,_abstract, action);
		}
		if (type.equals("SOAP")) {
			result = getAutoIndexingBySOAP(action,title,_abstract);
		}
		return result;
	}
	
	private String getAutoIndexingBySOAP(String version,String title,String word) {
		String result = "";
		String urlStr = WLAN_SERVICE_IP;
		String soapAction = "http://temp.uri"+AUTOINDEXING;
		BufferedReader in = null;
		HttpURLConnection connection;
		try {
//			String xml = "F:\\workspace\\dlwebservice\\src\\com\\dl\\webservice\\soaprequest2.xml";
			String xml = CommonUtil.getSOAPXML("autoIndexing","1.2",null, word);
			String soapStr = CommonUtil.getFileContextStr(xml);
			byte[] b = soapStr.getBytes();
			connection = CommonUtil.getHttpConnection(urlStr,"POST");
			connection.setRequestProperty("Content-Length",String.valueOf(b.length));
			connection.setRequestProperty("Content-Type", "1.1".equals(version)?"text/xml; charset=utf-8":"application/soap+xml");
			if("1.1".equals(version)){
				connection.setRequestProperty("soapActionString", soapAction);
			}
			
			// 写入SOAP内容作为参数
			connection.setDoOutput(true); 
			connection.setDoInput(true);
			// 建立实际的连接
	        connection.connect();
			OutputStream out = connection.getOutputStream();
	        out.write(b);
	        out.close();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line ="";
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	private String getAutoIndexingByHTTP(String title, String _abstract,String action) {
		// 由于参数长度大于250 ，所以默认使用POST 请求
		action = "POST";
		String result ="";
		// 调用内网接口
		String param = "title=" + title+ "&abstract=" + _abstract;
		boolean ispost = "POST".equals(action);
		String urlStr = WLAN_SERVICE_IP + AUTOINDEXING + "?"+param;
		if(ispost){
			urlStr = WLAN_SERVICE_IP+AUTOINDEXING;
		}
		BufferedReader in = null;
		HttpURLConnection connection;
		try {
			connection = CommonUtil.getHttpConnection(urlStr, action);
			connection.setRequestProperty("Content-Length",String.valueOf(param.length()*8));
			connection.setReadTimeout(30000);
			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			connection.setRequestProperty("Content-Type",ispost?"application/x-www-form-urlencoded":"text/xml; charset=utf-8");
			connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			if(ispost){
				connection.setDoOutput(true);
				connection.setDoInput(true);
			}
			// 建立实际的连接
	        connection.connect();
	        if(ispost){
	        	OutputStream out = connection.getOutputStream();
	        	out.write(param.getBytes("utf-8"));
	        	out.close();
	        }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
