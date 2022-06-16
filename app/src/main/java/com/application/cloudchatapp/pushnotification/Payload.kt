package com.application.cloudchatapp.pushnotification

import java.io.Serializable

data class Payload(
    var android: String?,
    var `data`: String?,
    var ios: String?
)


data class Data(
    var endTime: String?,
    var exhibitionId: String?,
    var meetingId: String?,
    var notificationType: Int?,
    var receiverId: String?,
    var senderId: String?,
    var reason: String?,
    var startDate: String?,
    var startTime: String?,
    var timezone: String?,
    var text: String?
):Serializable