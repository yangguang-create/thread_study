package com.thread.advanced.reentrantlocklist;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用reentrantLock实现一个线程安全的list.
 * 如上代码，通过操作array元素前进行加锁保证同一时间只有一个线程可以对array数组进行修改。
 * 但是也只能有一个线程对array元素进行访问。
 */
public class ReentrantLockList {

    //线程不安全的list
    private ArrayList<String> array = new ArrayList<>();
    //独占锁
    private volatile ReentrantLock lock = new ReentrantLock();

    //添加元素
    public void add(String e) {
        lock.lock();
        try {
            array.add(e);
        }finally {
            lock.unlock();
        }
    }

    //删除元素
    public void remove(String e) {
        lock.lock();
        try {
            array.remove(e);
        }finally {
            lock.unlock();
        }
    }

    //获取元素
    public String get(int index) {
        lock.lock();
        try {
            return array.get(index);
        }finally {
            lock.unlock();
        }
    }

}
