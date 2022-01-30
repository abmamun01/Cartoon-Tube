package com.mamunsproject.youtubekids.Games;

import static com.facebook.ads.AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mamunsproject.youtubekids.MainActivity;
import com.mamunsproject.youtubekids.R;

public class Games_MainActivity extends AppCompatActivity {


    RelativeLayout home, category, contact;
    ImageView mp1, mp2, mp3, mp4, mp5, mp6, btmnav;
    String mp1loader, mp2loader, mp3loader, mp4loader, mp5loader, mp6loader;
    CardView pop1, pop2, pop3, pop4, pop5;
    String p1loader, p2loader, p3loader, p4loader, p5loader;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;


    public static AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_main);

        AdSettings.setIntegrationErrorMode(INTEGRATION_ERROR_CRASH_DEBUG_MODE);
        AudienceNetworkAds.initialize(this);

        //===============================================FB BANNER AD============================================


        adView = new AdView(this, "761984221190315_941393986582670", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);


        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
// Ad error callback

                Log.d("Adslkfskadf", "onError: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
// Ad loaded callback
                Log.d("Adslkfskadf", "onAdLoaded: ");

            }

            @Override
            public void onAdClicked(Ad ad) {
// Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
// Ad impression logged callback
            }
        };

        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());

        // Request an ad
        adView.loadAd();

//===============================================FB BANNER AD============================================







        //bottom dialogue
        btmnav = (ImageView) findViewById(R.id.bottom_nav);
        btmnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });


        //if internet is not available
        if (checkInternet() != true) {
            Intent i = new Intent(Games_MainActivity.this, No_internet.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        }


        //most play items
        mp1loader = "https://games.cdn.famobi.com/html5games/o/om-nom-run/v1140/?fg_domain=play.famobi.com&fg_aid=A1000-1&fg_uid=abe80572-560a-444d-baf7-2fa4a7b2c02f&fg_pid=4638e320-4444-4514-81c4-d80a8c662371&fg_beat=357&original_ref=https%3A%2F%2Fhtml5games.com%2F";
        mp1 = (ImageView) findViewById(R.id.mostplaygame1);
        mp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Games_MainActivity.this, GameLoader.class);
                intent.putExtra("WEB_PASSING", mp1loader);
                startActivity(intent);
            }
        });


        mp2loader = "https://games.cdn.famobi.com/html5games/d/diamond-rush/v140/?fg_domain=play.famobi.com&fg_aid=A1000-1&fg_uid=395b0746-6d0e-4ec8-b11c-18cab8009214&fg_pid=4638e320-4444-4514-81c4-d80a8c662371&fg_beat=363&original_ref=https%3A%2F%2Fhtml5games.com%2F";
        mp2 = (ImageView) findViewById(R.id.mostplaygame2);
        mp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Games_MainActivity.this, GameLoader.class);
                intent.putExtra("WEB_PASSING", mp2loader);
                startActivity(intent);
            }
        });


        mp3loader = "https://games.cdn.famobi.com/html5games/t/table-tennis-world-tour/v240/?fg_domain=play.famobi.com&fg_aid=A1000-1&fg_uid=8c8e5fc6-7e38-47ab-96eb-150276e79ef8&fg_pid=4638e320-4444-4514-81c4-d80a8c662371&fg_beat=356&original_ref=https%3A%2F%2Fhtml5games.com%2F";
        mp3 = (ImageView) findViewById(R.id.mostplaygame3);
        mp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Games_MainActivity.this, GameLoader.class);
                intent.putExtra("WEB_PASSING", mp3loader);
                startActivity(intent);
            }
        });


        mp4loader = "https://games.cdn.famobi.com/html5games/a/archery-world-tour/v460/?fg_domain=play.famobi.com&fg_aid=A1000-1&fg_uid=ebcb987a-3fd0-4b4a-bae6-f456fff9cac3&fg_pid=4638e320-4444-4514-81c4-d80a8c662371&fg_beat=357&original_ref=https%3A%2F%2Fhtml5games.com%2F";
        mp4 = (ImageView) findViewById(R.id.mostplaygame4);
        mp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Games_MainActivity.this, GameLoader.class);
                intent.putExtra("WEB_PASSING", mp4loader);
                startActivity(intent);
            }
        });


        mp5loader = "https://games.cdn.famobi.com/html5games/0/3d-free-kick/v080/?fg_domain=play.famobi.com&fg_aid=A1000-1&fg_uid=2ee096ab-4cd7-4f9a-baa9-f58a54413c47&fg_pid=4638e320-4444-4514-81c4-d80a8c662371&fg_beat=361&original_ref=https%3A%2F%2Fhtml5games.com%2F";
        mp5 = (ImageView) findViewById(R.id.mostplaygame5);
        mp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Games_MainActivity.this, GameLoader.class);
                intent.putExtra("WEB_PASSING", mp5loader);
                startActivity(intent);
            }
        });


        mp6loader = "https://games.cdn.famobi.com/html5games/0/8-ball-billiards-classic/v240/?fg_domain=play.famobi.com&fg_aid=A1000-1&fg_uid=82038e98-d4e1-46dd-8de9-1460d1395eab&fg_pid=4638e320-4444-4514-81c4-d80a8c662371&fg_beat=358&original_ref=https%3A%2F%2Fhtml5games.com%2F";
        mp6 = (ImageView) findViewById(R.id.mostplaygame6);
        mp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Games_MainActivity.this, GameLoader.class);
                intent.putExtra("WEB_PASSING", mp6loader);
                startActivity(intent);
            }
        });


        //popular item

        //item declaration
        pop1 = (CardView) findViewById(R.id.popular1);
        pop2 = (CardView) findViewById(R.id.popular2);
        pop3 = (CardView) findViewById(R.id.popular3);
        pop4 = (CardView) findViewById(R.id.popular4);
        pop5 = (CardView) findViewById(R.id.popular5);


        //loading url
        p1loader = "https://games.cdn.famobi.com/html5games/b/bubble-tower-3d/v050/?fg_domain=play.famobi.com&fg_aid=A1000-1&fg_uid=1385d98a-b5f2-4339-bce7-b99a8dd2e8b0&fg_pid=4638e320-4444-4514-81c4-d80a8c662371&fg_beat=597&original_ref=https%3A%2F%2Fhtml5games.com%2F";
        p2loader = "https://games.cdn.famobi.com/html5games/t/tower-crash-3d/v290/?fg_domain=play.famobi.com&fg_aid=A1000-1&fg_uid=68a7c06e-ec27-4fa4-99a5-a5e9702ac4ef&fg_pid=4638e320-4444-4514-81c4-d80a8c662371&fg_beat=601&original_ref=https%3A%2F%2Fhtml5games.com%2F";
        p3loader = "https://games.cdn.famobi.com/html5games/e/element-blocks/v160/?fg_domain=play.famobi.com&fg_aid=A1000-1&fg_uid=8c70b6c7-5792-4c40-b891-496eef2fb5ed&fg_pid=4638e320-4444-4514-81c4-d80a8c662371&fg_beat=600&original_ref=https%3A%2F%2Fhtml5games.com%2F\n";
        p4loader = "https://games.cdn.famobi.com/html5games/m/moto-x3m-pool-party/v080/?fg_domain=play.famobi.com&fg_aid=A1000-1&fg_uid=b2102250-eb51-4704-9cab-ff296491cc68&fg_pid=4638e320-4444-4514-81c4-d80a8c662371&fg_beat=603&original_ref=https%3A%2F%2Fhtml5games.com%2F";
        p5loader = "https://games.cdn.famobi.com/html5games/g/gold-mine/v200/?fg_domain=play.famobi.com&fg_aid=A1000-1&fg_uid=b358f423-2ad1-4f9b-b81f-6118ee9778a9&fg_pid=4638e320-4444-4514-81c4-d80a8c662371&fg_beat=604&original_ref=https%3A%2F%2Fhtml5games.com%2F";


        //onclick

        pop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Games_MainActivity.this, GameLoader.class);
                intent.putExtra("WEB_PASSING", p1loader);
                startActivity(intent);

            }
        });
        pop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Games_MainActivity.this, GameLoader.class);
                intent.putExtra("WEB_PASSING", p2loader);
                startActivity(intent);

            }
        });
        pop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Games_MainActivity.this, GameLoader.class);
                intent.putExtra("WEB_PASSING", p3loader);
                startActivity(intent);

            }
        });
        pop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Games_MainActivity.this, GameLoader.class);
                intent.putExtra("WEB_PASSING", p4loader);
                startActivity(intent);

            }
        });
        pop5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Games_MainActivity.this, GameLoader.class);
                intent.putExtra("WEB_PASSING", p5loader);
                startActivity(intent);

            }
        });


        //bottom navigation setup
        home = (RelativeLayout) findViewById(R.id.home);
        category = (RelativeLayout) findViewById(R.id.category);
        contact = (RelativeLayout) findViewById(R.id.contact);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Games_MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
            }
        });
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(Games_MainActivity.this, Category.class);
                startActivity(s);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(Games_MainActivity.this, Contact.class);
                startActivity(g);
            }
        });


        //Chceking it's first time or not
/*        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();
        if (isItFirestTime()) {

           Intent i = new Intent(Games_MainActivity.this, Screen_one.class);
           startActivity(i);
        } else {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }*/


    }

    public boolean isItFirestTime() {
        if (sharedPreferences.getBoolean("firstTime", true)) {
            sharedEditor.putBoolean("firstTime", false);
            sharedEditor.commit();
            sharedEditor.apply();
            return true;
        } else {
            return false;
        }

    }


    public boolean checkInternet() {

        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();

    }


    //BottomSheet implimentation
    private void showBottomSheetDialog() {


        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout);

        LinearLayout share = bottomSheetDialog.findViewById(R.id.share);
        LinearLayout rating = bottomSheetDialog.findViewById(R.id.rating);
        LinearLayout contact = bottomSheetDialog.findViewById(R.id.contact);

        //not implimented yet
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Games_MainActivity.this, "Share is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Games_MainActivity.this, "Rating is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Games_MainActivity.this, "Contact is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        bottomSheetDialog.show();


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        Animatoo.animateSlideRight(Games_MainActivity.this);

    }
}