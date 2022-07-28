package com.application.cloudchatapp.utils

object PrefManager {
    /*private var sharedPreferences: SharedPreferences =
        CustomApplication.application.applicationContext.getSharedPreferences(
            BuildConfig.APPLICATION_ID,
            Context.MODE_PRIVATE
        )

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
        return sharedPreferences.getString(key, "")
    }

    fun getInt(key: String): Int? {
        return sharedPreferences.getInt(key, 0)
    }

    fun putString(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getStringSet(key: String): MutableSet<String?>? {
        return sharedPreferences.getStringSet(key, mutableSetOf())
    }

    fun putStringSet(key: String, value: MutableSet<String?>?) {
        sharedPreferences.edit().putStringSet(key, value).apply()
    }

    fun putInt(key: String, value: Int?) {
        value?.let { sharedPreferences.edit().putInt(key, it).apply() }
    }


    fun putBoolean(key: String, value: Boolean?) {
        value?.let { sharedPreferences.edit().putBoolean(key, it).apply() }
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun putLong(key: String, value: Long?) {
        value?.let { sharedPreferences.edit().putLong(key, it).apply() }
    }

    fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0)
    }

    fun clearAuthTokenPref() {
        val editor = sharedPreferences.edit()
        editor.remove(ACCESS_TOKEN)
        editor.apply()
    }

    *//*var languages: String?
        get() {
            return sharedPreferences.getString(AppConstant.APP.EXHIBITION_NAME, "")
        }
        set(exhibitionId) {
            sharedPreferences.edit().putString(AppConstant.APP.EXHIBITION_NAME, exhibitionId).apply()
        }*//*

    fun getUser(): LoginResponseModel? {
        return Gson().fromJson(
            sharedPreferences.getString("user", ""),
            LoginResponseModel::class.java
        )
    }

    fun setUser(user: LoginResponseModel?) {
        user?.let {
            AppConstant.USER_PROFILE = user
            sharedPreferences.edit().putString("user", Gson().toJson(user)).apply()
        }
    }

    fun clearAllPrefrance() {
        val lang = getStringSet(LANGUAGE_LIST)?.toMutableList()
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        putStringSet(LANGUAGE_LIST, lang?.toMutableSet())
    }*/

}