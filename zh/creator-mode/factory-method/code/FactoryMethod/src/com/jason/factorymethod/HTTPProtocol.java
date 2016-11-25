package com.jason.factorymethod;

/**
 * Created by jsson on 16/11/25.
 */
public class HTTPProtocol extends Protocol {

    @Override
    void performRequest() {
        System.out.println("发送 http 数据");
    }
}
