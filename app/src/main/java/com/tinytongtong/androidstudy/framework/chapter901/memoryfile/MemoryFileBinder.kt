package com.tinytongtong.androidstudy.framework.chapter901.memoryfile

import android.os.Binder
import android.os.Parcel
import android.os.ParcelFileDescriptor
import android.util.Log
import java.io.FileInputStream

/**
 * 两个进程在传递 FileDescriptor 时用到的 Code。
 */
const val MY_TRANSACT_CODE = 920511

/**
 * @Description: 这里不必使用 AIDL，继承 Binder 类 重写 onTransact 即可。
 *
 * @Author tinytongtong
 * @Date 2020/10/6 12:44 AM
 * @Version
 */
class MemoryFileBinder : Binder() {
    companion object {
        val TAG = MemoryFileBinder::class.java.simpleName
    }

    /**
     * 文件描述符 和 数据大小 通过 data 传入。
     */
    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
        val parent = super.onTransact(code, data, reply, flags)
        if (code != MY_TRANSACT_CODE && code != 931114) {
            return parent
        }

        // 读取 ParcelFileDescriptor 并转为 FileDescriptor
        val pfd = data.readParcelable<ParcelFileDescriptor>(javaClass.classLoader)
        if (pfd == null) {
            return parent
        }
        val descriptor = pfd.fileDescriptor

        // 读取共享内存中数据的大小
        val size = data.readInt()

        // 根据 FileDescriptor 创建 InputStream
        val input = FileInputStream(descriptor)

        // 从 共享内存 中读取字节，并转为文字
        val bytes = input.readBytes()
        val message = String(bytes, 0, size, Charsets.UTF_8)

        Log.e(TAG, "读取到另外一个进程写入的字符串：「$message」")

        // 回复调用进程
        reply?.writeString("Server 端收到 FileDescriptor, 并且从共享内存中读到了：「$message」")

        return true
    }
}