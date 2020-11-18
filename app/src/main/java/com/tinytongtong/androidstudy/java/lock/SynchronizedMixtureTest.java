package com.tinytongtong.androidstudy.java.lock;

/**
 * @Description: synchronized 对象锁 类锁 混合测试
 * @Author tinytongtong
 * @Date 2020/11/17 6:03 PM
 * @Version
 */
public class SynchronizedMixtureTest {

    /**
     * 加了类锁的方法
     */
    public static synchronized void method1() {
        System.out.println("类锁");
        try {
            System.out.println("类锁 sleeping start");
            Thread.sleep(500);
            System.out.println("类锁 sleeping end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对象锁
     */
    public synchronized void method2() {
        System.out.println("我是对象锁");
        try {
            System.out.println("对象锁 sleeping start");
            Thread.sleep(100);
            System.out.println("对象锁 sleeping end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加了类锁的代码块
     */
    public void method3() {
        synchronized (SynchronizedClassTest.class) {
            System.out.println("我是加了类锁的代码块");
            try {
                System.out.println("加了类锁的代码块 sleeping start");
                Thread.sleep(100);
                System.out.println("加了类锁的代码块 sleeping end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 加了对象锁的代码块
     */
    public void method4() {
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
     * 类锁不会阻塞同一对象中其他非加锁方法的访问
     */
    private static void test1() {
        SynchronizedMixtureTest st = new SynchronizedMixtureTest();
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
        Thread thread4 = new Thread(new Runnable() {
            int count = 0;

            @Override
            public void run() {
                while (count < 20) {
                    st.method4();
                    count++;
                }
            }
        });

        thread1.start();
        thread2.start();
//        thread3.start();
//        thread4.start();
    }
}
