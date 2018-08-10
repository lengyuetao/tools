package com.tao.tools;


import java.util.Observable;

/**
 * 被观察者
 */
public class MyObservable extends Observable {

    public MyObservable(){
        addObserver(new ObserverTest());
    }


    public void add(){
        System.out.println("******************");
        setChanged();
        notifyObservers();
    }
}
