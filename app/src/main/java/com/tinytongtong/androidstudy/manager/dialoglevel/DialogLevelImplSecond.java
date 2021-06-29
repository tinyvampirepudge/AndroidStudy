package com.tinytongtong.androidstudy.manager.dialoglevel;

import com.tinytongtong.androidstudy.manager.dialoglevel.core.IDialogLevel;
import com.tinytongtong.androidstudy.manager.dialoglevel.core.DialogLevelWrapper;

/**
 * @Description: TODO
 * @Author tinytongtong
 * @Date 2021/6/3 10:48 AM
 */
public class DialogLevelImplSecond implements IDialogLevel {

    private DialogLevelWrapper mWrapper;

    @Override
    public void attachWrapper(DialogLevelWrapper wrapper) {
        this.mWrapper = wrapper;
    }

    @Override
    public void show() {
        System.out.println(DialogLevelImplSecond.class.getSimpleName() + " show");
    }

    public void doSth(boolean status) {
        System.out.println(DialogLevelImplSecond.class.getSimpleName() + " doSth 哎，就是玩儿!!!");
        if (mWrapper != null) {
            mWrapper.notifyReadyStatus(status);
        }
    }

    @Override
    public void release() {
        System.out.println(DialogLevelImplSecond.class.getSimpleName() + " release");
    }
}
