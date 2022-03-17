package com.example.posyanduapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.posyanduapp.R
import com.example.posyanduapp.ui.LoginActivity
import com.example.posyanduapp.ui.orangtua.NotifikasiActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class FirebaseNotification : FirebaseMessagingService() {

    var context: Context? = null


    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val pesan: String? = message.data["body"]
        val title: String? = message.data["title"]
        issueNotification(pesan, title)
        Log.d("ISINYA","pesan: $pesan , judul : $title")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("SellerFirebaseService ", "Refreshed token :: $token")
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun makeNotificationChannel() {
        val channel = NotificationChannel(
            "Posyandu",
            "Posyandu Channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.setShowBadge(true) // set false to disable badges, Oreo exclusive
        val notificationManager = (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
        notificationManager.createNotificationChannel(channel)
    }

    private fun issueNotification(message: String?, title: String??) {

        val requestID = System.currentTimeMillis().toInt()
        // make the channel. The method has been discussed before.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            makeNotificationChannel()
        }
        // the check ensures that the channel will only be made
        // if the device is running Android 8+
        val notification: NotificationCompat.Builder = NotificationCompat.Builder(this, "Posyandu")
        // the second parameter is the channel id.
        // it should be the same as passed to the makeNotificationChannel() method
        val notificationIntent = Intent(applicationContext, NotifikasiActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val contentIntent = PendingIntent.getActivity(
            this,
            requestID,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        notification
            .setStyle(NotificationCompat.BigTextStyle())
            .setSmallIcon(R.drawable.logoposyandu) // can use any other icon
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setContentIntent(contentIntent)
            .setSound(alarmSound)
            .setPriority(NotificationCompat.PRIORITY_MAX)

            .setNumber(1) // this shows a number in the notification dots
        val notificationManager = (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
        notificationManager.notify(1, notification.build())
        // it is better to not use 0 as notification id, so used 1.
    }
}