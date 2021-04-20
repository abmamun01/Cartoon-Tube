package com.mamunsproject.youtubekids.Retrofit;


import com.mamunsproject.youtubekids.Model.ResponseVideo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("playlistItems?part=snippet%2CcontentDetails")
    Call<ResponseVideo> getAllVideos(@Query("maxResults")int max,
                                     @Query("playlistId") String playlistId,
                                     @Query("key") String apiKey);




}
