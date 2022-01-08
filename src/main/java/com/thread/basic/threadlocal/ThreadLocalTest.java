package com.thread.basic.threadlocal;

/**
 *  ThreadLocal 提供了线程的本地变量。
 *  多线程在访问 ThreadLocal对象的时候，实际操作的是自己本地内存里面的变量。从而避免了线程安全的问题。
 *  创建一个ThreadLocal对象后，每个线程都会复制一个变量到自己本地内存中。
 */
public class ThreadLocalTest {

    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    static void print(String str) {
        //1:打印当前线程本地内存中localVariable变量的值
        System.out.println(str + ": " + localVariable.get());
        //2：清除当前线程本地变量内存中的localVariable变量
//        localVariable.remove();
    }

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            //设置线程1 本地变量localVariable的值.
            localVariable.set("threadOne local variable");
            print("threadOne");
            System.out.println("threadOne remove after :" + localVariable.get());
        });

        Thread threadTwo = new Thread(() -> {
            //设置线程2 本地变量localVariable的值.
            localVariable.set("threadTwo local variable");
            print("threadTwo");
            System.out.println("threadTwo remove after :" + localVariable.get());
        });

        threadOne.start();
        threadTwo.start();

    }
}
