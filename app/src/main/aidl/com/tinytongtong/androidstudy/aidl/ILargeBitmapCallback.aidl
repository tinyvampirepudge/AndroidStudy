// IlargeBitmapCallback.aidl
package com.tinytongtong.androidstudy.aidl;

// Declare any non-default types here with import statements
import android.graphics.Bitmap;

interface IlargeBitmapCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    Bitmap getBitmap();
}
