// IlargeBitmapListCallback.aidl
package com.tinytongtong.androidstudy.aidl;

// Declare any non-default types here with import statements
import android.graphics.Bitmap;
import java.util.List;

interface ILargeBitmapListCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Bitmap> getBitmapList();
}
