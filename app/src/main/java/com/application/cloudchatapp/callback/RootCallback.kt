package com.application.cloudchatapp.callback

import android.view.View

interface RootCallback<T> {
    fun onRootCallback(index: Int, data: T?, type: CallbackType, view: View?) {}
}