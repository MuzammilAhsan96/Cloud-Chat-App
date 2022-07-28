package com.application.cloudchatapp.ui.splash


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.application.cloudchatapp.ui.main.MainActivity
import com.application.cloudchatapp.base.BaseActivity
import com.application.cloudchatapp.databinding.ActivityLoginBinding
import com.application.cloudchatapp.databinding.ActivitySplashBinding
import com.application.cloudchatapp.extension.launchActivity
import com.application.cloudchatapp.ui.auth.LoginActivity
import com.application.cloudchatapp.ui.auth.RegisterActivity
import com.application.cloudchatapp.ui.chat.RecentChatActivity
import com.application.cloudchatapp.utils.AppConstant
import com.application.cloudchatapp.utils.PreferenceKeeper
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {

    private lateinit var ui: ActivitySplashBinding

    /*override fun layoutRes(): ViewBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }*/
    override fun layoutRes(): ViewBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = binding as ActivitySplashBinding
        initUI()
        navigateActivity()
    }


    private fun initUI() {

        /*val slideDown = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.anim_down
        )

        val slideUp = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.anim_up
        )

        ui.ivMain.startAnimation(
            AnimationUtils.loadAnimation(
                applicationContext,
                R.anim.anim_rotation
            )
        )*/


    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun navigateActivity() {
        GlobalScope.launch { // context of the parent, main runBlocking coroutine
            delay(AppConstant.SPLASH_DELAY)

            /*val slideUp = AnimationUtils.loadAnimation(
                applicationContext,
                R.anim.anim_up
            )
            ui.ivMain.startAnimation(slideUp)*/
            delay(900)
            gotoScreen()
        }
    }

    private fun gotoScreen() {
        val isLogin = PreferenceKeeper.getString(PreferenceKeeper.ACCESS_TOKEN)
        if (isLogin.equals("") || isLogin!!.isEmpty()) {
            launchActivity(RecentChatActivity::class.java)
        } else {
            launchActivity(RecentChatActivity::class.java)
        }
        finish()
        /*val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()*/
        //launchActivity(LoginActivity::class.java)

        /*PreferenceKeeper.getInstance().apply {
            if (isQuestionnaire)
                launchActivity(DashboardActivity::class.java)
            else {

                if (!TextUtils.isEmpty(exhibitionId))
                    launchActivity(QuestionnaireActivity::class.java)
                else
                    launchActivity(DashboardActivity::class.java)
            }
        }*/
        finishAffinity()
    }

}