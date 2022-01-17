package com.thread.advanced.copyonarraylist;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 迭代器的弱一致性:
 *      所谓的弱一致性，指的是返回迭代器后，其他线程对list的增删改对迭代器不可见.
 *      因为他们操作的是不同的两个数组.
 */
public class CopyList {

    private static volatile CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        arrayList.add("hello");
        arrayList.add("alibaba");
        arrayList.add("welcome");
        arrayList.add("to");
        arrayList.add("hangzhou");

        Thread threadOne = new Thread(()-> {
            arrayList.set(1, "baba");
            arrayList.remove(2);
            arrayList.remove(3);
        });

        Iterator<String> itr = arrayList.iterator();

        threadOne.start();
        threadOne.join();
        while (itr.hasNext()) {
            System.out.println(itr.next());

        }
    }


}
