package com.application.cloudchatapp.model.base

import java.io.Serializable

/**
 * Created by GAURAV KUMAR on 27,October,2021
 * Quytech,
 */
data class ChatRecentUserModel(
    var chat_id: String? = null,
    var sender_id: String? = null,
    var receiver_id: String? = null,
    var msgBody: String? = null,
    var time: String? = null,
    var senderDetail: ChatUserModel? = null,
    var receiverDetail: ChatUserModel? = null
) : Serializable
