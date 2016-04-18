
package com.dl.webservice.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dl.webservice.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetWordDetail_QNAME = new QName("http://webservice.dl.com/", "getWordDetail");
    private final static QName _GetAutoIndexingResponse_QNAME = new QName("http://webservice.dl.com/", "getAutoIndexingResponse");
    private final static QName _GetWordDetailResponse_QNAME = new QName("http://webservice.dl.com/", "getWordDetailResponse");
    private final static QName _GetAutoIndexing_QNAME = new QName("http://webservice.dl.com/", "getAutoIndexing");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dl.webservice.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetWordDetail }
     * 
     */
    public GetWordDetail createGetWordDetail() {
        return new GetWordDetail();
    }

    /**
     * Create an instance of {@link GetAutoIndexingResponse }
     * 
     */
    public GetAutoIndexingResponse createGetAutoIndexingResponse() {
        return new GetAutoIndexingResponse();
    }

    /**
     * Create an instance of {@link GetWordDetailResponse }
     * 
     */
    public GetWordDetailResponse createGetWordDetailResponse() {
        return new GetWordDetailResponse();
    }

    /**
     * Create an instance of {@link GetAutoIndexing }
     * 
     */
    public GetAutoIndexing createGetAutoIndexing() {
        return new GetAutoIndexing();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWordDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.dl.com/", name = "getWordDetail")
    public JAXBElement<GetWordDetail> createGetWordDetail(GetWordDetail value) {
        return new JAXBElement<GetWordDetail>(_GetWordDetail_QNAME, GetWordDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAutoIndexingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.dl.com/", name = "getAutoIndexingResponse")
    public JAXBElement<GetAutoIndexingResponse> createGetAutoIndexingResponse(GetAutoIndexingResponse value) {
        return new JAXBElement<GetAutoIndexingResponse>(_GetAutoIndexingResponse_QNAME, GetAutoIndexingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWordDetailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.dl.com/", name = "getWordDetailResponse")
    public JAXBElement<GetWordDetailResponse> createGetWordDetailResponse(GetWordDetailResponse value) {
        return new JAXBElement<GetWordDetailResponse>(_GetWordDetailResponse_QNAME, GetWordDetailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAutoIndexing }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.dl.com/", name = "getAutoIndexing")
    public JAXBElement<GetAutoIndexing> createGetAutoIndexing(GetAutoIndexing value) {
        return new JAXBElement<GetAutoIndexing>(_GetAutoIndexing_QNAME, GetAutoIndexing.class, null, value);
    }

}
