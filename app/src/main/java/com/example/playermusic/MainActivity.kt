package com.example.playermusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.os.Build
import android.R.id.message
import android.graphics.BitmapFactory

import android.app.Notification
import com.example.playermusic.MyNotification.notification
import android.content.ContentResolver
import android.app.NotificationManager
import android.app.NotificationChannel
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService

import androidx.core.app.ComponentActivity.ExtraData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.NotificationCompat












//https://stackoverflow.com/questions/48140010/android-media-player-streaming-not-working

//https://stackoverflow.com/questions/52473974/binding-playerview-with-simpleexoplayer-from-a-service

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonStart.setOnClickListener{



                startService(Intent(this, MyService::class.java))

            showNotification()
        }

        buttonStop.setOnClickListener{
            stopService(Intent(this, MyService::class.java))

            showNotification()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           /* val notification = NotificationCompat.Builder(this,"12345678")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setChannelId("12345678").build()

            val name = "nome"
            val description =  "desc"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("12345678", name, importance)


            val mNotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotificationManager.createNotificationChannel(channel)

// Issue the notification.
            mNotificationManager.notify(12345678, notification) */
        }

    }


    fun showNotification() {
        //MyNotification(this)
        //finish()
    }
}
