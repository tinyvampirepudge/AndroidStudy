package com.tinytongtong.androidstudy.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tinytongtong.androidstudy.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_java.*
import java.util.concurrent.TimeUnit

class RxJavaActivity : AppCompatActivity() {
    companion object {
        const val TAG = "RxJavaActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)

        btnFlatMap.setOnClickListener {
            Observable.create(object : ObservableOnSubscribe<Int> {
                override fun subscribe(emitter: ObservableEmitter<Int>?) {
                    emitter?.onNext(1)
                    emitter?.onNext(2)
                    emitter?.onNext(3)
                    emitter?.onComplete()
                }
            })
                .flatMap(object : Function<Int, ObservableSource<String>> {
                    override fun apply(t: Int?): ObservableSource<String> {
                        val list = mutableListOf<String>()
                        for (x in 0 until 3) {
                            list.add("I am value " + t)
                        }
                        return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS)
                    }

                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                        Log.e(TAG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable?) {
                        Log.e(TAG, "onSubscribe")
                    }

                    override fun onNext(t: String?) {
                        Log.e(TAG, "onNext:" + t)
                    }

                    override fun onError(e: Throwable?) {
                        Log.e(TAG, "onError:" + e?.cause)
                    }

                })
        }
    }
}
