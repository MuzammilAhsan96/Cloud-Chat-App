package com.application.cloudchatapp.model.base

import java.io.Serializable

data class ChatUserModel(
    var id: String? = null,
    var profileImg: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var time: String? = null
) : Serializable