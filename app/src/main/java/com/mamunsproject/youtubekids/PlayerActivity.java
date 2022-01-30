package com.mamunsproject.youtubekids;


import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;


import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.mamunsproject.youtubekids.Adapter.VideoRecyclerAdapter;
import com.mamunsproject.youtubekids.Model.SearchModel.Item;
import com.mamunsproject.youtubekids.Model.SearchModel.SOAnswersResponse;
import com.mamunsproject.youtubekids.Retrofit.YoutubeAPI;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerActivity extends AppCompatActivity {

    YouTubePlayerView youTubePlayerView;
    String videoId, title_, videoIdAfterRecreate, titleAfterRecreate;
    Intent intent;
    private InterstitialAd interstitialAd;
    private VideoRecyclerAdapter adapter;
    private List<Item> videosForSearch;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        // lock the current device orientation



        intent = getIntent();
        videoId = intent.getStringExtra("videoid");
        title_ = intent.getStringExtra("title_id");
        Log.d("SPOLISKJFI", "NON SPLIT  : " + title_);

        videoIdAfterRecreate = intent.getStringExtra("videoIdAfterRecreate");
        titleAfterRecreate = intent.getStringExtra("titleAfterRecreate");

        Log.d("SPOLISKJFI", "titleAfterRecreate   : " + videoIdAfterRecreate);


        videosForSearch = new ArrayList<Item>();
        adapter = new VideoRecyclerAdapter(videosForSearch);
        recyclerView = findViewById(R.id.player_RV);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(adapter);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        //    youTubePlayerView.initialize("AIzaSyBQe0__ggAbF_iz5AUcTrq7G8Fjaj7mhcQ", this);

        Log.d("SPOLISKJFI", "videoIdAfterRecreate   : " + videoIdAfterRecreate);
        Log.d("SPOLISKJFI", "Video ID after RECREATE  : " + videoId);


        if (videoId != null) {
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });
        } else {

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                    Log.d("SPOLISKJFI", "Video ID after RECREATE OnPlayer : " + videoIdAfterRecreate);

                    youTubePlayer.loadVideo(videoIdAfterRecreate, 0);
                }
            });

        }


        try {

            if (title_ != null) {

                Log.d("SPOLISKJFI", "Title Before Create  : " + title_);

                String[] words = title_.trim().split(" ");

                if (words.length >= 4) {
                    String fourWordsName = words[0] + " " + words[1] + " " + words[2] + " " + words[3];

                    getSearchedVideos(fourWordsName);

                    Log.d("SPOLISKJFI", "SPLIT  : " + fourWordsName);
                } else if (words.length >= 3) {
                    String fourWordsName = words[0] + " " + words[1] + " " + words[2];

                    getSearchedVideos(fourWordsName);

                    Log.d("SPOLISKJFI", "SPLIT  : " + fourWordsName);
                } else if (words.length >= 2) {
                    String fourWordsName = words[0] + " " + words[1];

                    getSearchedVideos(fourWordsName);

                    Log.d("SPOLISKJFI", "SPLIT  : " + fourWordsName);
                }

            } else {


                String[] words = titleAfterRecreate.trim().split(" ");

                Log.d("SPOLISKJFI", "titleAfterRecreate  : " + titleAfterRecreate);

                if (words.length >= 4) {
                    String fourWordsName = words[0] + " " + words[1] + " " + words[2] + " " + words[3];

                    getSearchedVideos(fourWordsName);

                    Log.d("SPOLISKJFI", "SPLIT  : " + fourWordsName);
                } else if (words.length >= 3) {
                    String fourWordsName = words[0] + " " + words[1] + " " + words[2];

                    getSearchedVideos(fourWordsName);

                    Log.d("SPOLISKJFI", "SPLIT  : " + fourWordsName);
                } else if (words.length >= 2) {
                    String fourWordsName = words[0] + " " + words[1];

                    getSearchedVideos(fourWordsName);

                    Log.d("SPOLISKJFI", "SPLIT  : " + fourWordsName);
                }
            }

        } catch (Exception e) {

        }


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

                // loadIronSourceInterstitial();

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


        ScheduledExecutorService scheduledExecutorService
                = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(() -> {

                    if (interstitialAd.isAdInvalidated()) {
                        interstitialAd.loadAd();
                    } else {
                        loadIronSourceInterstitial();
                    }

                });
            }
        }, 210, 210, TimeUnit.SECONDS);


//===============================================FB INTERSTITIAL AD============================================


    }

    @Override
    protected void onResume() {
        super.onResume();


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, (view, position) -> {
                    Log.d("SPOLISKJFI", "SPLIT  : " + position);

                    Intent i = new Intent(PlayerActivity.this, PlayerActivity.class);  //your class
                    i.putExtra("videoIdAfterRecreate", videosForSearch.get(position).getId().getVideoId());
                    i.putExtra("titleAfterRecreate", videosForSearch.get(position).getSnippet().getTitle());
                    startActivity(i);
                    finish();


                })
        );
    }




/*    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.setFullscreen(true);

        youTubePlayer.setPlayerStateChangeListener(this);
        youTubePlayer.setPlaybackEventListener(this);

        if (!b) {
            youTubePlayer.cueVideo(videoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }*/


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


    private void getSearchedVideos(String query) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YoutubeAPI youtubeAPI = retrofit.create(YoutubeAPI.class);
        Call<SOAnswersResponse> call = youtubeAPI.getAnswers("AIzaSyBQe0__ggAbF_iz5AUcTrq7G8Fjaj7mhcQ", "snippet", query, "video", 20);
        call.enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {

                try {
                    videosForSearch = response.body().getItems();
                    adapter.getVideos().clear();
                    adapter.getVideos().addAll(videosForSearch);
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {

                    Log.d("KKSKKJLKSDFJ", ":Error " + response.message() + "  Reason  ");

                }

            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
                // Toast.makeText(parent, "Network error: failed to load the videos.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}