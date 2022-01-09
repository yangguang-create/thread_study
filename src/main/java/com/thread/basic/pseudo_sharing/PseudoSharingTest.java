package com.thread.basic.pseudo_sharing;

/**
 * 多线程下访问同一个缓存行的多个变量时才会出现伪共享。
 * 在单线程下访问一个缓存行里面的多个变量反而会对程序起到加速作用。
 */
public class PseudoSharingTest {
    /*
    *   在单线程情况下，顺序修改一个缓存行中的多个变量，会充分利用程序运行的局部性原则，从而加速程序的运行。
    *   在多线程情况下，并发修改一个缓存行中的多个变量时就会竞争缓存行，从而降低程序运行性能。
    * */
}

class TestForContent {
    static final int LINE_NUM = 1024;
    static final int COLUMN_NUM = 1024;

    public static void main(String[] args) {
        long[][] array = new long[LINE_NUM][COLUMN_NUM];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; ++i) {
            for (int j = 0; j < COLUMN_NUM; ++j) {
                array[i][j] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("cached time:" + (endTime - startTime));
    }
}
class TestForContent2 {
    static final int LINE_NUM = 1024;
    static final int COLUMN_NUM = 1024;

    public static void main(String[] args) {
        long[][] array = new long[LINE_NUM][COLUMN_NUM];

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < COLUMN_NUM; ++i) {
            for (int j = 0; j < LINE_NUM; ++j) {
                array[i][j] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("no cached time:" + (endTime - startTime));
    }
}