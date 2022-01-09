package com.thread.basic.threadlocal;

/**
 * 验证ThreadLocal 不支持继承性。
 * 验证InheritableThreadLocal 支持继承性。
 *      此线程使用父线程中threadLocals方法有很多，比如，创建线程时传入父线程中的变量。并将其复制到子线程中。
 *      但是这样的设计会改变用户的使用习惯。
 *      所以，如果需要子线程正常获取到父线程中的变量。可以使用InheritableThreadLocal。
 */
public class ThreadLocalTest2 {
    //    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("hello world");
        Thread thread = new Thread(() -> {
            System.out.println("thread:" + threadLocal.get());
        });

        thread.start();
        System.out.println("main:" + threadLocal.get());

    }
}
