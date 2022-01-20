package com.thread.advanced.reentrantlocklist;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用ReentrantReadWriteLock实现一个线程安全的list.
 * 如上代码，通过操作array元素前进行加锁保证同一时间只有一个线程可以对array数组进行修改。
 * 但是也只能有一个线程对array元素进行访问。
 *
 * 通过读写锁，实现list的读锁共享，写锁独占
 */
public class ReentrantLockList2 {

    //线程不安全的list
    private ArrayList<String> array = new ArrayList<>();
    //独占锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    //添加元素
    public void add(String e) {
        writeLock.lock();
        try {
            array.add(e);
        }finally {
            writeLock.unlock();
        }
    }

    //删除元素
    public void remove(String e) {
        writeLock.lock();
        try {
            array.remove(e);
        }finally {
            writeLock.unlock();
        }
    }

    //获取元素
    //获取元素使用的是读锁，这样可以运行多个读线程来同时访问list的元素，
    //这在读多写少的情况下性能更好。
    public String get(int index) {
        readLock.lock();
        try {
            return array.get(index);
        }finally {
            readLock.unlock();
        }
    }

}
