package com.application.cloudchatapp.utils

import com.google.gson.Gson
import java.lang.reflect.Type

object GSONUtils {
    @JvmStatic
    fun <T> parseJson(json: String?, type: Type?): T {
        return Gson().fromJson(json, type)
    }

    @JvmStatic
    fun getJson(profile: Any?): String {
        return Gson().toJson(profile)
    }
}