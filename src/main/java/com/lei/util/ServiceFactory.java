package com.lei.util;

public class ServiceFactory {
    private ServiceFactory(){};
    public static Object getService(Object obj){
        return (new TransactionInvocationHandler(obj)).getProxy();
    }
}
