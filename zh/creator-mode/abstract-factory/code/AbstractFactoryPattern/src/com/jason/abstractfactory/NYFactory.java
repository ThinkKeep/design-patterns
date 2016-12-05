package com.jason.abstractfactory;

/**
 * Created by jsson on 16/2/3.
 */
public class NYFactory implements AbsFactory {
    @Override
    public Hamburg createhamburg() {
        return new NYBigHamburg();
    }

    @Override
    public Drink createDrink() {
        return new NYCOCO();
    }
}
