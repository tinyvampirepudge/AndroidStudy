package com.tinytongtong.androidstudy.manager.dialoglevel;

import com.tinytongtong.androidstudy.manager.dialoglevel.core.DialogLevelWrapper;
import com.tinytongtong.androidstudy.manager.dialoglevel.core.IDialogLevel;
import com.tinytongtong.androidstudy.manager.dialoglevel.core.DialogLevelMng;

/**
 * @Description: TODO
 * @Author tinytongtong
 * @Date 2021/6/2 8:01 PM
 */
public class DialogLevelWrapperFirst extends DialogLevelWrapper {

    public DialogLevelWrapperFirst(IDialogLevel iDialogLevel) {
        super(iDialogLevel);
    }

    @Override
    public int priority() {
        return 1;
    }

}
