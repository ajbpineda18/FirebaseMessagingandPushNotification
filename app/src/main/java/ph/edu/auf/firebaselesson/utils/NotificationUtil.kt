package ph.edu.auf.firebaselesson.utils

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import ph.edu.auf.firebaselesson.MainActivity
import ph.edu.auf.firebaselesson.R

private const val NOTIFICATION_ID = 1234

@SuppressLint("UnspecifiedImmutableFlag")
fun NotificationManager.sendNotification(messageBody: String, messageTitle: String, applicationContext: Context) {
    val contentIntent = Intent(applicationContext,MainActivity::class.java)

    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val aufImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.img_auf_cea
    )

    val bigPictureStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(aufImage)
        .bigLargeIcon(null)

    val builder = NotificationCompat.Builder(
        applicationContext,
        "main_channel"
    )
        .setSmallIcon(R.drawable.ic_notify)
        .setContentTitle(messageTitle)
        .setContentText(messageBody)
        .setContentIntent(pendingIntent)
        .setStyle(bigPictureStyle)
        .setLargeIcon(aufImage)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)

    notify(NOTIFICATION_ID,builder.build())
}