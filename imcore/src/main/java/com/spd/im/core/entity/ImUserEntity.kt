package com.spd.im.core.entity

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

/**
 * @author :Reginer in  2020/6/1 14:41.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
@Keep
@Parcelize
class ImUserEntity(var cardId: String, var userName: String, var img: String? = null) : Parcelable {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}