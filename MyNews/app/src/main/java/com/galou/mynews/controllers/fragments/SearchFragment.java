package com.galou.mynews.controllers.fragments;


import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.galou.mynews.R;
import com.galou.mynews.controllers.dialogs.PickDateDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragmentSearch implements PickDateDialog.OnOKButtonListener {

    // interface
    protected OnButtonClickedListener mCallback;

    public interface OnButtonClickedListener{
        void onButtonSearchClicked(String queryTerm[], @Nullable String queryBeginDate,
                                   @Nullable String queryEndDate, List<String> querySections);
    }

    // --------------

    // views
    @BindView(R.id.search_fragment_start_begin_date) EditText beginDateUser;
    @BindView(R.id.search_fragment_search_end_date) EditText endDateUser;
    @BindView(R.id.begin_date_input_layout) TextInputLayout beginDateInputLayout;
    @BindView(R.id.end_date_input_layout) TextInputLayout endDateInputLayout;


    //for data
    private Calendar beginDate;
    private Calendar endDate;

    public static final SimpleDateFormat DATE_FORMAT_API =
            new SimpleDateFormat("yyyyMMdd", Locale.CANADA);
    public static final SimpleDateFormat DATE_FORMAT_DISPLAY =
            new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
    public static final int DIALOG_CODE = 12345;

    // --------------
    // ACTIONS
    // --------------

    @OnClick(R.id.search_fragment_search_button)
    public void onClickSearchButton(){
        this.setQueryTerm();
        this.setQuerySections();
        if (isAllDataCorrect()){
            separateQueryTerms();
            sentDataToActivity();
        }
    }

    @OnClick({R.id.search_fragment_start_begin_date, R.id.search_fragment_search_end_date})
    public void onClickDate(final View view){
        PickDateDialog datePickerDialog = new PickDateDialog();
        datePickerDialog.setTargetFragment(this, DIALOG_CODE);
        datePickerDialog.setViewId(view);
        if(view == beginDateUser && beginDate != null){
            datePickerDialog.setExistingDate(beginDate);
        }
        if(view == endDateUser && endDate != null){
            datePickerDialog.setExistingDate(endDate);
        }
        datePickerDialog.show(getFragmentManager().beginTransaction(), PickDateDialog.TAG);
    }

    @Override
    public void onOkButtonListener(Calendar calendar, View view) {
        if(view == beginDateUser){
            beginDateUser.setText(convertCalendarForDisplay(calendar));
            this.beginDate = calendar;
        }
        if(view == endDateUser){
            endDateUser.setText(convertCalendarForDisplay(calendar));
            this.endDate = calendar;
        }

    }

    // --------------
    // CONFIGURATION
    // --------------

    @Override
    protected int getFragmentLayout() {
        return (R.layout.fragment_search);
    }

    @Override
    protected Boolean isAllDataCorrect(){
        if (!isQueryTermCorrect() | !isQueryTermEnter() | !isOneSectionSelected() | !isDateCorrect()){
            return false;
        } else {
            return true;
        }

    }

    private Boolean isDateCorrect(){
        if(isIncorrectEndDate()) {
            endDateInputLayout.setError(getString(R.string.error_message_end_date));
            return false;
        } else{
            endDateInputLayout.setError(null);
            endDateInputLayout.setErrorEnabled(false);
            return true;
        }

    }

    private void sentDataToActivity(){
        String beginDateApi = null;
        if (beginDate != null){
            beginDateApi = convertCalendarForAPI(beginDate);
        }
        String endDateApi = null;
        if(endDate != null) {
            endDateApi = convertCalendarForAPI(endDate);
        }
        mCallback.onButtonSearchClicked(listQueryTerms, beginDateApi, endDateApi, querySections);

    }


    // --------------
    // UTILS
    // --------------

    private Boolean isIncorrectEndDate(){
        if(endDateUser.getText().length() > 0 && beginDateUser.getText().length() > 0) {
            return (endDate.getTime().before(beginDate.getTime()));
        } else {
            return false;
        }
    }

    private String convertCalendarForDisplay(Calendar calendar){
        return DATE_FORMAT_DISPLAY.format(calendar.getTime());
    }

    private String convertCalendarForAPI(Calendar calendar){
        return DATE_FORMAT_API.format(calendar.getTime());
    }

    // --------------
    // FRAGMENT SUPPORT
    // --------------

    @Override
    protected void createCallbackToParentActivity(){
        try{
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString()+"must implement OnButtonClickedListener");
        }
    }

}
