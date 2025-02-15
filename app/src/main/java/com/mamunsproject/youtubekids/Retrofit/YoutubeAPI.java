package com.mamunsproject.youtubekids.Retrofit;


import com.mamunsproject.youtubekids.Model.SearchModel.SOAnswersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface YoutubeAPI {
    @GET("search/")
    Call<SOAnswersResponse> getAnswers(@Query("key") String key,
                                       @Query("part") String part,
                                       @Query("q") String search,
                                       @Query("type") String type,
                                       @Query("maxResults") int maxResult);
}
