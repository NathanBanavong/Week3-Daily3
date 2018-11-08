package com.example.consultants.week3_daily3;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String START_ACTION = "StartForground";
    public static String STOP_ACTION = "StopForground";


    private TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onMusicService(View view) {
        String leTitle;
        tvMain = findViewById(R.id.tvMain);
        leTitle = tvMain.getText().toString();

        Intent musicIntent = new Intent(MainActivity.this, MyMusicService.class);
        musicIntent.putExtra("musicInput", leTitle);

        switch (view.getId()) {

            case R.id.btnStartMusic:
////                -----------------------------------------------------
//                Intent startIntent = new Intent(MainActivity.this, MyMusicService.class);
//                startIntent.setAction(MainActivity.START_ACTION);
//                startService(startIntent);
////                -----------------------------------------------------
//              Original Work
                startService(musicIntent);
                break;

            case R.id.btnStopMusic:
////                -----------------------------------------------------
//                Intent stopIntent = new Intent(MainActivity.this, MyMusicService.class);
//                stopIntent.setAction(MainActivity.STOP_ACTION);
//                stopService(stopIntent);
////                -----------------------------------------------------
//                original work
                stopService(musicIntent);
                break;

        }

    }
}
