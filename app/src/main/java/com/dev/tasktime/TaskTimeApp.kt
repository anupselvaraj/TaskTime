package com.dev.tasktime
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build

class TaskTimeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val channelId = "alarm_id"
        val channelName = "alarm_name"
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )

        } else {
            TODO("VERSION.SDK_INT < O")
        }
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor = Color.GREEN
        notificationManager.createNotificationChannel(channel)

    }
}