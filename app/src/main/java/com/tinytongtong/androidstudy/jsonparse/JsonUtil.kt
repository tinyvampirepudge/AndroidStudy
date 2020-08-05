package com.tinytongtong.androidstudy.jsonparse

import android.content.Context
import android.text.TextUtils
import java.io.IOException
import java.io.InputStream

/**
 * @Description: JsonUtil更新
 *
 * @Author tinytongtong
 * @Date 2020/8/2 8:56 PM
 * @Version
 */
class JsonUtil {
    companion object {
        val instance = JsonUtil()
    }

    /**
     * 从assets中读取对应的json文件
     *
     * @param context
     * @param assetsName
     * @return
     */
    fun getJsonFromAssets(
        context: Context,
        assetsName: String
    ): String? {
        if (TextUtils.isEmpty(assetsName)) {
            return null
        }
        var jsonString = ""
        try {
            val sb = StringBuffer()
            var `is`: InputStream? = null
            `is` = context.assets.open(assetsName)
            var len = -1
            val buf = ByteArray(`is`.available()) //为了解决部分中文乱码问题，一次读取所有的
            while (`is`.read(buf).also { len = it } != -1) {
                sb.append(String(buf, 0, len, Charsets.UTF_8))
            }
            `is`.close()
            jsonString = sb.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return jsonString
    }
}