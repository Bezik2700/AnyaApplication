package com.example.anyaapplication.setting.room

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.anyaapplication.setting.WorkerFromNotifications
import java.util.concurrent.TimeUnit

class App: Application(){

    val database by lazy { MainDb.createDataBase(this) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        val channel = NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_DEFAULT)

        val notificationManager = getSystemService(NotificationManager::class.java)

        notificationManager.createNotificationChannel(channel)

        val periodicWorkRequest: WorkRequest = PeriodicWorkRequest.Builder(
            WorkerFromNotifications::class.java, 1, TimeUnit.DAYS)
            .build()

        Log.i("WorkManager",periodicWorkRequest.toString())
        WorkManager.getInstance(this).enqueue(periodicWorkRequest)
    }
}

@Database(entities = [NameEntity:: class], version = 1)
abstract class MainDb: RoomDatabase() {
    abstract val dao: Dao
    companion object {
        fun createDataBase(context: Context): MainDb{
            return Room.databaseBuilder(
                context,
                MainDb::class.java,
                "MainDb"
            ).build()
        }
    }
}