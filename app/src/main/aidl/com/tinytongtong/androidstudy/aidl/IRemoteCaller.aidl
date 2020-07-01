// IRemoteCaller.aidl
package com.tinytongtong.androidstudy.aidl;

// Declare any non-default types here with import statements
import com.tinytongtong.androidstudy.aidl.ICallback;

interface IRemoteCaller {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void publishBinder(ICallback callback);
}
