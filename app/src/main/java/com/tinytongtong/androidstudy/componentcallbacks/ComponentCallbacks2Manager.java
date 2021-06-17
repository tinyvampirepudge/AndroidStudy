package com.tinytongtong.androidstudy.componentcallbacks;

import android.content.ComponentCallbacks;
import android.content.Context;

/**
 * @Description: 接收ComponentCallbacks2的回调
 * @Author tinytongtong
 * @Date 4/29/21 5:08 PM
 */
public class ComponentCallbacks2Manager {

    private ComponentCallbacks mComponentCallbacks;
    private Context mContext;

    private ComponentCallbacks2Manager() {

    }

    private static final class ComponentCallbacks2ManagerHolder {
        private static final ComponentCallbacks2Manager INSTANCE = new ComponentCallbacks2Manager();
    }

    public static ComponentCallbacks2Manager getInstance() {
        return ComponentCallbacks2ManagerHolder.INSTANCE;
    }

    public void init(Context context) {
        mContext = context;
    }

    public void register(ComponentCallbacks componentCallbacks) {
        if (mContext == null) {
            return;
        }
        mComponentCallbacks = componentCallbacks;
        mContext.registerComponentCallbacks(mComponentCallbacks);
    }

    public void unregister(ComponentCallbacks componentCallbacks) {
        if (mContext == null) {
            return;
        }
        mContext.registerComponentCallbacks(componentCallbacks);
    }
}
