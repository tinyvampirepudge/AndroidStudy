package com.tinytongtong.androidstudy.aidl

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Description:
 *
 * @Author tinytongtong
 * @Date 2020/10/4 5:33 PM
 * @Version
 */
@Parcelize
data class LargeListBean(val index: Int?, val count: Int?, val person: PersonBean?): Parcelable

@Parcelize
data class PersonBean(val name: String?, val age: Int?): Parcelable