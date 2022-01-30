package com.mamunsproject.youtubekids;

import static com.facebook.ads.AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.ironsource.mediationsdk.IronSource;
import com.mamunsproject.youtubekids.Fragment.Home_Fragment;
import com.mamunsproject.youtubekids.Fragment.PlayList_Fragment;
import com.mamunsproject.youtubekids.Games.Games_MainActivity;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;


public class MainActivity extends AppCompatActivity {

    SmoothBottomBar bottomNavigationView;
    public AdView adView;
    public static InterstitialAd MainActinterstitialAd;
    MeowBottomNavigation bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdSettings.setIntegrationErrorMode(INTEGRATION_ERROR_CRASH_DEBUG_MODE);
        AudienceNetworkAds.initialize(this);
        IronSource.init(this, "12d83d5dd");
        checkConnection();
//===============================================FB BANNER AD============================================


/*
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
*/

//===============================================FB BANNER AD============================================


//===============================================FB INTERSTITIAL AD============================================
        MainActinterstitialAd = new InterstitialAd(getApplicationContext(), "761984221190315_942699579785444");

        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e("KFJLKSDF", "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e("KFJLKSDF", "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("KFJLKSDF", "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("KFJLKSDF", "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                MainActinterstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d("KFJLKSDF", "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d("KFJLKSDF", "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        MainActinterstitialAd.loadAd(
                MainActinterstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());


//===============================================FB INTERSTITIAL AD============================================

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerID, new Home_Fragment()).commit();


//===============================================MEO BOTTOM NAV============================================

        bottomNavigation = findViewById(R.id.bottom_navigation_id);


        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_gamecon));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_playlist));


        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        fragment = new Home_Fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerID, fragment).commit();

                        break;

                    case 2:
                        startActivity(new Intent(getApplicationContext(), Games_MainActivity.class));
                        Animatoo.animateSlideLeft(MainActivity.this);
                        break;

                    case 3:
                        fragment = new PlayList_Fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerID, fragment).commit();

                        break;
                }


            }
        });

        // bottomNavigation.setCount(1, "5");
        bottomNavigation.show(1, true);


        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

                // Toast.makeText(getApplicationContext(), "Clicked"+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {


                Log.d("RESELECTD", "ITems : " + item.getId());
            }
        });


//===============================================MEO BOTTOM NAV============================================


        //  bottomNavigationView = findViewById(R.id.bottomBar);



/*
        bottomNavigationView.setOnItemSelectedListener(i -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            switch (i) {


                case 0:
                    transaction.replace(R.id.fragmentContainerID, new Home_Fragment());
                    transaction.commit();
                    break;


                case 1:
                    transaction.replace(R.id.fragmentContainerID, new PlayList_Fragment());
                    transaction.commit();
                    break;


            }

            return false;

        });*/


    }

    public void checkConnection() {

        ConnectivityManager manager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (null != activeNetwork) {

            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

            }
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

            }


        } else {


            new AlertDialog.Builder(this)
                    .setTitle("No Internet Connection!")
                    .setMessage("You Have To Turn On Your Mobile Data or Wifi!")
                    .setCancelable(false)

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        // Continue with delete operation

                        checkConnection();
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(R.drawable.ic_warn)
                    .show();


        }

    }


    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
    }

}























































