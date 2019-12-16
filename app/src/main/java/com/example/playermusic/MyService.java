package com.example.playermusic;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;

public class MyService extends Service {
    //creating a mediaplayer object
    private MediaPlayer player;

    private SimpleExoPlayer player1;

    private static MyService instance = null;

    public static boolean isInstanceCreated() {
        return instance != null;
    }

    private static Context pctx;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //getting systems default ringtone





        instance = this;
        Context ctx = this;
        if (player1 == null) {
            startPlayer();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


                Intent notificationIntent = new Intent(ctx, MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                Intent snoozeIntent = new Intent(ctx, com.example.playermusic.HelperActivity.class);
                //snoozeIntent.setAction(ACTION_SNOOZE);
                snoozeIntent.putExtra("DO", "app");
                PendingIntent snoozePendingIntent =
                        PendingIntent.getBroadcast(ctx, 0, snoozeIntent, 0);


                RemoteViews contentView=new RemoteViews(ctx.getPackageName(), R.layout.notification_layout_pause );
                setListeners(contentView);

                Notification builder = new NotificationCompat.Builder(ctx, "548853")
                        .setContent(contentView)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setAutoCancel(false)
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
                notificationManager.deleteNotificationChannel("548854");
                notificationManager.createNotificationChannel(channel);



                //notificationManager.notify(12345678, builder);
                startForeground(1, builder);
            }

        }
        /*
        final Context ctx = this;
        player = new MediaPlayer();
        Uri uri = Uri.parse("http://stm01.virtualcast.com.br:8176/live");*/

        /*try {
            player.setDataSource("http://stm01.virtualcast.com.br:8176/live");
            player.prepare();
        } catch (IOException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
            e.printStackTrace();
        }*/



        /*
        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.reset();
                return false;
            }
        });

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        try {
            player.setDataSource("http://stm01.virtualcast.com.br:8176/live");
            player.prepareAsync();
        } catch (IllegalArgumentException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
        } catch (IllegalStateException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
        } catch (IOException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
        }

         */





        /*try {
            String url = "http://stm01.virtualcast.com.br:8176/live"; // your URL here

            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(url);
            player.prepare(); // might take long! (for buffering, etc)
        } catch (IllegalArgumentException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
        } catch (IllegalStateException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
        } catch (IOException e) {
            Log.d("DEBUG_RADIO", e.getMessage());
        }*/

        /*player = MediaPlayer.create(this,
                Settings.System.DEFAULT_RINGTONE_URI);
        //setting loop play to true
        //this will make the ringtone continuously playing
        player.setLooping(true);*/

        //staring the player
        //player.start();

        //we have some options for service
        //start sticky means service will be explicity started and stopped
        return START_STICKY;
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

    private void startPlayer() {
        /*Context ctx = this;
        player1  = new SimpleExoPlayer.Builder(ctx).build();
        String userAgent = Util.getUserAgent(ctx, "SimpleExoPlayer");
        Uri uri = Uri.parse("http://stm01.virtualcast.com.br:8176/live");
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory(
                userAgent, null,
                DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                true);
        // This is the MediaSource representing the media to be played.
        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);

        TrackSelector trackSelector = new DefaultTrackSelector();

        player1 = ExoPlayerFactory.newSimpleInstance(ctx, trackSelector);
        //player1.addListener((Player.EventListener) ctx);

        player1.prepare(mediaSource);
        player1.setPlayWhenReady(true); */


        Context ctx = this;

        String userAgent = Util.getUserAgent(ctx, "SimpleExoPlayer");
        Uri uri = Uri.parse("http://stm01.virtualcast.com.br:8176/live");
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory(
                userAgent, null,
                DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                true);
        // This is the MediaSource representing the media to be played.
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);

        TrackSelector trackSelector = new DefaultTrackSelector(ctx);

        player1  = new SimpleExoPlayer.Builder(ctx).setTrackSelector(trackSelector).build();
        //player1.set;
        //player1.addListener((Player.EventListener) ctx);

        player1.prepare(mediaSource);
        player1.setPlayWhenReady(true);
    }

    public void  showNotification() {
        Context ctx = this;
        //new MyNotification(ctx);
        //finish()
    }



    @Override
    public void onCreate()
    {
        super.onCreate();
        showNotification();
        pctx = this;
        instance = this;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showNotification();
        player1.stop();
        player1.release();
        instance = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent notificationIntent = new Intent(pctx, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(pctx, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            Intent snoozeIntent = new Intent(pctx, com.example.playermusic.HelperActivity.class);
            //snoozeIntent.setAction(ACTION_SNOOZE);
            snoozeIntent.putExtra("DO", "app");
            PendingIntent snoozePendingIntent =
                    PendingIntent.getBroadcast(pctx, 0, snoozeIntent, 0);


            RemoteViews contentView=new RemoteViews(pctx.getPackageName(), R.layout.notification_layout );
            setListeners(contentView);

            Notification builder = new NotificationCompat.Builder(pctx, "548854")
                    .setContent(contentView)
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
            NotificationChannel channel = new NotificationChannel("548854", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = pctx.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);



            notificationManager.notify(12345679, builder);
            //startForeground(1, builder);
        }

        //stopping the player when service is destroyed
//        player.stop();
    }
}
