package com.wscsports.android.blaze.sampleapp

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment


fun Activity.logd(message: String?) {
    Log.d("${this::class.java.simpleName}TAG", message ?: "")
}

fun Fragment.logd(message: String?) {
    Log.d("${this::class.java.simpleName}TAG", message ?: "")
}

fun Context.logd(message: String?) {
    Log.d("${this::class.java.simpleName}TAG", message ?: "")
}