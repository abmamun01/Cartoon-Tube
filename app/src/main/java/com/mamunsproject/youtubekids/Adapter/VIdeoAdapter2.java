package com.mamunsproject.youtubekids.Adapter;


import static com.mamunsproject.youtubekids.MainActivity.MainActinterstitialAd;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.mamunsproject.youtubekids.MainActivity;
import com.mamunsproject.youtubekids.Model.TypeThumbnail;
import com.mamunsproject.youtubekids.Model.Video;
import com.mamunsproject.youtubekids.PlayerActivity;
import com.mamunsproject.youtubekids.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class VIdeoAdapter2 extends RecyclerView.Adapter<VIdeoAdapter2.VideoHolder> {

    Context context;
    ArrayList<Video> arrayListVideo;

    AdView adView, adview2;

    public VIdeoAdapter2(Context context, ArrayList<Video> arrayListVideo) {
        this.context = context;
        this.arrayListVideo = arrayListVideo;
    }

    public ArrayList<Video> getArrayListVideo() {
        return arrayListVideo;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sample_recycler, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {


        if (position % 3 == 0) {
            holder.medium_ads.setVisibility(View.VISIBLE);
            holder.banner2__holder.setVisibility(View.VISIBLE);
            adView = new AdView(context, "761984221190315_941394439915958", AdSize.RECTANGLE_HEIGHT_250);
            LinearLayout adContainer = holder.itemView.findViewById(R.id.medium_rectangle_holder);

            adview2 = new AdView(context, "761984221190315_948307495891319", AdSize.BANNER_HEIGHT_50);
            LinearLayout adContainer2 = holder.itemView.findViewById(R.id.banner2__holder);

            // Add the ad view to your activity layout
            adContainer.addView(adView);
            adContainer2.addView(adview2);

            adView.loadAd(adView.buildLoadAdConfig().build());
            adview2.loadAd(adView.buildLoadAdConfig().build());
            // Request an ad
            if (adView.isAdInvalidated()) {
                adView.loadAd();

            }

            if (adview2.isAdInvalidated()) {
                adview2.loadAd();

            }
        }

        Video video = arrayListVideo.get(position);


        if (video != null) {

            holder.textViewTitle.setText(video.snippet.title);
            if (video.snippet.thumbnails != null) {
                TypeThumbnail t = video.snippet.thumbnails.high;
                if (t == null) t = video.snippet.thumbnails.medium;
                if (t == null) t = video.snippet.thumbnails.standard;

                if (t != null) {
                    Glide.with(context).load(t.url).into(holder.imageViewThumbnail);

                } else {
                    Toast.makeText(context, "No Image Available!", Toast.LENGTH_SHORT).show();
                }


                //  Glide.with(context).load(t.url).into(holder.imageViewThumbnail);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String id_ = video.contentDetails.videoId;
                        String title_ = video.snippet.title;
                        Intent intent = new Intent(context, PlayerActivity.class);
                        intent.putExtra("videoid", id_);
                        intent.putExtra("title_id", title_);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);


                        if (MainActinterstitialAd.isAdInvalidated()) {
                            MainActinterstitialAd.loadAd();
                        } else {

                            loadIronSourceInterstitial();
                        }


                    }
                });


            }
        }
    }

    @Override
    public int getItemCount() {
        return arrayListVideo.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.tv_item_title)
//        TextView textViewTitle;
//        @BindView(R.id.iv_item_cover)
//        ImageView imageViewThumbnail;

        TextView textViewTitle;
        ImageView imageViewThumbnail;
        RelativeLayout relativeTransitionId;
        LinearLayout medium_ads;
        LinearLayout banner2__holder;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            textViewTitle = itemView.findViewById(R.id.tv_item_title);
            imageViewThumbnail = itemView.findViewById(R.id.iv_item_cover);
            relativeTransitionId = itemView.findViewById(R.id.relativeTranstionId);
            medium_ads = itemView.findViewById(R.id.medium_rectangle_holder);
            banner2__holder = itemView.findViewById(R.id.banner2__holder);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
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
