package com.mamunsproject.youtubekids.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mamunsproject.youtubekids.Adapter.VIdeoAdapter2;
import com.mamunsproject.youtubekids.Adapter.VideoRecyclerAdapter;
import com.mamunsproject.youtubekids.Model.ResponseVideo;
import com.mamunsproject.youtubekids.Model.SearchModel.Item;
import com.mamunsproject.youtubekids.Model.SearchModel.SOAnswersResponse;
import com.mamunsproject.youtubekids.Model.Video;
import com.mamunsproject.youtubekids.R;
import com.mamunsproject.youtubekids.Retrofit.ApiClient;
import com.mamunsproject.youtubekids.Retrofit.ApiInterface;
import com.mamunsproject.youtubekids.Retrofit.YoutubeAPI;
import com.mamunsproject.youtubekids.Utils.Constant;
import com.mamunsproject.youtubekids.Utils.MyConsts;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home_Fragment extends Fragment {


    VIdeoAdapter2 vIdeoAdapter;
    ArrayList<Video> arrayListVideo;
    ApiInterface apiInterface;
    ProgressBar progressBar;
    private List<ResponseVideo> videos;
    private VideoRecyclerAdapter adapter;
    String nextPageToken;
    ResponseVideo responseVideo;
    String key;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        progressBar = view.findViewById(R.id.progressBarID);
        videos = new ArrayList<ResponseVideo>();

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.
                collection("AllPlayListKEY").document("ALL_CARTOON_ID");


        documentReference
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()) {

                    key = documentSnapshot.getString("ALL_CARTOON_ID");
                    getVideos(key);


                } else {
                    Toast.makeText(getContext(), "Does'nt Exist", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(e -> {

        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerMain);
        Context context;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        arrayListVideo = new ArrayList<>();
        vIdeoAdapter = new VIdeoAdapter2(getContext(), arrayListVideo);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            Log.v("LastItemDetected", "Last Item Wow !");

                            // Do pagination.. i.e. fetch new data
                            if (responseVideo != null) {
                                nextPageToken = responseVideo.getPageToken();
                            }
                            Log.v("LastItemDetected", "nextPageToken!" + nextPageToken);
                            Log.v("LastItemDetected", "key!" + key);

                            getNextPageVideos(key, nextPageToken);

                            loading = true;
                        }
                    }
                }
            }
        });


        recyclerView.setAdapter(vIdeoAdapter);

        //    getSearchedVideos("");

    }

    private void getVideos(String key) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseVideo> callVideo = apiInterface.getAllVideos(3000, key
                , MyConsts.APIKEY);

        callVideo.enqueue(new Callback<ResponseVideo>() {
            @Override
            public void onResponse(Call<ResponseVideo> call, Response<ResponseVideo> response) {

                responseVideo = response.body();


                Log.d("GETPATGETOKEN", "onResponse: " + responseVideo.getPageToken());


                if (responseVideo != null) {
                    progressBar.setVisibility(View.GONE);
                    if (responseVideo.items.size() > 0) {
                        for (int i = 0; i < responseVideo.items.size(); i++) {
                            arrayListVideo.add(responseVideo.items.get(i));
                        }
                        vIdeoAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getContext(), "There is No Video In Your Channel!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseVideo> call, Throwable t) {

            }
        });

    }


    private void getNextPageVideos(String key, String pageToken) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseVideo> callVideo = apiInterface.getAllVideos(3000, key
                , MyConsts.APIKEY, pageToken);

        callVideo.enqueue(new Callback<ResponseVideo>() {
            @Override
            public void onResponse(Call<ResponseVideo> call, Response<ResponseVideo> response) {

                responseVideo = response.body();


                Log.d("GETPATGETOKEN", "onResponse: " + responseVideo.getPageToken());


                if (responseVideo != null) {
                    progressBar.setVisibility(View.GONE);
                    if (responseVideo.items.size() > 0) {
                        for (int i = 0; i < responseVideo.items.size(); i++) {
                            arrayListVideo.add(responseVideo.items.get(i));
                        }
                        vIdeoAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getContext(), "There is No Video In Your Channel!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseVideo> call, Throwable t) {

            }
        });

    }







/*
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
*/

}