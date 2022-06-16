package com.application.cloudchatapp.ui.splash


import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.application.cloudchatapp.ui.main.MainActivity
import com.application.cloudchatapp.base.BaseActivity
import com.application.cloudchatapp.databinding.ActivitySplashBinding
import com.application.cloudchatapp.utils.AppConstant
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {

    private lateinit var ui: ActivitySplashBinding

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

        launchActivity(MainActivity::class.java)

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