package com.tao.tools;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 */
public class ObserverTest implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("执行添加方法！");
    }
}
