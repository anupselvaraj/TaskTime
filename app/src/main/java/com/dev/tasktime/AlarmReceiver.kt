package com.dev.tasktime
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import android.app.Notification
import android.graphics.BitmapFactory

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        val channelId = "alarm_id"

        context?.let { ctx ->
            val notificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notification = NotificationCompat.Builder(ctx, channelId)
                    .setSmallIcon(R.drawable.tasktimeicon)
                    .setContentTitle("Alarm Demo")
                    .setContentText("Notification sent with message $message")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .build()

                notificationManager.notify(1, notification )
            }
            else
            {
//             builder = Notification.Builder(ctx)
//                .setContent(ctx)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_background))
            }
            Toast.makeText(context, "Alarm set successfully $message", Toast.LENGTH_SHORT).show()

        }
    }
}
