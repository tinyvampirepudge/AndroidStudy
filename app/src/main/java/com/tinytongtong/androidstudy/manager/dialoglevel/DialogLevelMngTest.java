package com.tinytongtong.androidstudy.manager.dialoglevel;

import com.tinytongtong.androidstudy.manager.dialoglevel.core.DialogLevelMng;
import com.tinytongtong.androidstudy.manager.dialoglevel.core.DialogLevelWrapper;

import java.util.concurrent.TimeUnit;

/**
 * @Description: {@link DialogLevelMng} 的测试用例
 * @Author tinytongtong
 * @Date 2021/6/4 5:41 PM
 */
public class DialogLevelMngTest {

    public static void main(String[] args) {
        // 初始化管理器
        DialogLevelMng mng = new DialogLevelMng();
        // 初始化各个任务
        DialogLevelImplFirst implFirst = new DialogLevelImplFirst();
        DialogLevelWrapper wrapperFirst = new DialogLevelWrapperFirst(implFirst);

        DialogLevelImplSecond implSecond = new DialogLevelImplSecond();
        DialogLevelWrapper wrapperSecond = new DialogLevelWrapperSecond(implSecond);

        DialogLevelImplThird implThird = new DialogLevelImplThird();
        DialogLevelWrapper wrapperThird = new DialogLevelWrapperThird(implThird);

        DialogLevelImplFourth implFourth = new DialogLevelImplFourth();
        DialogLevelWrapper wrapperFourth = new DialogLevelWrapperFourth(implFourth);
        // 添加数据
        mng.add(wrapperFourth);
        mng.add(wrapperThird);
        mng.add(wrapperSecond);
        mng.add(wrapperFirst);

        // log测试
        mng.log();


        // mock 单个对话框主动更新数据状态
        new Thread(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    implFirst.doSth(true);
                    mng.log();
                }
        ).start();

        new Thread(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    implSecond.doSth(true);
                    mng.log();
                }
        ).start();

        new Thread(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    implThird.doSth(true);
                    mng.log();
                }
        ).start();

        new Thread(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    implFourth.doSth(true);
                    mng.log();
                }
        ).start();

        new Thread(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    implFirst.doSth(false);
                    implSecond.doSth(false);
                    implThird.doSth(false);
                    implFourth.doSth(false);
                    mng.log();
                }
        ).start();
    }
}
