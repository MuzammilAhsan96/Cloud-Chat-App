package com.application.cloudchatapp.model.base

import java.io.Serializable

data class LoginResponseModel(
    //val authorization: String = "",
    val accessToken: String? = null,
    val user_detils: UserDetailResponse? = null,
    //val review: ReviewModel? = null,
    //val rate_plan: RatePlan? = null
    //var rate_plan: MutableList<RatePlan?>? = mutableListOf()
) : Serializable


