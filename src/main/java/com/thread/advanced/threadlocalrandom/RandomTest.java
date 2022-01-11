package com.thread.advanced.threadlocalrandom;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ThreadRandom , 在对线程情况下，多个线程需要竞争种子这个原子变量。
 * 根据这个种子，生成新的随机数.由于CAS操作，所以，会造成大量的线程进行自旋重试。影响并发性能.
 * ThreadLocalRandom通过模仿ThreadLocal的原理，让每个线程都持有自己的本地种子变量。
 * 该种子变量只有在使用随机数的时候才会被初始化，这样下来，在多线程情况下对种子进行更新，都操作的是自己线程所持有的变量。
 * 从而避免的竞争自旋.
 */
public class RandomTest {
    public static void main(String[] args) {
        Random random1 = new Random();
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
    }
}
