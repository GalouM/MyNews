package com.galou.mynews.searchNotification;

import android.support.annotation.NonNull;
import android.util.Log;

import com.galou.mynews.models.ApiStreams;
import com.galou.mynews.models.ArticleSearch;
import com.galou.mynews.models.SectionSearch;
import com.galou.mynews.models.SectionTopStories;
import com.galou.mynews.utils.TextUtil;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.galou.mynews.searchNotification.ErrorMessage.BEFORE_BEGIN_DATE;
import static com.galou.mynews.searchNotification.ErrorMessage.EMPTY;
import static com.galou.mynews.searchNotification.ErrorMessage.INCORRECT;
import static com.galou.mynews.searchNotification.ErrorMessage.IN_FUTURE;
import static com.galou.mynews.utils.DateUtil.convertCalendarForAPI;
import static com.galou.mynews.utils.DateUtil.convertUserDateToCalendar;
import static com.galou.mynews.utils.DateUtil.isDateAfterToday;
import static com.galou.mynews.utils.DateUtil.isEndDateBeforeBeginDate;
import static com.galou.mynews.utils.TextUtil.isTextContainsSpecialCharacter;

/**
 * Created by galou on 2019-04-02
 */
public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View searchView;
    private Calendar beginDateCalendar;
    private Calendar endDateCalendar;
    private String beginDateApi;
    private String endDateApi;

    private String queryTerm;
    private String beginDate;
    private String endDate;
    private List<String> sections;

    private String querySearch;
    private SectionSearch searchQuery;

    private Disposable disposable;


    public SearchPresenter(@NonNull SearchContract.View searchView) {
        this.searchView = searchView;
        this.searchView.setPresenter(this);

    }

    @Override
    public void startSearch(String queryTerm, String beginDate, String endDate, List<String> sections) {
        this.queryTerm = queryTerm;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.sections = sections;

        searchView.disableAllErrors();
        beginDateCalendar = convertUserDateToCalendar(beginDate);
        endDateCalendar = convertUserDateToCalendar(endDate);

        if (allDataAreCorrect()){
            String queryTermsForAPI = TextUtil.convertQueryTermForAPI(queryTerm);
            String querySectionForAPI = "news_desk%3A" + TextUtil.convertListInStringForAPI(sections);
            convertCalendarForApi();
            this.disposable = ApiStreams.streamFetchSearch(beginDateApi, endDateApi, querySectionForAPI, queryTermsForAPI).subscribeWith(getObserverSearch());
            Log.e("tag", beginDateApi + endDateApi + querySectionForAPI + queryTermsForAPI);
        }

    }

    private DisposableObserver<SectionSearch> getObserverSearch(){
        return new DisposableObserver<SectionSearch>() {
            @Override
            public void onNext(SectionSearch section) {
                sendListArticleToView(section);

            }

            @Override
            public void onError(Throwable e) {
                sendErrorToView(e);

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void sendListArticleToView(SectionSearch searchQuery){
        this.searchQuery = searchQuery;
        searchView.showResultResearch(this.searchQuery);

    }

    private void sendErrorToView(Throwable e){
        searchView.showSnackBar();
    }

    @Override
    public void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();

    }

    private boolean allDataAreCorrect(){
        return !(!isQueryTermCorrect() | !isQuerySectionCorrect() |
                !isBeginDateCorrect() | !isEndDateCorrect());

    }

    private boolean isQueryTermCorrect() {
        if (queryTerm.length() <= 0) {
            searchView.displayErrorQueryTerm(EMPTY);
            return false;
        }
        if (isTextContainsSpecialCharacter(queryTerm)) {
            searchView.displayErrorQueryTerm(INCORRECT);
            return false;
        }
        return true;


    }

    private boolean isQuerySectionCorrect(){
        if (sections.size() <= 0) {
            searchView.displayErrorSections(EMPTY);
            return false;
        }

        return true;

    }

    private boolean isBeginDateCorrect(){
        if (beginDate.length() > 0) {
            if (beginDateCalendar == null) {
                searchView.displayErrorBeginDate(INCORRECT);
                return false;
            }
            if (isDateAfterToday(beginDateCalendar)) {
                searchView.displayErrorBeginDate(IN_FUTURE);
                return false;
            }

        }
        return true;
    }

    private boolean isEndDateCorrect(){
        if (endDate.length() > 0) {
            if (endDateCalendar == null) {
                searchView.displayErrorEndDate(INCORRECT);
                return false;
            }
            if (isEndDateBeforeBeginDate(beginDateCalendar, endDateCalendar)) {
                searchView.displayErrorEndDate(BEFORE_BEGIN_DATE);
                return false;
            }
            if (isDateAfterToday(endDateCalendar)) {
                searchView.displayErrorEndDate(IN_FUTURE);
                return false;
            }
        }
        return true;
    }

    private void convertCalendarForApi(){
        if (beginDateCalendar != null){
            beginDateApi = convertCalendarForAPI(beginDateCalendar);
        }
        if (endDateCalendar != null) {
            endDateApi = convertCalendarForAPI(endDateCalendar);
        }

    }
}
