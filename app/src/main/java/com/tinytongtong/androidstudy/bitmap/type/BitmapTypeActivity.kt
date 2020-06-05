package com.tinytongtong.androidstudy.bitmap.type

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tinytongtong.androidstudy.R
import com.tinytongtong.tinyutils.LogUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_bitmap_type.*
import java.io.File

/**
 * @Description: 获取bitmap网图类型
 * https://www.jianshu.com/p/5829e2d29567
 * @Author wangjianzhou
 * @Date 2020/6/3 6:07 PM
 * @Version
 */
class BitmapTypeActivity : AppCompatActivity() {
    companion object {
        val TAG = BitmapTypeActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_type)

        val jpgUrl = "https://i.guancha.cn/news/2018/05/30/20180530134316299.jpg!wap.jpg"
        val pngUrl = "https://tinytongtong-1255688482.cos.ap-beijing.myqcloud.com/kotlin2.png"
        val gifUrl =
            "https://tinytongtong-1255688482.cos.ap-beijing.myqcloud.com/Aug-09-2019%2011-36-57.gif"
        val webpUrl = "https://tinytongtong-1255688482.cos.ap-beijing.myqcloud.com/maomi1.webp"

        btnJpeg.setOnClickListener {
            Observable.create<File> {
                val fileTask = Glide.with(this)
                    .asFile()
                    .load(jpgUrl)
                    .submit()
                val file = fileTask.get()
                it.onNext(file)
                it.onComplete()
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    ivJpeg.setImageBitmap(BitmapFactory.decodeFile(it.absolutePath))
                    it
                }
                .observeOn(Schedulers.io())
                .map({
                    getBmpTypeByOptions(it.absolutePath)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                        LogUtils.e(TAG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable?) {
                        LogUtils.e(TAG, "onSubscribe")
                    }

                    override fun onNext(t: String?) {
                        LogUtils.e(TAG, "onNext")
                        LogUtils.e(TAG, "onNext mimeType:" + t)
                        tvJpegInfoByBmpOptions.text = tvJpegInfoByBmpOptions.text.toString() + t
                    }

                    override fun onError(e: Throwable?) {
                        LogUtils.e(TAG, "onError")
                    }

                })

            Observable.create<File> {
                var fileTask = Glide.with(this)
                    .asFile()
                    .load(jpgUrl)
                    .submit()
                val file = fileTask.get()
                it.onNext(file)
                it.onComplete()
            }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map({
                    FileTypeUtil.getMimeType(it.absolutePath)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                        LogUtils.e(TAG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable?) {
                        LogUtils.e(TAG, "onSubscribe")
                    }

                    override fun onNext(t: String?) {
                        LogUtils.e(TAG, "onNext")
                        LogUtils.e(TAG, "onNext mimeType:" + t)
                        tvJpegInfoByHead.text = tvJpegInfoByHead.text.toString() + t
                    }

                    override fun onError(e: Throwable?) {
                        LogUtils.e(TAG, "onError")
                    }

                })
        }

        btnPng.setOnClickListener {
            Observable.create<File> {
                var fileTask = Glide.with(this)
                    .asFile()
                    .load(pngUrl)
                    .submit()
                val file = fileTask.get()
                it.onNext(file)
                it.onComplete()
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    ivPng.setImageBitmap(BitmapFactory.decodeFile(it.absolutePath))
                    it
                }
                .observeOn(Schedulers.io())
                .map({
                    getBmpTypeByOptions(it.absolutePath)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                        LogUtils.e(TAG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable?) {
                        LogUtils.e(TAG, "onSubscribe")
                    }

                    override fun onNext(t: String?) {
                        LogUtils.e(TAG, "onNext")
                        LogUtils.e(TAG, "onNext mimeType:" + t)
                        tvPngInfoByBmpOptions.text = tvPngInfoByBmpOptions.text.toString() + t
                    }

                    override fun onError(e: Throwable?) {
                        LogUtils.e(TAG, "onError")
                    }

                })

            Observable.create<File> {
                var fileTask = Glide.with(this)
                    .asFile()
                    .load(pngUrl)
                    .submit()
                val file = fileTask.get()
                it.onNext(file)
                it.onComplete()
            }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map({
                    FileTypeUtil.getMimeType(it.absolutePath)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                        LogUtils.e(TAG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable?) {
                        LogUtils.e(TAG, "onSubscribe")
                    }

                    override fun onNext(t: String?) {
                        LogUtils.e(TAG, "onNext")
                        LogUtils.e(TAG, "onNext mimeType:" + t)
                        tvPngInfoByHead.text = tvPngInfoByHead.text.toString() + t
                    }

                    override fun onError(e: Throwable?) {
                        LogUtils.e(TAG, "onError")
                    }

                })
        }

        btnGif.setOnClickListener {
            Observable.create<File> {
                var fileTask = Glide.with(this)
                    .asFile()
                    .load(gifUrl)
                    .submit()
                val file = fileTask.get()
                it.onNext(file)
                it.onComplete()
            }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map({
                    getBmpTypeByOptions(it.absolutePath)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                        LogUtils.e(TAG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable?) {
                        LogUtils.e(TAG, "onSubscribe")
                    }

                    override fun onNext(t: String?) {
                        LogUtils.e(TAG, "onNext")
                        LogUtils.e(TAG, "onNext mimeType:" + t)
                        tvGifInfoByBmpOptions.text = tvGifInfoByBmpOptions.text.toString() + t
                    }

                    override fun onError(e: Throwable?) {
                        LogUtils.e(TAG, "onError")
                    }

                })

            Observable.create<File> {
                var fileTask = Glide.with(this)
                    .asFile()
                    .load(gifUrl)
                    .submit()
                val file = fileTask.get()
                it.onNext(file)
                it.onComplete()
            }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map({
                    FileTypeUtil.getMimeType(it.absolutePath)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                        LogUtils.e(TAG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable?) {
                        LogUtils.e(TAG, "onSubscribe")
                    }

                    override fun onNext(t: String?) {
                        LogUtils.e(TAG, "onNext")
                        LogUtils.e(TAG, "onNext mimeType:" + t)
                        tvGifInfoByHead.text = tvGifInfoByHead.text.toString() + t
                    }

                    override fun onError(e: Throwable?) {
                        LogUtils.e(TAG, "onError")
                    }

                })

            Glide.with(this).load(gifUrl).into(ivGif)
        }

        btnWebp.setOnClickListener {
            Observable.create<File> {
                var fileTask = Glide.with(this)
                    .asFile()
                    .load(webpUrl)
                    .submit()
                val file = fileTask.get()
                it.onNext(file)
                it.onComplete()
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    ivWebp.setImageBitmap(BitmapFactory.decodeFile(it.absolutePath))
                    it
                }
                .observeOn(Schedulers.io())
                .map({
                    getBmpTypeByOptions(it.absolutePath)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                        LogUtils.e(TAG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable?) {
                        LogUtils.e(TAG, "onSubscribe")
                    }

                    override fun onNext(t: String?) {
                        LogUtils.e(TAG, "onNext")
                        LogUtils.e(TAG, "onNext mimeType:" + t)
                        tvWebpInfoByBmpOptions.text = tvWebpInfoByBmpOptions.text.toString() + t
                    }

                    override fun onError(e: Throwable?) {
                        LogUtils.e(TAG, "onError")
                    }

                })

            Observable.create<File> {
                var fileTask = Glide.with(this)
                    .asFile()
                    .load(webpUrl)
                    .submit()
                val file = fileTask.get()
                it.onNext(file)
                it.onComplete()
            }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map({
                    FileTypeUtil.getMimeType(it.absolutePath)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                        LogUtils.e(TAG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable?) {
                        LogUtils.e(TAG, "onSubscribe")
                    }

                    override fun onNext(t: String?) {
                        LogUtils.e(TAG, "onNext")
                        LogUtils.e(TAG, "onNext mimeType:" + t)
                        tvWebpInfoByHead.text = tvWebpInfoByHead.text.toString() + t
                    }

                    override fun onError(e: Throwable?) {
                        LogUtils.e(TAG, "onError")
                    }

                })
        }

    }

    fun getBmpTypeByOptions(filePath: String): String {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, options)
        return options.outMimeType
    }
}