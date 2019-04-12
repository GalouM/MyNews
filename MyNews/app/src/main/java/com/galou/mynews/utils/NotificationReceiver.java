package com.galou.mynews.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.galou.mynews.R;
import com.galou.mynews.models.ApiStreams;
import com.galou.mynews.models.SectionSearch;

import java.util.Calendar;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.galou.mynews.consultArticles.MainActivity.CHANNEL_ID;
import static com.galou.mynews.searchNotification.NotificationsView.NOTIFICATION_ID;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_QUERY_SECTIONS;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_QUERY_TERM;
import static com.galou.mynews.utils.DateUtil.convertCalendarForAPI;

/**
 * Created by galou on 2019-04-10
 */
public class NotificationReceiver extends BroadcastReceiver {

    private String queryTerms;
    private String querySection;
    private String beginDate;
    private Disposable disposable;
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Bundle bundle = intent.getExtras();
        queryTerms = bundle.getString(BUNDLE_KEY_QUERY_TERM);
        querySection = bundle.getString(BUNDLE_KEY_QUERY_SECTIONS);

        createBeginDate();
        getArticles();

    }

    private void createBeginDate(){
        Calendar beginDateCalendar = Calendar.getInstance();
        beginDate = convertCalendarForAPI(beginDateCalendar);
    }

    // -------------------
    // API REQUEST
    // -------------------

    private void getArticles() {
        this.disposable = ApiStreams.streamFetchSearch(beginDate, null, querySection, queryTerms, 0).subscribeWith(getObserverSearch());

    }

    private DisposableObserver<SectionSearch> getObserverSearch(){
        return new DisposableObserver<SectionSearch>() {
            @Override
            public void onNext(SectionSearch section) {
                showNotification(section);

            }

            @Override
            public void onError(Throwable e) { 
                showFailedNotification();
            }

            @Override
            public void onComplete() { }
        };
    }

    // -------------------
    // SHOW NOTIFICATION
    // -------------------

    private void showNotification(SectionSearch section){
        int numberArticle = section.getResponse().getMeta().getHits();
        String contentTextNotification = String.format(context.getString(R.string.content_notification_message), numberArticle);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.new_york_time_icon)
                .setContentTitle(context.getString(R.string.title_notifications))
                .setContentText(contentTextNotification)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

    }
    
    private void showFailedNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.new_york_time_icon)
                .setContentTitle(context.getString(R.string.error_notifications))
                .setContentText(context.getString(R.string.error_notification_message))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
        
    }
}
