package com.tinytongtong.androidstudy.innerclass;

/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2020/6/18 5:21 PM
 * @Version TODO
 */
class TestInnerClass {
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.print();

        InterfaceTest interfaceTest = new InterfaceTest() {
            @Override
            public void test() {

            }
        };
    }
}
