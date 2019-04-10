package com.galou.mynews.searchNotification;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.galou.mynews.R;
import com.galou.mynews.consultArticles.MainActivity;
import com.galou.mynews.models.ArticleSearch;
import com.galou.mynews.models.SectionSearch;
import com.galou.mynews.resultsSearch.ResultsSearchActivity;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.galou.mynews.utils.DateUtil.convertCalendarForDisplay;
import static com.galou.mynews.utils.DateUtil.convertUserDateToCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchView extends BaseView implements PickDateView.OnOKButtonListener, SearchContract.View {

    private SearchContract.Presenter presenter;

    // views
    @BindView(R.id.search_fragment_start_begin_date) EditText beginDateUser;
    @BindView(R.id.search_fragment_search_end_date) EditText endDateUser;
    @BindView(R.id.begin_date_input_layout) TextInputLayout beginDateInputLayout;
    @BindView(R.id.end_date_input_layout) TextInputLayout endDateInputLayout;
    @BindView(R.id.search_view_root_view) CoordinatorLayout rootView;

    public static final int DIALOG_CODE = 12345;

    public static final String BUNDLE_KEY_BEGIN_DATE = "beginDate";
    public static final String BUNDLE_KEY_END_DATE = "endDate";
    public static final String BUNDLE_KEY_QUERY_SECTIONS = "querySections";
    public static final String BUNDLE_KEY_QUERY_TERM = "queryTerms";


    // --------------
    // CONFIGURATION
    // --------------
    @Override
    public void setPresenter(@NonNull SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected int getFragmentLayout() {
        return (R.layout.fragment_search);
    }

    // --------------
    // ACTIONS CLICK
    // --------------

    @Override
    @OnClick(R.id.search_fragment_search_button)
    public void onClickSearchButton(){
        this.setQueryTerm();
        this.setQuerySections();
        presenter.startSearch(queryTerms, beginDateUser.getText().toString(),
                endDateUser.getText().toString(), querySections);
    }

    @OnClick({R.id.search_fragment_start_begin_date, R.id.search_fragment_search_end_date})
    public void onClickDate(final android.view.View view){
        Calendar beginDate = convertUserDateToCalendar(beginDateUser.getText().toString());
        Calendar endDate = convertUserDateToCalendar(endDateUser.getText().toString());
        PickDateView datePickerDialog = new PickDateView();
        datePickerDialog.setTargetFragment(this, DIALOG_CODE);
        datePickerDialog.setViewId(view);
        if(view == beginDateUser && beginDate != null){
            datePickerDialog.setExistingDate(beginDate);
        }
        if(view == endDateUser && endDate != null){
            datePickerDialog.setExistingDate(endDate);
        }
        datePickerDialog.show(getFragmentManager().beginTransaction(), PickDateView.TAG);
    }

    @Override
    public void onOkButtonListener(Calendar calendar, android.view.View view) {
        if(view == beginDateUser){
            beginDateUser.setText(convertCalendarForDisplay(calendar));
        }
        if(view == endDateUser){
            endDateUser.setText(convertCalendarForDisplay(calendar));
        }

    }


    // --------------
    // SHOW ERROR MESSAGES
    // --------------


    @Override
    public void displayErrorQueryTerm(ErrorMessage errorMessage) {
        switch (errorMessage){
            case EMPTY:
                queryTermInputLayout.setError(getString(R.string.error_message_query_empty));
                break;
            case INCORRECT:
                queryTermInputLayout.setError(getString(R.string.error_message_query_incorrect));
                break;
        }

    }


    @Override
    public void displayErrorBeginDate(ErrorMessage errorMessage) {
        switch (errorMessage){
            case INCORRECT:
                beginDateInputLayout.setError(getString(R.string.error_message_format_date));
                break;
            case IN_FUTURE:
                beginDateInputLayout.setError(getString(R.string.error_message_date_in_future));
                break;
        }
    }

    @Override
    public void displayErrorEndDate(ErrorMessage errorMessage) {
        switch (errorMessage){
            case INCORRECT:
                endDateInputLayout.setError(getString(R.string.error_message_format_date));
                break;
            case BEFORE_BEGIN_DATE:
                endDateInputLayout.setError(getString(R.string.error_message_end_date_before_begin));
                break;
            case IN_FUTURE:
                endDateInputLayout.setError(getString(R.string.error_message_date_in_future));
                break;
        }
    }

    @Override
    public void displayErrorSections(ErrorMessage errorMessage) {
        switch (errorMessage){
            case EMPTY:
                querySectionInputLayout.setError(getString(R.string.error_message_no_section_selected));
                break;
        }
    }

    @Override
    public void disableAllErrors() {
        queryTermInputLayout.setError(null);
        queryTermInputLayout.setErrorEnabled(false);
        querySectionInputLayout.setError(null);
        querySectionInputLayout.setErrorEnabled(false);
        beginDateInputLayout.setError(null);
        beginDateInputLayout.setErrorEnabled(false);
        endDateInputLayout.setError(null);
        endDateInputLayout.setErrorEnabled(false);

    }

    // --------------
    // START RESULT ACTIVITY
    // --------------

    @Override
    public void showResultResearch(String beginDate, String endDate, String querySection, String queryTerms) {
        Intent intent = new Intent(getContext(), ResultsSearchActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_BEGIN_DATE, beginDate);
        bundle.putString(BUNDLE_KEY_END_DATE, endDate);
        bundle.putString(BUNDLE_KEY_QUERY_SECTIONS, querySection);
        bundle.putString(BUNDLE_KEY_QUERY_TERM, queryTerms);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
