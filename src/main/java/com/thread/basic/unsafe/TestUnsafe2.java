package com.thread.basic.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 *  不能通过正规渠道使用Unsafe这个类。因为这个类是直接操作内存的对象。不安全。
 *  所以Unsafe类在设计的时候，不能从外界直接使用。
 *  但是可以使用间接的方法使用:
 *      1:使用反射。
 */
public class TestUnsafe2 {
    //通过反射的方式获取
    static final Unsafe unsafe;

    //记录变量state在类TestUnsafe中的偏移值
    static final long stateOffset;


    private volatile long state = 0;

    static {
        try {
            //使用反射获取Unsafe的成员变量theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            //设置为可存取
            field.setAccessible(true);
            //获取变量的值
            unsafe = (Unsafe) field.get(null);

            //获取state变量在类TestUnsafe中的偏移值.
            stateOffset = unsafe.objectFieldOffset(TestUnsafe2.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        TestUnsafe2 testUnsafe = new TestUnsafe2();
        //如果testUnsafe对象中内存偏移量为stateOffset的state变量的值为0,就更新该值为1.
        boolean b = unsafe.compareAndSwapInt(testUnsafe, stateOffset, 0, 1);
        System.out.println(b);

    }
}
