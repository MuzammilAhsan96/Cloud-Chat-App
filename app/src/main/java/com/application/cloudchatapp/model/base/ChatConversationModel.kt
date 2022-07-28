package com.application.cloudchatapp.model.base

import java.io.Serializable

/**
 * Created by GAURAV KUMAR on 20,October,2021
 * Quytech,
 */

class ChatConversationModel(
    var chat_id: String? = null,
    var device_type: String? = null,
    var sender_id: String? = null,
    var receiver_id: String? = null,
    var msgBody: String? = null,
    var time: String? = null,
) : Serializable

