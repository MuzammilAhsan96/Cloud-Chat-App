package com.application.cloudchatapp.ui.auth

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.application.cloudchatapp.base.BaseActivity
import com.application.cloudchatapp.databinding.ActivityRegisterBinding
import com.application.cloudchatapp.extension.hideKeyboard
import com.application.cloudchatapp.model.base.UserDetailResponse
import com.application.cloudchatapp.utils.AppUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : BaseActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var ui: ActivityRegisterBinding
    private val allUsers = mutableListOf(
        UserDetailResponse(
            id = "111111",
            email = "muzzu@gmail.com",
            firstName = "Muzzu",
            lastName = "Ahsan",
            password = "12345678"
        ), UserDetailResponse(
            id = "222222",
            email = "pushkar@gmail.com",
            firstName = "Pushkar",
            lastName = "Dubey",
            password = "12345678"
        ),
        UserDetailResponse(
            id = "333333",
            email = "vipul@gmail.com",
            firstName = "Vipul",
            lastName = "Khankriyal",
            password = "12345678"
        ), UserDetailResponse(
            id = "444444",
            email = "mukku@gmail.com",
            firstName = "Mukku",
            lastName = "Ahsan",
            password = "12345678"
        ),
        UserDetailResponse(
            id = "555555",
            email = "raju@gmail.com",
            firstName = "Raju",
            lastName = "Pandey",
            password = "12345678"
        )
    )

    override fun layoutRes(): ViewBinding {
        return ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = binding as ActivityRegisterBinding
        // Initialize Firebase Auth
        auth = Firebase.auth
        setListeners()
    }

    private fun setListeners() {
        ui.includeHeader.ivBack.setOnClickListener {
            AppUtil.preventTwoClick(it)
            it.hideKeyboard()
            onBackPressed()
        }
    }
}