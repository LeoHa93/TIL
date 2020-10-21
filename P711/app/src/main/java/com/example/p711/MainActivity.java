package com.example.p711;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    NotificationManager manager;
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx = findViewById(R.id.tx);
        FirebaseMessaging.getInstance().subscribeToTopic("car").addOnCompleteListener(new OnCompleteListener<Void>() {  //eclipse와 연동 되는 것 : "car"
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String msg = "FCM COMPLETE > > > ";
                if(!task.isSuccessful()){
                    msg = "FCM FAIL";
                }
                Log.d("[TAG: ]", msg);
            }
        });
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(receiver, new IntentFilter("notification"));
    }   //END onCreate()
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null){
                String title = intent.getStringExtra("title");
                String control = intent.getStringExtra("control");
                String data = intent.getStringExtra("data");
                Toast.makeText(MainActivity.this, title + " and this is test for toast " + control + " " + data, Toast.LENGTH_SHORT).show();

                alert();

                tx.setText(title + " " + control + " " + data);
            }
        }
    };
    public void alert(){
        //VIBRATOR PART
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(Build.VERSION.SDK_INT>=26){
            vibrator.vibrate(VibrationEffect.createOneShot(1000, 10));
        }else{
            vibrator.vibrate(1000);
        }

        //ALERT PART
        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder  builder = null;
        if(Build.VERSION.SDK_INT>=26){
            if(manager.getNotificationChannel("ch1") == null){
                manager.createNotificationChannel(new NotificationChannel("ch1", "chname", NotificationManager.IMPORTANCE_DEFAULT));
            }
            builder = new NotificationCompat.Builder(this, "ch1");
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        builder.setContentTitle("P711 Notification");
        builder.setContentText(">>YOU HAVE ALERT<<");
        builder.setSmallIcon(R.drawable.gold);
        Notification noti = builder.build();
        manager.notify(1, noti);

    }
}