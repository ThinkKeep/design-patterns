package com.jason.factorymethod;

/**
 * Created by jsson on 16/11/25.
 */
public class Client {
    public static void main(String[] args) {
        //Http
        ProtocolFactory factory = new HTTPFactory();
        Protocol protocol = factory.createProtocol();
        protocol.performRequest();

        //UDP
        factory = new UDPFactory();
        protocol = factory.createProtocol();
        protocol.performRequest();

        //TCP
        factory = new TCPFactory();
        protocol = factory.createProtocol();
        protocol.performRequest();

    }
}
