package com.tinytongtong.androidstudy.innerclass;

/**
 * @Description: TODO
 * @Author tinytongtong
 * @Date 2020/6/18 5:15 PM
 * @Version TODO
 */
class OuterClass {

    class InnerClass {

        public void print() {
            System.out.println("猫了个咪");
        }
    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.print();
    }
}
