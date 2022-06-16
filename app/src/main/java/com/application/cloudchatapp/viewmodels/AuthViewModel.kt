package com.application.cloudchatapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.cloudchatapp.api.APICallHandler
import com.application.cloudchatapp.model.base.Errors
import com.application.cloudchatapp.api.APIType

class AuthViewModel : ViewModel(), APICallHandler<Any> {
    var error = MutableLiveData<Errors>()
/*

    var signupSuccess = MutableLiveData<UserProfileResponse?>()
    var signin = MutableLiveData<UserProfileResponse?>()
    var forgot = MutableLiveData<UserProfileResponse?>()
    var emailExists = MutableLiveData<CommonApiResponse?>()


    fun signupAPI(model: SignupModel?) {
        val apiCallManager = APICallManager(APIType.SIGNUP, this)
        apiCallManager.signupAPI(model)
    }

    fun signin(model: SigninModel?) {
        val apiCallManager = APICallManager(APIType.SIGNIN, this)
        apiCallManager.signin(model)
    }


    fun forgot(email: String?) {
        val apiCallManager = APICallManager(APIType.FORGOT, this)
        apiCallManager.forgot(email)
    }


    fun emailExist(email: String?) {
        val apiCallManager = APICallManager(APIType.EMAIL_EXIST, this)
        apiCallManager.emailExist(email)
    }
*/

    override fun onSuccess(apiType: APIType, response: Any?) {
        when (apiType) {
            /*APIType.SIGNUP -> {
                signupSuccess.value = response as UserProfileResponse
            }

            APIType.EMAIL_EXIST -> {
                emailExists.value = response as CommonApiResponse
            }

            APIType.SIGNIN -> {
                signin.value = response as UserProfileResponse
            }

            APIType.FORGOT -> {
                forgot.value = response as UserProfileResponse
            }*/


            else -> {
            }

        }
    }

    override fun onFailure(apiType: APIType, error: Errors) {
        this.error.value = error
    }
}