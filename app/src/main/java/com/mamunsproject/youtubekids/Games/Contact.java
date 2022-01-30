package com.mamunsproject.youtubekids.Games;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.mamunsproject.youtubekids.MainActivity;
import com.mamunsproject.youtubekids.R;

public class Contact extends AppCompatActivity {

    RelativeLayout home, category, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        //bottom navigation setup
        home = (RelativeLayout) findViewById(R.id.home);
        category = (RelativeLayout) findViewById(R.id.category);
        contact = (RelativeLayout) findViewById(R.id.contact);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent h = new Intent(Contact.this, Games_MainActivity.class);
                startActivity(h);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(Contact.this, Category.class);
                startActivity(c);


            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Contact.this, "Contact", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        Animatoo.animateSlideRight(Contact.this);
    }
}