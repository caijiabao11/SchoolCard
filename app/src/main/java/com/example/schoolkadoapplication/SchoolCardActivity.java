package com.example.schoolkadoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SchoolCardActivity extends AppCompatActivity{
    private TextView mTextview,mTv,mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolcard);
        bindViews();
        intoTime();

        //接收传参
        Intent getIntent = getIntent();
        String mName = getIntent.getStringExtra("name");
        String mNumber = getIntent.getStringExtra("number");
        String mStatus = getIntent.getStringExtra("status");
        if (mStatus.equals("进校")) {
            mTv.setText(mStatus+"："+"学生:"+mName+"-"+mNumber+",白名单验证通过");
            mTitle.setText(mStatus);
        }else{
            mTv.setText(mStatus+"："+"学生:"+mName+"-"+mNumber+",白名单验证通过,该申请离校扫码1次，离校码只能使用一次，请不要重复打开");
            mTitle.setText(mStatus);
        }



    }
    private void intoTime() {
        new Thread(){
            @Override
            public void run(){
                do {
                    try{
                        Thread.sleep(100);
                        Message message = new Message();
                        message.what=1;
                        handler.sendMessage(message);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }while (true);
            }
        }.start();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
//                    long time = System.currentTimeMillis();
//                    CharSequence format  = DateFormat.format(".SSS",time);
//                    mTextview.setText(format);
                    SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss.SSS");
                    Date date = new Date(System.currentTimeMillis());
                    mTextview.setText(formatter.format(date));
                    break;
            }
        }
    };

    private void bindViews() {
        mTextview = findViewById(R.id.tt);
        mTv = findViewById(R.id.textView);
        mTitle = findViewById(R.id.tit);
    }
}
