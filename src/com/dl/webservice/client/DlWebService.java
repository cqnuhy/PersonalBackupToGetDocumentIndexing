
package com.dl.webservice.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DlWebService", targetNamespace = "http://webservice.dl.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DlWebService {


    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAutoIndexing", targetNamespace = "http://webservice.dl.com/", className = "com.dl.webservice.client.GetAutoIndexing")
    @ResponseWrapper(localName = "getAutoIndexingResponse", targetNamespace = "http://webservice.dl.com/", className = "com.dl.webservice.client.GetAutoIndexingResponse")
    @Action(input = "http://webservice.dl.com/DlWebService/getAutoIndexingRequest", output = "http://webservice.dl.com/DlWebService/getAutoIndexingResponse")
    public String getAutoIndexing(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getWordDetail", targetNamespace = "http://webservice.dl.com/", className = "com.dl.webservice.client.GetWordDetail")
    @ResponseWrapper(localName = "getWordDetailResponse", targetNamespace = "http://webservice.dl.com/", className = "com.dl.webservice.client.GetWordDetailResponse")
    @Action(input = "http://webservice.dl.com/DlWebService/getWordDetailRequest", output = "http://webservice.dl.com/DlWebService/getWordDetailResponse")
    public String getWordDetail(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

}
