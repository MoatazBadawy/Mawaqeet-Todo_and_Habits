package com.moataz.mawaqeet.components.notification

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.moataz.mawaqeet.R
import com.moataz.mawaqeet.components.notification.utils.NotificationConstant.HABIT_ALERT_MORNING_CHANNEL_ID
import com.moataz.mawaqeet.main.MainActivity
import java.util.*

class HabitAlertMorningNotification : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        // Create the notification channel
        val channelId = HABIT_ALERT_MORNING_CHANNEL_ID

        // Get the title string from the resources file
        val title = context.getString(R.string.morning_notification_title)
        val description = context.getString(R.string.morning_notification_description)

        // Create an intent to launch when the user taps the notification
        val notificationIntent = Intent(context, MainActivity::class.java)
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(notificationIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

        val notification: Notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.ic_habits)
            .setLights(Notification.FLAG_SHOW_LIGHTS, 1000, 500)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(resultPendingIntent)
            .setStyle(NotificationCompat.BigTextStyle())
            .setLights(Color.BLUE, 500, 500)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(context, channelId)
        } else {
            Notification.Builder(context)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                channelId,
                "Channel_ALERT_CHANNEL_ID",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.enableLights(true)
            channel.lightColor = Color.WHITE
            channel.enableVibration(false)
            notificationManager.createNotificationChannel(channel)
            notificationManager.notify(0, notification)
        } else {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(0, notification)
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun setupHabitAlertMorningNotification(context: Context) {
        val cal = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()
        cal[Calendar.HOUR_OF_DAY] = 6
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        if (cal.timeInMillis > System.currentTimeMillis()) {
            val notificationIntent = Intent(context, HabitAlertMorningNotification::class.java)
            val broadcast =
                PendingIntent.getBroadcast(
                    context,
                    0,
                    notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                cal.timeInMillis,
                (24 * 60 * 60 * 1000).toLong(),
                broadcast
            ) //Repeat every 24 h
        }
    }
}