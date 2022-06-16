package com.application.cloudchatapp.api

import android.text.TextUtils
import android.util.Log
import com.application.cloudchatapp.BuildConfig
import com.application.cloudchatapp.utils.AppConstant
import com.application.cloudchatapp.utils.PreferenceKeeper
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class APIInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String =  PreferenceKeeper.getInstance().accessToken ?: ""
        Log.i("ACCESS", "TOKEN $token")
        val originalRequest: Request = chain.request()
        if (TextUtils.isEmpty(token)) {
            val builder = originalRequest.newBuilder()
            val oldReq: Request = builder
                //   .addHeader("Content-Type:application", "x-www-form-urlencoded")
                .addHeader("Authorization", BuildConfig.BASE_AUTH)
                .addHeader("appVersion", BuildConfig.VERSION_NAME)
                .addHeader("Connection", "close")
                .addHeader("platform", AppConstant.APP.DEVICE_TYPE)
                .build()
            return chain.proceed(oldReq)
        }
        val builder = originalRequest.newBuilder()
        val oldReq: Request = builder
            .addHeader("accessToken", token)
            .addHeader("Authorization", BuildConfig.BASE_AUTH)
            .addHeader("appVersion", BuildConfig.VERSION_NAME)
            .addHeader("Connection", "close")
            .addHeader("platform", AppConstant.APP.DEVICE_TYPE)
            .build()
        return chain.proceed(oldReq)
    }

    companion object {
        private val TAG = APIInterceptor::class.java.simpleName
    }
}