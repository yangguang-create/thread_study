package com.thread.basic.volatileANDsynchronized;

/**
 * 这个类中的value是线程不安全的.
 */
public class ThreadNotSafeInteger {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
