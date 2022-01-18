package com.thread.advanced.aqs;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

/**
 *  自定义一个不可重入的锁，自定义AQS需要重写一些列函数，还需要自定义原子变量state的含义。
 *  state为0，表示锁没有被线程持有，state为1表示锁已经被某一个线程持有。
 *  由于是不可重入锁，所以不需要记录持有锁的线程获取锁的次数。
 */
public class ConsumerAndProducer {
    final static NonReentrantLock lock = new NonReentrantLock();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();

    final static Queue<String> queue = new LinkedBlockingQueue<>();
    final static int queueSize = 10;

    public static void main(String[] args) {

        Thread producer = new Thread(()->{
            //get exclusive lock
            lock.lock();
            try {
                //if queue is full, use condition var to wait
                while (queue.size() == queueSize) {
                    notEmpty.await();
                }
                //add ele to queue.
                queue.add("ele");

                //notify other thread
                notFull.signal();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        Thread consumer = new Thread(()->{
            lock.lock();
            try {
                while (0 == queue.size()) {
                    notFull.await();
                }

                String ele = queue.poll();

                notEmpty.signal();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });


        producer.start();
        consumer.start();
    }
}
