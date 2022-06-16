package com.application.cloudchatapp.api

import com.application.cloudchatapp.model.base.Errors

interface APICallHandler<T> {

    fun onSuccess(apiType: APIType, response: T?)

    fun onFailure(apiType: APIType, error: Errors)
}