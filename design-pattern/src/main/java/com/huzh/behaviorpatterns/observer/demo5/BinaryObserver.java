package com.huzh.behaviorpatterns.observer.demo5;

/**
 * @author huzh
 * @description
 * @date 2021.4.16 17:35
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: "
                + Integer.toBinaryString(subject.getState()));
    }
}
