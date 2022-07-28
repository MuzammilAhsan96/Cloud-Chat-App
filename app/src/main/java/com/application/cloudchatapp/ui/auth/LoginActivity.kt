package com.application.cloudchatapp.ui.auth

import android.os.Bundle
import android.text.TextUtils
import androidx.viewbinding.ViewBinding
import com.application.cloudchatapp.R
import com.application.cloudchatapp.base.BaseActivity
import com.application.cloudchatapp.databinding.ActivityLoginBinding
import com.application.cloudchatapp.extension.showToast
import com.application.cloudchatapp.model.base.UserDetailResponse
import com.application.cloudchatapp.ui.chat.RecentChatActivity
import com.application.cloudchatapp.utils.AppUtil
import com.application.cloudchatapp.utils.PreferenceKeeper

class LoginActivity : BaseActivity() {
    private lateinit var ui: ActivityLoginBinding
    private var email: String? = null
    private var password: String? = null
    private var flag = false
    private var index = -1
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
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = binding as ActivityLoginBinding
        setListeners()
    }

    private fun setListeners() {
        ui.tvRegister.setOnClickListener {
            AppUtil.preventTwoClick(it)
            launchActivity(RegisterActivity::class.java)
        }
        ui.btnSignIn.setOnClickListener {
            AppUtil.preventTwoClick(it)
            if (validation()) {
                for (i in 0..allUsers.size.minus(1)) {
                    if (email == allUsers[i].email && password == allUsers[i].password) {
                        flag = true
                        index = i
                        break
                    }
                }
                if (flag) {
                    PreferenceKeeper.putString(
                        PreferenceKeeper.ACCESS_TOKEN,
                        allUsers[index].id
                    )
                    PreferenceKeeper.setUser(allUsers[index])
                    PreferenceKeeper.putString(
                        PreferenceKeeper.KEY_ID,
                        allUsers[index].id
                    )
                    launchActivity(RecentChatActivity::class.java)
                    finish()
                } else {
                    showToast("Email or Password is wrong")
                }
            }
        }
    }

    private fun validation(): Boolean {
        if (!validateEmailOrUsername()) {
            ui.tieEmail.requestFocus()
            return false
        }
        if (!validatePassword()) {
            ui.tiePassword.requestFocus()
            return false
        }
        return true
    }

    private fun validateEmailOrUsername(): Boolean {
        email = ui.tieEmail.text.toString().trim()
        val x = ui.tieEmail.left
        val y = ui.tieEmail.top
        return when {
            TextUtils.isEmpty(email) -> {
                ui.tilEmail.isErrorEnabled = true
                ui.tilEmail.error = getString(R.string.entermail)
                //ui.scrollView.scrollTo(x, y)
                false
            }
            email?.length ?: 0 < 3 -> {
                ui.tilEmail.isErrorEnabled = true
                ui.tilEmail.error =
                    getString(R.string.enter_three_character_long_username_email)
                //ui.scrollView.scrollTo(x, y)
                false
            }
            else -> {
                ui.tilEmail.isErrorEnabled = false
                true
            }
        }
    }

    private fun validatePassword(): Boolean {
        password = ui.tiePassword.text.toString().trim()
        val x = ui.tiePassword.left
        val y = ui.tiePassword.top
        return when {
            TextUtils.isEmpty(password) -> {
                ui.tilPassword.isErrorEnabled = true
                ui.tilPassword.error = getString(R.string.enterpassword)
                false
            }
            password?.length ?: 0 < 8 -> {
                ui.tilPassword.isErrorEnabled = true
                ui.tilPassword.error = getString(R.string.enter_eight_character_long_password)
                false
            }
            else -> {
                ui.tilPassword.isErrorEnabled = false
                true
            }
        }
    }
}