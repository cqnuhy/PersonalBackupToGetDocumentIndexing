package com.dl.webservice;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Endpoint;

import com.dl.test.MainTest;
import com.dl.utils.Const;


/**
* 文件名称：PushDlWebService.java<br>
* 摘要：用于发布DlWebService,初始化配置文件<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：丁亮<br>
* 完成日期：2016年2月23日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：丁亮<br>
* 完成日期：2016年2月23日<br>
 */
public class PushDlWebServiceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String PUSH_WEBSERVICE_URL = "http://127.0.0.1:8888/dlws";

	@Override 
    public void init(ServletConfig servletConfig) throws ServletException { 
            super.init(servletConfig); 
            try {
            	// 初始化配置
				Const constant = new Const(System.getProperty("catalina.base")+"\\webapps\\config.properties");
				// 发布webservice
				if(null!=constant){
					this.PUSH_WEBSERVICE_URL = Const.applicationConst.getProperty("PUSH_WEBSERVICE_URL");
					System.out.println("准备启动DlWebService服务："+PUSH_WEBSERVICE_URL); 
					Endpoint.publish(PUSH_WEBSERVICE_URL, new DlWebService()); 
					System.out.println("已成功启动WebService服务："+PUSH_WEBSERVICE_URL); 
				}
				// 执行测试
				MainTest.doTest("web");
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

    } 

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
            System.out.println("此Servlet不处理任何业务逻辑，只用于发布一个WebService服务："+PUSH_WEBSERVICE_URL); 
    } 
}
