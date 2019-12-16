package com.example.playermusic;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

//public class HelperActivity extends Activity {
public class HelperActivity extends BroadcastReceiver {

    private static Context pctx;

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = (String) intent.getExtras().get("DO");
        Log.d("DEBUG_RADIO","action = "+action);
        if (action.equals("app")) {
            //Your code

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent stopIntent = new Intent(context, MyService.class);
                stopIntent.setAction("stop");
                context.startForegroundService(stopIntent);
                context.startForegroundService(new Intent(context, MyService.class));
            } else {
                context.stopService(new Intent(context, MyService.class));
                context.startService(new Intent(context, MyService.class));
            }


            Log.d("DEBUG_RADIO","app foi chamado");
        } else if (action.equals("stop")) {

            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent stopIntent = new Intent(context, MyService.class);
                stopIntent.setAction("stop");
                context.startForegroundService(stopIntent);
            } else {*/
                context.stopService(new Intent(context, MyService.class));
            //}
            //context.stopService(new Intent(context, MyService.class));
            Log.d("DEBUG_RADIO","stop foi chamado");



        }
        //MyNotification.setContentViewToNotfication();
    }

    public static void setListeners(RemoteViews view){
        Context ctx = pctx;
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

    /*
    private HelperActivity ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG_RADIO","Helper foi chamado");

        super.onCreate(savedInstanceState);
        ctx = this;
        String action = (String) getIntent().getExtras().get("DO");
        if (action.equals("radio")) {
            //Your code
        } else if (action.equals("volume")) {
            //Your code
        } else if (action.equals("reboot")) {
            //Your code
        } else if (action.equals("top")) {
            //Your code
        } else if (action.equals("app")) {
            //Your code
            stopService(new Intent(ctx, MyService.class));
            startService(new Intent(ctx, MyService.class));
            Log.d("DEBUG_RADIO","app foi chamado");
        } else if (action.equals("stop")) {
            //Your code
            stopService(new Intent(ctx, MyService.class));
            Log.d("DEBUG_RADIO","stop foi chamado");
        }

        if (!action.equals("reboot"))
            finish();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }*/
}
