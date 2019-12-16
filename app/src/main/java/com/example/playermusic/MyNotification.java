package com.example.playermusic;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


//https://stackoverflow.com/questions/12526228/how-to-put-media-controller-button-on-notification-bar
public class MyNotification extends Notification {

    private static Context ctx;
    private NotificationManager mNotificationManager;
    public static Notification notification = null;

    @SuppressLint("NewApi")
    public MyNotification(Context pctx){
        super();

        /*
        *
        *
        * */

        ctx=pctx;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /*Intent notificationIntent = new Intent(ctx, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            Intent snoozeIntent = new Intent(ctx, com.example.playermusic.HelperActivity.class);
            //snoozeIntent.setAction(ACTION_SNOOZE);
            snoozeIntent.putExtra("DO", "app");
            PendingIntent snoozePendingIntent =
                    PendingIntent.getBroadcast(ctx, 0, snoozeIntent, 0);

            Notification builder = new NotificationCompat.Builder(ctx, "548853")

                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("My notification")
                    .setContentText("Hello World!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(contentIntent)
                    .addAction(R.mipmap.ic_launcher, "Play",
                            snoozePendingIntent).build();

            CharSequence name = "radio";
            String description = "Canal";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("548853", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = ctx.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);



            notificationManager.notify(12345678, builder);

            // Issue the notification.
            /*NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ctx);
            notificationManager.notify(548853, builder);*/



        } else {
            String ns = Context.NOTIFICATION_SERVICE;
            mNotificationManager = (NotificationManager) ctx.getSystemService(ns);
            CharSequence tickerText = "Shortcuts";
            long when = System.currentTimeMillis();
            Notification.Builder builder = new Notification.Builder(ctx);

            notification=builder.build();
            notification.when=when;
            notification.tickerText=tickerText;
            notification.icon=R.mipmap.ic_launcher;
            notification.visibility = VISIBILITY_PUBLIC;
            notification.priority = PRIORITY_MAX;


            setContentViewToNotfication();

        /*Intent notificationIntent = new Intent(ctx, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(ctx, 0,
                notificationIntent, 0);*/


            //notification.contentIntent = intent;
            notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
            notification.flags |= Notification.FLAG_NO_CLEAR;
            notification.flags |= Notification.FLAG_ONGOING_EVENT;
            CharSequence contentTitle = "From Shortcuts";
            mNotificationManager.notify(548853, notification);

        }





    }



    public static void setContentViewToNotfication() {
        RemoteViews contentView=new RemoteViews(ctx.getPackageName(), R.layout.notification_layout_pause );

        if (MyService.isInstanceCreated()) {
            contentView=new RemoteViews(ctx.getPackageName(), R.layout.notification_layout);
        }

        //set the button listeners
        setListeners(contentView);

        notification.contentView = contentView;
    }

    public static void setListeners(RemoteViews view){
        //radio listener
        /*Intent radio=new Intent(ctx,HelperActivity.class);
        radio.putExtra("DO", "radio");
        PendingIntent pRadio = PendingIntent.getActivity(ctx, 0, radio, 0);
        view.setOnClickPendingIntent(R.id.radio, pRadio);

        //volume listener
        Intent volume=new Intent(ctx, HelperActivity.class);
        volume.putExtra("DO", "volume");
        PendingIntent pVolume = PendingIntent.getActivity(ctx, 1, volume, 0);
        view.setOnClickPendingIntent(R.id.volume, pVolume);

        //reboot listener
        Intent reboot=new Intent(ctx, HelperActivity.class);
        reboot.putExtra("DO", "reboot");
        PendingIntent pReboot = PendingIntent.getActivity(ctx, 5, reboot, 0);
        view.setOnClickPendingIntent(R.id.reboot, pReboot);

        //top listener
        Intent top=new Intent(ctx, HelperActivity.class);
        top.putExtra("DO", "top");
        PendingIntent pTop = PendingIntent.getActivity(ctx, 3, top, 0);
        view.setOnClickPendingIntent(R.id.top, pTop); */

        //app listener
        Intent snoozeIntent = new Intent(ctx, com.example.playermusic.HelperActivity.class);
        //snoozeIntent.setAction(ACTION_SNOOZE);
        snoozeIntent.putExtra("DO", "app");
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(ctx, 0, snoozeIntent, 0);
        view.setOnClickPendingIntent(R.id.btn1, snoozePendingIntent);

        /*Intent app = new Intent(ctx, com.example.playermusic.HelperActivity.class);
        app.putExtra("DO", "app");
        PendingIntent pApp = PendingIntent.getActivity(ctx, 4, app, 0);
        view.setOnClickPendingIntent(R.id.btn1, pApp);*/


        Intent app2 = new Intent(ctx, com.example.playermusic.HelperActivity.class);
        app2.putExtra("DO", "stop");
        PendingIntent pApp2 = PendingIntent.getBroadcast(ctx, 5, app2, 0);
        view.setOnClickPendingIntent(R.id.btn2, pApp2);



    }

}
