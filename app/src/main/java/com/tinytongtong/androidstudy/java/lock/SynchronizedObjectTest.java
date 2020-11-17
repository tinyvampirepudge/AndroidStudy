package com.tinytongtong.androidstudy.java.lock;

/**
 * @Description: synchronized 对象锁 测试
 * @Author tinytongtong
 * @Date 2020/11/17 5:46 PM
 * @Version
 */
public class SynchronizedObjectTest {

    /**
     * 加了对象锁的方法
     */
    public synchronized void method1() {
        System.out.println("对象锁");
        try {
            System.out.println("对象锁 sleeping start");
            Thread.sleep(500);
            System.out.println("对象锁 sleeping end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通方法
     */
    public void method2() {
        System.out.println("我是普通方法");
        try {
            System.out.println("普通方法 sleeping start");
            Thread.sleep(100);
            System.out.println("普通方法 sleeping end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加了对象锁的代码块
     */
    public void method3() {
        synchronized (this) {
            System.out.println("我是加了对象锁的代码块");
            try {
                System.out.println("加了对象锁的代码块 sleeping start");
                Thread.sleep(100);
                System.out.println("加了对象锁的代码块 sleeping end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        test1();
    }

    /**
     * 对象锁不会阻塞同一对象中其他非加锁方法的访问
     */
    private static void test1() {
        SynchronizedObjectTest st = new SynchronizedObjectTest();
        Thread thread1 = new Thread(new Runnable() {
            int count = 0;

            @Override
            public void run() {
                while (count < 20) {
                    st.method1();
                    count++;
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            int count = 0;

            @Override
            public void run() {
                while (count < 20) {
                    st.method2();
                    count++;
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            int count = 0;

            @Override
            public void run() {
                while (count < 20) {
                    st.method3();
                    count++;
                }
            }
        });

        thread1.start();
//        thread2.start();
        thread3.start();
    }
}
