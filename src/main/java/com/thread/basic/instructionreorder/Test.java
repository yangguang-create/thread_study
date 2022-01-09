package com.thread.basic.instructionreorder;

/**
 * 由于(1)(2)(3)(4)这四个步骤不存在数据依赖性。
 * 所以，可能会被编译器或者处理器进行指令重排。
 * 即先执行(4)再执行(3),并且在(3)执行前开始执行(2)。这是得到的结果是0，而不是 4.
 *
 * 重排序在多线程下会导致非预期的程序执行结果。
 *  使用volatile可以避免重排序和内存可见性的问题。
 *  针对写操作，保证写之前的操作不会重排序到写操作之后。
 *  针对读操作，保证读之后的操作不会重排序到读操作之前。
 */
public class Test {
    static int num = 0;
    //使用volatile修饰，可以避免指令重排序.
    static volatile boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();
        WriteThread writeThread = new WriteThread();
        writeThread.start();

        Thread.sleep(10);
        readThread.interrupt();
        System.out.println("main over..");
    }

    public static class WriteThread extends Thread {
        @Override
        public void run() {
            num = 2;//(3)
            ready = true;//(4)
            System.out.println("write thread set over...");
        }
    }

    public static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (ready) {//(1)
                    System.out.println(num + num);//(2)
                }
                System.out.println("read thread ....");
            }
        }
    }
}


