package com.jason.abstractfactory;

/**
 * Created by jsson on 16/12/5.
 */
public class Store {
    public static void main(String[] args) {
        AbsFactory factory = new ChineseFactory();
        factory.createDrink();
        factory.createhamburg();

        factory = new NYFactory();
        factory.createDrink();
        factory.createhamburg();
    }
}
