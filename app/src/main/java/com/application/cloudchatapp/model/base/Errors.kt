package com.application.cloudchatapp.model.base

import com.google.gson.annotations.SerializedName

class Errors {
    var errorCode = 0

    @SerializedName("responseMessage")
    var errorMessage: String? = null
}