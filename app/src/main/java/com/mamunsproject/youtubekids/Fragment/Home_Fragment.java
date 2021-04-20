package com.mamunsproject.youtubekids.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mamunsproject.youtubekids.Adapter.VIdeoAdapter;
import com.mamunsproject.youtubekids.Model.ResponseVideo;
import com.mamunsproject.youtubekids.Model.Video;
import com.mamunsproject.youtubekids.R;
import com.mamunsproject.youtubekids.Retrofit.ApiClient;
import com.mamunsproject.youtubekids.Retrofit.ApiInterface;
import com.mamunsproject.youtubekids.Utils.MyConsts;

import java.util.ArrayList;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Fragment extends Fragment {


    VIdeoAdapter vIdeoAdapter;
    ArrayList<Video> arrayListVideo;
    ApiInterface apiInterface;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        progressBar=view.findViewById(R.id.progressBarID);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        arrayListVideo = new ArrayList<>();
        vIdeoAdapter = new VIdeoAdapter(getContext(), arrayListVideo);
        recyclerView.setAdapter(vIdeoAdapter);
        getVideos();


    }

    private void getVideos() {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseVideo> callVideo = apiInterface.getAllVideos(3000, MyConsts.ALL_CARTOON_ID
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
                    Toast.makeText(getContext(), "There is No Video In Your Channel!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseVideo> call, Throwable t) {

            }
        });

    }




}