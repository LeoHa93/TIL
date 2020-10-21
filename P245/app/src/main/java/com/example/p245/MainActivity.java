package com.example.p245;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
    }
    public void controller(View v){
        if(v.getId() == R.id.button1){
            login();
        }
        else if(v.getId() == R.id.button2){
            register();
        }
        else if(v.getId() == R.id.button3){
            detail();
        }
        else if(v.getId() == R.id.button4){
            userList();
        }
    }
    public void login(){
        container.removeAllViews();
        LayoutInflater inflater;
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sub1, container,true);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                ProgressBar progressBar;
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
    public void register(){
        container.removeAllViews();
        final LayoutInflater inflater;
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sub2, container,true);
        Button sub2_btn = (Button) findViewById(R.id.sub2_btn);
        sub2_btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder();
                builder.setTitle("WANNA REGISTER?");
                builder.setMessage("WANNA REGISTER?");
                builder.setIcon(R.drawable.d1);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
    public void detail(){}
    public void userList(){
        ScrollView scrollView;
        ImageView imageView;
        BitmapDrawable bitmap;

        container.removeAllViews();
        LayoutInflater inflater;
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sub4, container,true);

        scrollView=findViewById(R.id.scrollView);
        imageView=findViewById(R.id.imageView1);
        scrollView.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.d);
        int bitmapWidth = bitmap.getIntrinsicWidth();

//        int bitmapHeight = bitmap.getIntrinsicHeight();
        int bitmapHeight = 1800;

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;

    }

}