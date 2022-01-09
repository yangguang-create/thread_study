package com.thread.basic.volatileANDsynchronized;

/**
 *  使用synchronized关键字保证线程安全.
 */
public class ThreadSafeIntegerWithSyn {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {

        this.value = value;
    }
}
