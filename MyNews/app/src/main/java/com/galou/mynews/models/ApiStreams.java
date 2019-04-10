package com.galou.mynews.models;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by galou on 2019-03-27
 */
public class ApiStreams {

    public static Observable<SectionMostPopular> streamsFetchMostPopSection(){
        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        return apiService.getMostPopSection()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<SectionTopStories> streamFetchTopStories(String section){
        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        return apiService.getTopStoriesSection(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<SectionSearch> streamFetchSearch(String beginDate, String endDate,
                                                              String querySection, String queryTerms){
        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        return apiService.getSearchSection(beginDate, endDate, querySection, queryTerms)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
