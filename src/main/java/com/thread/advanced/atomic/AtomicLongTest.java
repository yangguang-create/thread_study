package com.thread.advanced.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 在没有源自类的情况下，实现计数器需要hi用一定的同步措施，
 * 比如使用synchronized关键字，但是这些都是阻塞方法，对性能有一定影响，
 * 而这里的原子操作类使用CAS非阻塞算法，性能更好。
 *
 * 但是在高并发情况下，AtomicLong还是会存在性能问题，
 * 而LongAdder解决了AtomicLong的性能问题.
 */
public class AtomicLongTest {
    private static AtomicLong atomicLong = new AtomicLong();

    private static Integer[] arrayOne = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] arrayTwo = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            int size = arrayOne.length;
            for (int i = 0; i < size; ++i) {
                if (arrayOne[i].intValue() == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        Thread threadTwo = new Thread(() -> {
            int size = arrayTwo.length;
            for (int i = 0; i < size; ++i) {
                if (arrayTwo[i].intValue() == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();

        System.out.println("count 0:" + atomicLong.get());
    }
}
