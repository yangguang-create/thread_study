package com.thread.basic.volatileANDsynchronized;

/**
 * 使用volatile关键字保证线程安全.
 *  但是volatile只保证可见性，不保证原子性。和synchronized并不是完全等价.
 *
 */
public class ThreadSafeIntegerWithVolatile {
    private volatile int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {

        this.value = value;
    }
}
