package com.tinytongtong.androidstudy.proxy;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

/**
 * @Description:
 * @Author tinytongtong
 * @Date 2020/10/19 9:28 PM
 * @Version
 */
public class ProxyUtil {

    /**
     * 判断设备 是否使用代理上网
     *
     * @param context
     * @return
     */
    private boolean isWifiProxy(Context context) {
        // 是否大于等于4.0
        final boolean IS_ICS_OR_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
        String proxyAddress;
        int proxyPort;
        if (IS_ICS_OR_LATER) {
            proxyAddress = System.getProperty("http.proxyHost");
            String portStr = System.getProperty("http.proxyPort");
            proxyPort = Integer.parseInt((portStr != null ? portStr : "-1"));
        } else {
            proxyAddress = android.net.Proxy.getHost(context);
            proxyPort = android.net.Proxy.getPort(context);
        }
        return (!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1);
    }
}
