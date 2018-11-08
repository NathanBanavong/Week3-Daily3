package com.example.consultants.week3_daily3;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static com.example.consultants.week3_daily3.App.CHANNEL_ID;

public class MyMusicService extends Service {

//    public static String START_ACTION = "StartForground";
//    public static String STOP_ACTION = "StopForground";
//    public static String PREV_ACTION = "previous";
//    public static String PLAY_ACTION = "play";
//    public static String NEXT_ACTION = "next";

    public static final String TAG = MyMusicService.class.getSimpleName() + "_TAG";
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        String input = intent.getStringExtra("musicInput");

        Intent notificationIntent = new Intent(this, MainActivity.class);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.like_a_boss);
        mediaPlayer.start();

        //Test this -> pass intent and string
        //works
        notificationView(notificationIntent, input);

        //TODO look at other possibilities with this pass -> need to pause
        return START_NOT_STICKY;
    }

    //original notification method
    public void notificationView(Intent notificationIntent, String input) {

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,
                notificationIntent,
                0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Music Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_music_note)
                .setContentIntent(pendingIntent)
//                test this
//                .setOngoing(true)
                .addAction(android.R.drawable.ic_media_previous,
                        "Previous",
                        pendingIntent)
                .addAction(android.R.drawable.ic_media_play,
                        "Play",
                        pendingIntent)
                .addAction(android.R.drawable.ic_media_next,
                        "Next",
                        pendingIntent)
//
                .build();
//        start the intent -> promote to foreground
        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //release the song when user interacts
        mediaPlayer.release();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

}
