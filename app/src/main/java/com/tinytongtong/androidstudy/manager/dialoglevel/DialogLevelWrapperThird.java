package com.tinytongtong.androidstudy.manager.dialoglevel;

import com.tinytongtong.androidstudy.manager.dialoglevel.core.DialogLevelWrapper;
import com.tinytongtong.androidstudy.manager.dialoglevel.core.IDialogLevel;
import com.tinytongtong.androidstudy.manager.dialoglevel.core.DialogLevelMng;

/**
 * @Description: TODO
 * @Author tinytongtong
 * @Date 2021/6/2 8:01 PM
 */
public class DialogLevelWrapperThird extends DialogLevelWrapper {

    public DialogLevelWrapperThird(DialogLevelMng mLevelMng, IDialogLevel iDialogLevel) {
        super(mLevelMng, iDialogLevel);
    }

    @Override
    public int priority() {
        return 3;
    }
}
