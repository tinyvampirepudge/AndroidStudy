package com.tinytongtong.androidstudy.manager.dialoglevel;

import com.tinytongtong.androidstudy.manager.dialoglevel.core.DialogLevelWrapper;
import com.tinytongtong.androidstudy.manager.dialoglevel.core.IDialogLevel;

/**
 * @Description: TODO
 * @Author tinytongtong
 * @Date 2021/6/3 10:48 AM
 */
public class DialogLevelImplThird implements IDialogLevel {

    private DialogLevelWrapper mWrapper;

    @Override
    public void attachWrapper(DialogLevelWrapper wrapper) {
        this.mWrapper = wrapper;
    }

    @Override
    public void show() {
        System.out.println(DialogLevelImplThird.class.getSimpleName() + " show");
    }

    public void doSth() {
        System.out.println(DialogLevelImplThird.class.getSimpleName() + " doSth 哎，就是玩儿!!!");
        if (mWrapper != null) {
            mWrapper.notifyReadyStatus(false);
        }
    }

    @Override
    public void release() {
        System.out.println(DialogLevelImplThird.class.getSimpleName() + " release");
    }
}
