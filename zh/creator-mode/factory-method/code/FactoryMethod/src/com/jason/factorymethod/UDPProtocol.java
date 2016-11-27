package com.jason.factorymethod;

/**
 * Created by jsson on 16/11/25.
 */
public class UDPProtocol extends Protocol {

    @Override
    void performRequest() {
        System.out.println("发送 UDP 数据");
    }
}
