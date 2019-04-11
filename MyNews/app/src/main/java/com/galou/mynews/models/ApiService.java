package com.galou.mynews.models;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by galou on 2019-03-27
 */
public interface ApiService {

     String BASE_URL = "https://api.nytimes.com/svc/";
     String API_KEY = "4UGuTENR5tNBtpkR4YxJLYK0VWLhAGAW";

     @GET("mostpopular/v2/shared/7.json?api-key="+API_KEY)
    Observable<SectionMostPopular> getMostPopSection();

     @GET("topstories/v2/{section}.json?api-key="+API_KEY)
     Observable<SectionTopStories> getTopStoriesSection(@Path("section") String section);

    @GET("search/v2/articlesearch.json?sort=relevance&api-key="+API_KEY)
    Observable<SectionSearch> getSearchSection(
            @Query("begin_date") String beginDate,
            @Query("end_date") String endDate,
            @Query("fq") String querySection,
            @Query("q") String queryTerms);



    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
