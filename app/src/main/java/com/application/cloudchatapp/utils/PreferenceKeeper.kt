package com.application.cloudchatapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.support.multidex.BuildConfig
import com.application.cloudchatapp.base.App
import com.application.cloudchatapp.model.base.LoginResponseModel
import com.application.cloudchatapp.model.base.UserDetailResponse
import com.google.gson.Gson

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

        // Keys
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val KEY_EMAIL = "email"
        const val KEY_ID = "id"
        const val KEY_FIRST_NAME = "KEY_FIRST_NAME"
        const val KEY_LAST_NAME = "KEY_LAST_NAME"
        const val KEY_USERNAME = "username"
        const val KEY_USERPROFILE = "userprofile"
        const val KEY_CONTACT_NUMBER = "contact_number"
        const val KEY_DOB = "dob"
        const val KEY_UPLOAD_FILE = "upload_file"
        const val HOST_ID = "host_id"
        const val KEY_DEVICE_TOKEN = "device_id"
        const val KEY_USER_TYPE = "user_type"
        const val FCM_USER_ID = "fcm_user_id"
        const val CHAT_ID = "chat_id"
        const val ZOOM_VERIFICATON_STATUS = "ZOOM_VERIFICATION_STATUS"
        const val IS_NEW_NOTIFICATION = "is_new_notification"
        const val LANGUAGE_LIST = "LANGUAGE_LIST"

        fun getString(key: String): String? {
            return prefs.getString(key, "")
        }

        fun getInt(key: String): Int? {
            return prefs.getInt(key, 0)
        }

        fun putString(key: String, value: String?) {
            prefs.edit().putString(key, value).apply()
        }

        fun getStringSet(key: String): MutableSet<String?>? {
            return prefs.getStringSet(key, mutableSetOf())
        }

        fun putStringSet(key: String, value: MutableSet<String?>?) {
            prefs.edit().putStringSet(key, value).apply()
        }

        fun putInt(key: String, value: Int?) {
            value?.let { prefs.edit().putInt(key, it).apply() }
        }


        fun putBoolean(key: String, value: Boolean?) {
            value?.let { prefs.edit().putBoolean(key, it).apply() }
        }

        fun getBoolean(key: String): Boolean {
            return prefs.getBoolean(key, false)
        }

        fun putLong(key: String, value: Long?) {
            value?.let { prefs.edit().putLong(key, it).apply() }
        }

        fun getLong(key: String): Long {
            return prefs.getLong(key, 0)
        }

        fun clearAuthTokenPref() {
            val editor = prefs.edit()
            editor.remove(ACCESS_TOKEN)
            editor.apply()
        }

        fun getUser(): UserDetailResponse? {
            return Gson().fromJson(
                prefs.getString("user", ""),
                UserDetailResponse::class.java
            )
        }

        fun setUser(user: UserDetailResponse?) {
            user?.let {
                AppConstant.USER_PROFILE = user
                prefs.edit().putString("user", Gson().toJson(user)).apply()
            }
        }

        fun clearAllPrefrance() {
            val lang = getStringSet(LANGUAGE_LIST)?.toMutableList()
            val editor = prefs.edit()
            editor.clear()
            editor.apply()
            putStringSet(LANGUAGE_LIST, lang?.toMutableSet())
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


    /*var languages: String?
        get() {
            return sharedPreferences.getString(AppConstant.APP.EXHIBITION_NAME, "")
        }
        set(exhibitionId) {
            sharedPreferences.edit().putString(AppConstant.APP.EXHIBITION_NAME, exhibitionId).apply()
        }*/


    fun clearAllData() {
        prefs.edit().clear().apply()
        AppUtil.generateFCMToken(App.INSTANCE)
    }

}