package com.jason.factorymethod;

/**
 * Created by jsson on 16/11/25.
 */
public class TCPFactory extends ProtocolFactory {
    @Override
    public Protocol createProtocol() {
        return new TCPProtocol();
    }
}
