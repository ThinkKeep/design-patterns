package com.jason.abstractfactory;

/**
 * Created by jsson on 16/12/5.
 */
public class ChineseFactory implements AbsFactory {
    @Override
    public Hamburg createhamburg() {
        return new ChineseBigHamburg();
    }

    @Override
    public Drink createDrink() {
        return new ChineseCOCO();
    }
}
