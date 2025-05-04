package com.example.anyaapplication.setting

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.anyaapplication.MainActivity
import com.example.anyaapplication.R
import kotlin.random.Random

class WorkerFromNotifications(
    context: Context,
    workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    private val notificationChannelID = "notification_channel_id"

    private val intent = Intent(applicationContext, MainActivity::class.java)

    private fun showSimpleNotification() {
        val notification = NotificationCompat.Builder(applicationContext, notificationChannelID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.appicon)
            .setContentIntent(PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE))
            .setAutoCancel(true)
            .build()
        notificationManager.notify(Random.nextInt(), notification)
    }

    override fun doWork(): Result {
        showSimpleNotification()
        return Result.success()
    }

    companion object {
        const val title = "Hello"
        const val message = "Delay time you"
    }
}