package com.tinytongtong.androidstudy.framework.chapter901.memoryfile

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.tinytongtong.androidstudy.R
import java.io.FileDescriptor

/**
 * @Description: MemoryFile传递大数据
 * https://www.jianshu.com/p/4a4bc36000fc
 *
 * @Author tinytongtong
 * @Date 2020/10/6 12:22 AM
 * @Version
 */
class MemoryFileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_file)

        // 创建服务进程
        val intent = Intent(this, MemoryFileService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }
}

private val serviceConnection = object : ServiceConnection {

    override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
        if (binder == null) {
            return
        }

        // 创建 MemoryFile，并拿到 ParcelFileDescriptor
        val descriptor = createMemoryFile() ?: return

        // 传递 FileDescriptor 和 共享内存中数据的大小
        val sendData = Parcel.obtain()
        sendData.writeParcelable(descriptor, 0)
        sendData.writeInt(bytes.size)

        // 保存对方进程的返回值
        val reply = Parcel.obtain()

        // 开始跨进程传递
        binder.transact(MY_TRANSACT_CODE, sendData, reply, 0)

        // 读取 Binder 执行的结果
        val msg = reply.readString()
        Log.e("MemoryFileTest", "Binder 执行结果是：「$msg」")
    }

    override fun onServiceDisconnected(name: ComponentName?) {}

}

/**
 * 需要写入到共享内存中的数据
 */
private val bytes = "落霞与孤鹜齐飞，秋水共长天一色。".toByteArray()


/**
 * 创建 MemoryFile 并返回 ParcelFileDescriptor
 */
private fun createMemoryFile(): ParcelFileDescriptor? {
    // 创建 MemoryFile 对象，1024 是最大占用内存的大小。
    val file = MemoryFile("TestAshmemFile", 1024)

    // 获取文件描述符，因为方法被标注为 @hide，只能反射获取
    val descriptor = invokeMethod("getFileDescriptor", file) as? FileDescriptor

    // 如果获取失败，返回
    if (descriptor == null) {
        Log.e("MemoryFileTest", "获取匿名共享内存的 FileDescriptor 失败")
        return null
    }

    // 往共享内存中写入数据
    file.writeBytes(bytes, 0, 0, bytes.size)

    // 因为要跨进程传递，需要序列化 FileDescriptor
    return ParcelFileDescriptor.dup(descriptor)
}


/**
 * 通过反射执行 obj.name() 方法
 */
private fun invokeMethod(name: String, obj: Any): Any? {
    val method = obj.javaClass.getDeclaredMethod(name)
    return method.invoke(obj)
}