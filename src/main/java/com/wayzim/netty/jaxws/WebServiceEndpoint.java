package com.wayzim.netty.jaxws;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @Author heshouyou
 * @Date Create in 2018/4/1 15:56
 **/
@WebService(serviceName = "WebServiceEndpoint"
        ,endpointInterface = "com.wayzim.netty.jaxws.WebServiceEndpoint"
        ,targetNamespace = "http://jaxws.netty.wayzim.com")
@Component
public class WebServiceEndpoint {

    @WebMethod
    @WebResult(name = "String")
    public String service(@WebParam(name = "request") String request){
        System.out.println("进入webservice的service方法，请求报文：" + request);
        return "处理成功";
    }
}
