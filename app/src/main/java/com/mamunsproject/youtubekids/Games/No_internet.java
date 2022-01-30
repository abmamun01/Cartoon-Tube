package com.mamunsproject.youtubekids.Games;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.mamunsproject.youtubekids.R;

public class No_internet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        ImageView btn = (ImageView)findViewById(R.id.retry);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(No_internet.this, Games_MainActivity.class);
                startActivity(i);
            }
        });
    }
}