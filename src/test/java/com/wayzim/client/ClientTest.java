package com.wayzim.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ${DESCRIPTION}
 *
 * @author 14684
 * @create 2020-12-03 13:47
 */
public class ClientTest {

    private static Client client;
    private static final ReentrantLock reentrantLock = new ReentrantLock();

    //  获取连接
    private static Client createClient(String remoteUrl) {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        return dcf.createClient(remoteUrl);
    }

    //  执行请求全过程
    public static String execute(String remoteUrl, String methodName, Object... args) throws Exception {
        try {
            reentrantLock.lock();
            if (client == null) {
                client = createClient(remoteUrl);
            }
        } catch (Exception e) {
            reentrantLock.unlock();
        }
        if (client == null) {
            throw new RuntimeException("mockWcs服务连接失败...");
        }
        Object[] service = client.invoke(methodName, args);

        String res = null;
        if (service == null || service.length == 0) return null;
        if (service[0] != null) {
            //  输出调用结果
            res = service[0].toString();
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        String remoteUrl = "http://localhost:20003/WebServiceEndpoint?wsdl";
        String methodName = "service";
        String message = "hello";
        String res = execute(remoteUrl, methodName, message);
        System.err.println(res);
    }
}
