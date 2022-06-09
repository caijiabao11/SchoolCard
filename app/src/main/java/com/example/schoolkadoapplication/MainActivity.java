package com.example.schoolkadoapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;


public class MainActivity extends AppCompatActivity {
    private ImageView mImageView,mImageView_1;
    private EditText mName,mNumber;
    private Switch mSwitch;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        Glide.with(MainActivity.this)
                .load(R.drawable.main_bg2)
                .centerCrop()
                .into(mImageView_1);
        imgGenerationOnClick();

    }

    private void bindViews() {
        mName = findViewById(R.id.name);
        mNumber = findViewById(R.id.number);
        mSwitch = findViewById(R.id.switch1);
        mButton = findViewById(R.id.imgGeneration);
        mImageView = findViewById(R.id.main_bgimg);
        mImageView_1 = findViewById(R.id.main_bgimg1);
    }

    private void imgGenerationOnClick() {
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    mSwitch.setText("进校");
                    Glide.with(MainActivity.this)
                            .load(R.drawable.main_bg1)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .centerCrop()
                            .into(mImageView);
                    mImageView_1.setVisibility(View.INVISIBLE);
                    mImageView.setVisibility(View.VISIBLE);
                }else {
                    mSwitch.setText("出校");
                    Glide.with(MainActivity.this)
                            .load(R.drawable.main_bg2)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .centerCrop()
                            .into(mImageView_1);
                    mImageView_1.setVisibility(View.VISIBLE);
                    mImageView.setVisibility(View.INVISIBLE);
            }
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mName.getText().toString();
                String number = mNumber.getText().toString();
                String status = mSwitch.getText().toString();

                Intent intent = new Intent(MainActivity.this, SchoolCardActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("number",number);
                intent.putExtra("status",status);
                startActivity(intent);

            }
        });
    }


}
