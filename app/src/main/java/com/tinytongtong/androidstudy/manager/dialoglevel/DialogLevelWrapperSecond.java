package com.tinytongtong.androidstudy.manager.dialoglevel;

import com.tinytongtong.androidstudy.manager.dialoglevel.core.DialogLevelWrapper;
import com.tinytongtong.androidstudy.manager.dialoglevel.core.IDialogLevel;
import com.tinytongtong.androidstudy.manager.dialoglevel.core.DialogLevelMng;

/**
 * @Description: TODO
 * @Author tinytongtong
 * @Date 2021/6/2 8:01 PM
 */
public class DialogLevelWrapperSecond extends DialogLevelWrapper {

    public DialogLevelWrapperSecond(DialogLevelMng mLevelMng, IDialogLevel iDialogLevel) {
        super(mLevelMng, iDialogLevel);
    }

    @Override
    public int priority() {
        return 2;
    }
}
