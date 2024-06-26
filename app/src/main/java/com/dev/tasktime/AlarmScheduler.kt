package com.dev.tasktime
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.AlarmManagerCompat.canScheduleExactAlarms
import java.time.ZoneId

class AlarmScheduler(
    private val context: Context
) {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    fun schedule(alarmItem: AlarmItem) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", alarmItem.message)
        }
        val alarmTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            alarmItem.time.atZone(ZoneId.systemDefault()).toEpochSecond()*1000L
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        if(canScheduleExactAlarms(alarmManager))
        {
            val pi = PendingIntent.getBroadcast(context, alarmItem.hashCode(),intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                alarmTime,
                pi)
            Log.e("TastTimeApp", "Alarm set at $alarmTime")
        }
        else
        {
            return
        }
    }

    fun cancel(alarmItem: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                alarmItem.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}