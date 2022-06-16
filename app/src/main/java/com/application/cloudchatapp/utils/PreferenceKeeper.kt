package com.application.cloudchatapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.support.multidex.BuildConfig
import com.application.cloudchatapp.base.App

class PreferenceKeeper {

    init {
        prefs = App.INSTANCE.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    companion object {
        private lateinit var prefs: SharedPreferences
        private lateinit var keeper: PreferenceKeeper
        fun getInstance(): PreferenceKeeper {
            keeper = PreferenceKeeper()
            return keeper
        }
    }

    var accessToken: String?
        get() {
            return prefs.getString(AppConstant.APP.ACCESS_TOKEN, "")
        }
        set(accessToken) {
            prefs.edit().putString(AppConstant.APP.ACCESS_TOKEN, accessToken).apply()
        }


    var deviceToken: String?
        get() {
            return prefs.getString(AppConstant.APP.DEVICE_TOKEN, "")
        }
        set(deviceToken) {
            prefs.edit().putString(AppConstant.APP.DEVICE_TOKEN, deviceToken).apply()
        }

    /*var fcmToken: String?
        get() {
            return prefs.getString(AppConstant.APP.FCM_TOKEN, "")
        }
        set(fcmToken) {
            prefs.edit().putString(AppConstant.APP.FCM_TOKEN, fcmToken).apply()
        }*/


    var deviceId: String?
        get() {
            return prefs.getString(AppConstant.APP.DEVICE_ID, "")
        }
        set(deviceId) {
            prefs.edit().putString(AppConstant.APP.DEVICE_ID, deviceId).apply()
        }


    var exhibitionId: String?
        get() {
            return prefs.getString(AppConstant.APP.EXHIBITION_ID, "")
        }
        set(exhibitionId) {
            prefs.edit().putString(AppConstant.APP.EXHIBITION_ID, exhibitionId).apply()
        }

    var exhibitionImage: String?
        get() {
            return prefs.getString(AppConstant.APP.EXHIBITION_IMAGE, "")
        }
        set(exhibitionId) {
            prefs.edit().putString(AppConstant.APP.EXHIBITION_IMAGE, exhibitionId).apply()
        }

    var exhibitionName: String?
        get() {
            return prefs.getString(AppConstant.APP.EXHIBITION_NAME, "")
        }
        set(exhibitionId) {
            prefs.edit().putString(AppConstant.APP.EXHIBITION_NAME, exhibitionId).apply()
        }


    var isLogin: Boolean
        get() {
            return prefs.getBoolean(AppConstant.APP.USER_LOGGED_IN, false)
        }
        set(isLogin) {
            prefs.edit().putBoolean(AppConstant.APP.USER_LOGGED_IN, isLogin ?: false).apply()
        }

    var isSwitch: Boolean?
        get() {
            return prefs.getBoolean(AppConstant.APP.IS_SWITCH, false)
        }
        set(isSwitch) {
            prefs.edit().putBoolean(AppConstant.APP.IS_SWITCH, isSwitch ?: false).apply()
        }


    var isQuestionnaire: Boolean
        get() {
            return prefs.getBoolean(AppConstant.APP.IS_QUESTIONNAIRE, false)
        }
        set(isQuestionnaire) {
            prefs.edit().putBoolean(AppConstant.APP.IS_QUESTIONNAIRE, isQuestionnaire ?: false)
                .apply()
        }


    /*fun getUser(): UserProfileData? {
        return Gson().fromJson(prefs.getString("user", ""), UserProfileData::class.java)
    }

    fun setUser(user: UserProfileData?) {
        user?.let {
            AppConstant.USER_PROFILE = user
            prefs.edit().putString("user", Gson().toJson(user)).apply()
        }
    }*/

    fun clearAllData() {
        prefs.edit().clear().apply()
        AppUtil.generateFCMToken(App.INSTANCE)
    }

}