// ILargeDataListCallback.aidl
package com.tinytongtong.androidstudy.aidl;

// Declare any non-default types here with import statements
import com.tinytongtong.androidstudy.aidl.LargeListBean;
import java.util.List;

interface ILargeDataListCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<LargeListBean> getDataList();
}
