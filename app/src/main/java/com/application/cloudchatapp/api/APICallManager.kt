package com.application.cloudchatapp.api

import android.util.Log
import com.application.cloudchatapp.extension.launchActivityClearFlag
import com.application.cloudchatapp.extension.showToast
import com.application.cloudchatapp.utils.PreferenceKeeper
import com.application.cloudchatapp.R
import com.application.cloudchatapp.model.base.BaseResponse
import com.application.cloudchatapp.base.App
import com.application.cloudchatapp.model.base.Errors
import com.application.cloudchatapp.ui.splash.SplashActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

private const val TAG = "APICallManager"
class APICallManager<T>(
    private val mCallType: APIType,
    private val mAPICallHandler: APICallHandler<T>
) : Callback<BaseResponse<T>> {

    override fun onResponse(call: Call<BaseResponse<T>>?, response: Response<BaseResponse<T>>) {

        if (response.code() == APIStatusCode.OK || response.code() == APIStatusCode.CREATED || response.code() == APIStatusCode.NO_CONTENT) {
            val body = response.body()

            if (body != null) {
                if (body.statusCode == 1) {
                    mAPICallHandler.onSuccess(mCallType, body.responseData)
                } else {
                    if (body.error != null) {


                        Log.d(TAG, "onResponse: "+body.error?.errorMessage)
                        if (body.error?.errorCode == 1) {
                            Log.d(TAG, "onResponse: "+body.error.toString())
//                            PreferenceKeeper.getInstance().isLogin = false
//
//                            PreferenceKeeper.getInstance().accessToken = null

//                            val intent = Intent(App.INSTANCE, SignInActivity::class.java)
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                            App.INSTANCE.startActivity(intent)

                        }
                        else if (body.error?.errorCode == 2) {
                            Log.d(TAG, "onResponse: "+body.error.toString())
                            PreferenceKeeper.getInstance().exhibitionId = null
                            PreferenceKeeper.getInstance().isLogin = false
                            PreferenceKeeper.getInstance().isQuestionnaire = false
                            PreferenceKeeper.getInstance().clearAllData()
                            showToast(body.error?.errorMessage)

                            App.INSTANCE.launchActivityClearFlag(SplashActivity::class.java)

                        }

                        else {
                            mAPICallHandler.onFailure(mCallType, body.error!!)
                        }
                    } else {
                        val errors = Errors()
                        val errorMessage =
                            App.INSTANCE.resources.getString(R.string.error_something_wrong)
                        errors.errorMessage = errorMessage

                        Log.d(TAG, "onResponse: "+errorMessage)
                        mAPICallHandler.onFailure(mCallType, errors)
                    }
                }
            }
        } else {

            Log.d(TAG, "onResponse: pls try again later")
            val errors = Errors()
            val errorMessage = App.INSTANCE.resources.getString(R.string.error_something_wrong)
            errors.errorMessage = errorMessage
            mAPICallHandler.onFailure(mCallType, errors)
        }
    }

    override fun onFailure(call: Call<BaseResponse<T>>?, throwable: Throwable) {
        val errorCode = 0
        val message: String? =
            if (throwable is UnknownHostException || throwable is SocketException || throwable is SocketTimeoutException) {
                App.INSTANCE.resources.getString(R.string.error_something_wrong)
            } else {
                throwable.message
            }
        val errors = Errors()

        Log.d(TAG, "onFailure: "+message)
        errors.errorMessage = message
        mAPICallHandler.onFailure(mCallType, errors)
    }

    /*fun logout(signupModel: SignupModel) {
        APIHandler.getApiInterface()
            .logout(signupModel).enqueue(this@APICallManager as Callback<BaseResponse<CommonApiResponse>>)
    }
*/

}
