package com.mamunsproject.youtubekids.Games;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.mamunsproject.youtubekids.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameLoader extends AppCompatActivity {

    WebView web;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_loader);


        //===============================================FB INTERSTITIAL AD============================================

        interstitialAd = new InterstitialAd(getApplicationContext(), "761984221190315_941394306582638");


        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e("TAG", "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e("TAG", "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("TAG", "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("TAG", "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d("TAG", "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d("TAG", "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());


//===============================================FB INTERSTITIAL AD============================================


        String webAddress = getIntent().getStringExtra("WEB_PASSING");

        web = (WebView) findViewById(R.id.web);

        // loading url in the the WebView.
        web.loadUrl(webAddress);

        // this will enable the javascipt.
        web.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        web.setWebViewClient(new WebViewClient());

    }

    @Override
    public void onBackPressed() {


        if (interstitialAd.isAdInvalidated()) {
            interstitialAd.loadAd();
        } else if (IronSource.isInterstitialReady()) {

            loadIronSourceInterstitial();

        } else if (web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }


    }

    private void loadIronSourceInterstitial() {
        IronSource.loadInterstitial();
        IronSource.setInterstitialListener(new InterstitialListener() {
            @Override
            public void onInterstitialAdReady() {
                Log.d("onInterstitialAdReady", "onInterstitialAdReady: ");
                IronSource.showInterstitial();
            }

            @Override
            public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
                Log.d("onInterstitialAdReady", "onInterstitialAdLoadFailed: " + ironSourceError.getErrorMessage());

            }

            @Override
            public void onInterstitialAdOpened() {

            }

            @Override
            public void onInterstitialAdClosed() {

            }

            @Override
            public void onInterstitialAdShowSucceeded() {

            }

            @Override
            public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
                Log.d("onInterstitialAdReady", "ironSourceError: " + ironSourceError.getErrorMessage());

            }

            @Override
            public void onInterstitialAdClicked() {

            }
        });
    }

}