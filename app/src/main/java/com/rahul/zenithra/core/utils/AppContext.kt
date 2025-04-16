package com.rahul.zenithra.core.utils

import android.content.Context
import java.lang.ref.WeakReference

object AppContext {
    private var contextRef: WeakReference<Context>? = null

    fun initialize(context: Context) {
        this.contextRef = WeakReference(context.applicationContext)
    }

    val context: Context?
        get() = contextRef?.get()
}