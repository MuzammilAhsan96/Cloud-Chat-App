package com.application.cloudchatapp.pushnotification


object NotificationUtil {

    /*fun showNotification(context: Context, title: String?, body: String?, intent: Intent?) {
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1
        val channelId = "channel-01"
        val channelName = "Channel Name"
        val importance = NotificationManager.IMPORTANCE_HIGH
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                channelId, channelName, importance
            )
            notificationManager.createNotificationChannel(mChannel)
        }
        val notiIntent = Intent(context, DashboardActivity::class.java)
        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(com.app.pbgevents.R.drawable.app_icon)
            .setContentTitle(title)
            *//*.setContentText(body)*//*
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
        val stackBuilder: TaskStackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addNextIntent(intent ?: notiIntent)
        val resultPendingIntent: PendingIntent? = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder.setContentIntent(resultPendingIntent)
        notificationManager.notify(notificationId, mBuilder.build())
    }*/
}