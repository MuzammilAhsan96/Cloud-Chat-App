package com.app.pbgevents.utils

import android.view.View
import com.application.cloudchatapp.extension.snack
import com.application.cloudchatapp.model.base.Errors
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorUtil {
    fun handlerGeneralError(view: View?, throwable: Throwable) {
        throwable.printStackTrace()
        if (view == null) return
        when (throwable) {
            is ConnectException -> view.snack("Please turn on Internet"){}
            is SocketTimeoutException -> view.snack("Socket Timeout"){}
            is UnknownHostException -> view.snack("No Internet Connection"){}
            is InternalError -> view.snack("Internal Server Error"){}
            is HttpException -> { view.snack("Something went wrong"){}}
            else -> { view.snack("Something went wrong"){} }
        }
    }

    fun generateError(message: String?): Errors? {
        val errors= Errors()
        errors.errorMessage=message

        return errors
    }

}
