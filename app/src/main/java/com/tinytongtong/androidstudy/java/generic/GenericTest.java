package com.tinytongtong.androidstudy.java.generic;

import androidx.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;

/**
 * @Description: PECS
 * https://www.jianshu.com/p/e5b8cd33ec94
 * @Author tinytongtong
 * @Date 2020/11/14 12:43 PM
 * @Version
 */
public class GenericTest {
    static class Fruit {
        @NonNull
        @Override
        public String toString() {
            return "Fruit, " + super.toString();
        }
    }

    static class Apple extends Fruit {
        @NonNull
        @Override
        public String toString() {
            return "Apple, " + super.toString();
        }
    }

    static class RedApple extends Apple {
        @NonNull
        @Override
        public String toString() {
            return "RedApple, " + super.toString();
        }
    }

    public static void main(String[] args) {
//        List<Fruit> fruitBasket = new ArrayList<>();
//        fruitBasket.add(new Fruit());
//        getOutFruits(fruitBasket);
//
//        List<Apple> appleBasket = new ArrayList<>();
//        appleBasket.add(new Apple());
//        getOutFruits(appleBasket);

//        PETest();

        CSTest();
    }

    private static void PETest() {
        // PE: Producer extends
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple());
        List<? extends Fruit> basket = apples;
        for (Fruit item : basket) {
            System.out.println(item);
        }
//        basket.add(new Fruit()); // 编译错误
//        basket.add(new Apple()); // 编译错误
    }

    /**
     * 实现如何往篮子里加不同的水果。使用<? super T>
     */
    private static void CSTest() {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple());
        apples.add(new RedApple());
        System.out.println(apples.get(0).getClass().getSimpleName());// Apple
        System.out.println(apples.get(1).getClass().getSimpleName());// RedApple

        List<? super Apple> basket = apples;
        // 编译器只知道，苹果和苹果子类型是可以放进去（也是Fruit的子类型，也是Object的子类型）。
        // 这意味着，我们总是可以将一个苹果的子类型放入苹果的超类型的list中。
        basket.add(new Apple());
        basket.add(new RedApple());
//        basket.add(new Fruit());// 编译报错
        System.out.println(basket.get(0).getClass().getSimpleName());// Apple
        System.out.println(basket.get(1).getClass().getSimpleName());// RedApple
        System.out.println(basket.get(2).getClass().getSimpleName());// Apple
        System.out.println(basket.get(3).getClass().getSimpleName());// RedApple
    }

    public static void getOutFruits(List<? extends Fruit> list) {
        for (Fruit item : list) {
            System.out.println(item);
        }
    }
}
