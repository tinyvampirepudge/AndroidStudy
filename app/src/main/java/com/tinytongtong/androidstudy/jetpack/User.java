package com.tinytongtong.androidstudy.jetpack;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

/**
 * @Description: TODO
 * @Author wangjianzhou
 * @Date 4/6/22 9:28 PM
 * @Version TODO
 */
public class User {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableInt age = new ObservableInt();
}
