package com.galou.mynews.searchNotification;

import android.support.annotation.NonNull;

import com.galou.mynews.utils.TextUtil;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

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

    private SearchContract.ContractView searchView;
    private Calendar beginDateCalendar;
    private Calendar endDateCalendar;
    private String beginDateApi;
    private String endDateApi;

    private String queryTerm;
    private String beginDate;
    private String endDate;
    private List<String> sections;


    public SearchPresenter(@NonNull SearchContract.ContractView searchView) {
        this.searchView = searchView;
        searchView.setPresenter(this);

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
            String[] queryTerms = TextUtil.separateTextBySpace(queryTerm);
            convertCalendarForApi();

            String termsSearch = "Query Term: " + Arrays.toString(queryTerms) + "\n"
                    + "Begin Date: " + beginDateApi + "\n"
                    + "End Date: " + endDateApi + "\n"
                    + "Section: " + sections;

            searchView.showResultResearch(termsSearch);

        }

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
