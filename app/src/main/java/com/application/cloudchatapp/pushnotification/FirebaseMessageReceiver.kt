package com.application.cloudchatapp.pushnotification

import android.content.Intent
import android.util.Log
import com.application.cloudchatapp.utils.AppConstant
import com.application.cloudchatapp.ui.main.MainActivity
import com.application.cloudchatapp.utils.PreferenceKeeper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson

class FirebaseMessageReceiver : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("FFFFFF", "fcm token: $p0")
//        PreferenceKeeper.getInstance().fcmToken = p0
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)


            Log.d("FFFFFF", "fcm message from onMessageReceived: ${Gson().toJson(p0.data)}")
//        Log.d("FFFFFF", "fcm message from onMessageReceived: ${p0.data}")
        if (p0.data.values.isNotEmpty()) {
            val messagePayload = Gson().fromJson((Gson().toJson(p0.data)), Payload::class.java)
            val dataPayload =  Gson().fromJson((messagePayload.data), Data::class.java)

            var title = ""
            var message = ""
            var intent: Intent? = null
            dataPayload?.apply {
                message = text.toString()

                val exId = PreferenceKeeper.getInstance().exhibitionId

                if(notificationType == 12){
                    title = "Ticket"
                    intent = Intent(applicationContext, MainActivity::class.java)
//                        intent?.putExtra(AppConstant.BK.FROM, AppConstant.BK.NOTIFICATION)
                    intent?.putExtra(AppConstant.BK.NOTIFICATION_TYPE, AppConstant.NOTIFICATION_TYPE.TICKET)
                } else{
                    if(meetingId.isNullOrBlank()){
                        title = "Connection"
                        /*when(notificationType){
                            AppConstant.NOTIFICATION_TYPE.REQUEST -> {
                                //when sender has accepted the notification
                                intent = Intent(applicationContext, ConnectionsActivity::class.java)
//                        intent?.putExtra(AppConstant.BK.FROM, AppConstant.BK.NOTIFICATION)
                                intent?.putExtra(AppConstant.BK.NOTIFICATION_TYPE, AppConstant.NOTIFICATION_TYPE.REQUEST)
                            }

                            AppConstant.NOTIFICATION_TYPE.REJECT -> {
                                //when sender has accepted the notification
                                intent = Intent(applicationContext, ConnectionsActivity::class.java)
//                        intent?.putExtra(AppConstant.BK.FROM, AppConstant.BK.NOTIFICATION)
                                intent?.putExtra(AppConstant.BK.NOTIFICATION_TYPE, AppConstant.NOTIFICATION_TYPE.REJECT)

                            }
                            AppConstant.NOTIFICATION_TYPE.ACCEPT -> {
                                //when sender has accepted the notification
                                intent = Intent(applicationContext, ConnectionsActivity::class.java)
//                        intent?.putExtra(AppConstant.BK.FROM, AppConstant.BK.NOTIFICATION)
                                intent?.putExtra(AppConstant.BK.NOTIFICATION_TYPE, AppConstant.NOTIFICATION_TYPE.ACCEPT)

                            }

                            AppConstant.NOTIFICATION_TYPE.C0NN_SCAN -> {
                                //when sender has accepted the notification
                                intent = Intent(applicationContext, ConnectionsActivity::class.java)
//                        intent?.putExtra(AppConstant.BK.FROM, AppConstant.BK.NOTIFICATION)
                                intent?.putExtra(AppConstant.BK.NOTIFICATION_TYPE, AppConstant.NOTIFICATION_TYPE.ACCEPT)

                            }

                            AppConstant.NOTIFICATION_TYPE.EVENT_REM -> {
                                //when sender has accepted the notification
                                intent = Intent(applicationContext, EventActivity::class.java)
//                        intent?.putExtra(AppConstant.BK.FROM, AppConstant.BK.NOTIFICATION)
                                intent?.putExtra(AppConstant.BK.NOTIFICATION_TYPE, AppConstant.NOTIFICATION_TYPE.ACCEPT)

                            }


                            else ->{
                                intent = Intent(applicationContext, NotificationActivity::class.java)

                            }
                        }*/
                    } else{
                        title = "Meeting"
                        /*when(notificationType){

                            AppConstant.NOTIFICATION_TYPE.REQUEST -> {
                                //when sender has accepted the notification
                                intent = Intent(applicationContext, EventActivity::class.java)
//                        intent?.putExtra(AppConstant.BK.FROM, AppConstant.BK.NOTIFICATION)
                                intent?.putExtra(AppConstant.BK.NOTIFICATION_TYPE, AppConstant.NOTIFICATION_TYPE.REQUEST)

                            }
                            AppConstant.NOTIFICATION_TYPE.REJECT -> {
                                //when sender has accepted the notification
                                intent = Intent(applicationContext, EventActivity::class.java)
//                        intent?.putExtra(AppConstant.BK.FROM, AppConstant.BK.NOTIFICATION)
                                intent?.putExtra(AppConstant.BK.NOTIFICATION_TYPE, AppConstant.NOTIFICATION_TYPE.REJECT)

                            }
                            AppConstant.NOTIFICATION_TYPE.ACCEPT -> {
                                //when sender has accepted the notification
                                intent = Intent(applicationContext, EventActivity::class.java)
//                        intent?.putExtra(AppConstant.BK.FROM, AppConstant.BK.NOTIFICATION)
                                intent?.putExtra(AppConstant.BK.NOTIFICATION_TYPE, AppConstant.NOTIFICATION_TYPE.ACCEPT)

                            }
                            AppConstant.NOTIFICATION_TYPE.RESCHEDULE -> {
                                //when sender has accepted the notification
                                intent = Intent(applicationContext, EventActivity::class.java)
//                        intent?.putExtra(AppConstant.BK.FROM, AppConstant.BK.NOTIFICATION)
                                intent?.putExtra(AppConstant.BK.NOTIFICATION_TYPE, AppConstant.NOTIFICATION_TYPE.RESCHEDULE)
                                intent?.putExtra(AppConstant.BK.RESCHEDULE_DATA, dataPayload)

                            }
                            else ->{
                                intent = Intent(applicationContext, NotificationActivity::class.java)

                            }
                        }*/
                    }
                }

//                //checks if exhbition is different
//                if(exId != exhibitionId){
//                    intent = Intent(applicationContext, NotificationActivity::class.java)
////                    showToast("Notification is for a different exhibition")
//                }
            }


            /*NotificationUtil.showNotification(
                this,
                title,
                message,
                intent
            )*/
        }
    }
}