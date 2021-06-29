package com.tinytongtong.androidstudy.manager.dialoglevel;

import com.tinytongtong.androidstudy.manager.dialoglevel.core.IDialogLevel;
import com.tinytongtong.androidstudy.manager.dialoglevel.core.DialogLevelMng;
import com.tinytongtong.androidstudy.manager.dialoglevel.core.DialogLevelWrapper;

/**
 * @Description: TODO
 * @Author tinytongtong
 * @Date 2021/6/2 8:01 PM
 */
public class DialogLevelWrapperFourth extends DialogLevelWrapper {

    public DialogLevelWrapperFourth(IDialogLevel iDialogLevel) {
        super(iDialogLevel);
    }

    @Override
    public int priority() {
        return 4;
    }
}
