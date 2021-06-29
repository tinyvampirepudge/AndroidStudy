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

    public DialogLevelWrapperThird(IDialogLevel iDialogLevel) {
        super(iDialogLevel);
    }

    @Override
    public int priority() {
        return 3;
    }
}
