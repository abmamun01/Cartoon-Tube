package com.mamunsproject.youtubekids.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mamunsproject.youtubekids.Adapter.VIdeoAdapter2;
import com.mamunsproject.youtubekids.Model.ResponseVideo;
import com.mamunsproject.youtubekids.Model.Video;
import com.mamunsproject.youtubekids.R;
import com.mamunsproject.youtubekids.Retrofit.ApiClient;
import com.mamunsproject.youtubekids.Retrofit.ApiInterface;
import com.mamunsproject.youtubekids.Utils.MyConsts;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doraemon extends AppCompatActivity {


    VIdeoAdapter2 vIdeoAdapter;
    ArrayList<Video> arrayListVideo;
    ApiInterface apiInterface;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doraemon);


        progressBar=findViewById(R.id.progressBarID);

        RecyclerView recyclerView =findViewById(R.id.recyclerMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        arrayListVideo = new ArrayList<>();
        vIdeoAdapter = new VIdeoAdapter2(getApplicationContext(), arrayListVideo);
        recyclerView.setAdapter(vIdeoAdapter);
        getVideos();

    }

    private void getVideos() {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseVideo> callVideo = apiInterface.getAllVideos(3000, MyConsts.DOREAMON_ID
                , MyConsts.APIKEY);

        callVideo.enqueue(new Callback<ResponseVideo>() {
            @Override
            public void onResponse(Call<ResponseVideo> call, Response<ResponseVideo> response) {

                ResponseVideo responseVideo = response.body();
                if (responseVideo != null) {
                    progressBar.setVisibility(View.GONE);
                    if (responseVideo.items.size() > 0) {
                        for (int i = 0; i < responseVideo.items.size(); i++) {
                            arrayListVideo.add(responseVideo.items.get(i));
                        }
                        vIdeoAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "There is No Video In Your Channel!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseVideo> call, Throwable t) {

            }
        });

    }

}