package com.galou.mynews.controllers.fragments;


import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.galou.mynews.R;
import com.galou.mynews.controllers.dialogs.PickDateDialog;
import com.galou.mynews.utils.DateUtil;
import com.galou.mynews.utils.TextUtil;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.galou.mynews.utils.DateUtil.convertCalendarForAPI;
import static com.galou.mynews.utils.DateUtil.convertCalendarForDisplay;
import static com.galou.mynews.utils.DateUtil.convertUserDateToCalendar;
import static com.galou.mynews.utils.DateUtil.isDateAfterToday;
import static com.galou.mynews.utils.DateUtil.isEndDateBeforeBeginDate;

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

    public static final int DIALOG_CODE = 12345;

    // --------------
    // ACTIONS
    // --------------

    @OnClick(R.id.search_fragment_search_button)
    public void onClickSearchButton(){
        this.setQueryTerm();
        this.setQuerySections();
        if (isAllDataCorrect()){
            listQueryTerms = TextUtil.separateTextBySpace(queryTerms);
            sentDataToActivity();
        }
    }

    @OnClick({R.id.search_fragment_start_begin_date, R.id.search_fragment_search_end_date})
    public void onClickDate(final View view){
        beginDate = convertUserDateToCalendar(beginDateUser.getText().toString());
        endDate = convertUserDateToCalendar(endDateUser.getText().toString());
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
        }
        if(view == endDateUser){
            endDateUser.setText(convertCalendarForDisplay(calendar));
        }

    }

    // --------------
    // CONFIGURATION
    // --------------

    @Override
    protected int getFragmentLayout() {
        return (R.layout.fragment_search);
    }

    // --------------
    // TEST FOR ERROR MESSAGE
    // --------------


    private Boolean isBeginDateCorrect(){
        beginDate = convertUserDateToCalendar(beginDateUser.getText().toString());
        if (beginDateUser.getText().length() > 0) {
            if (beginDate == null) {
                beginDateInputLayout.setError(getString(R.string.error_message_format_date));
                return false;
            } else if (isDateAfterToday(beginDate)) {
                beginDateInputLayout.setError(getString(R.string.error_message_date_in_future));
            } else {
                beginDateInputLayout.setError(null);
                beginDateInputLayout.setErrorEnabled(false);
                return true;
            }
        }
        return true;
    }

    private Boolean isEndDateCorrect(){
        endDate = convertUserDateToCalendar(endDateUser.getText().toString());
        if (endDateUser.getText().length() > 0) {
            if (endDate == null) {
                endDateInputLayout.setError(getString(R.string.error_message_format_date));
                return false;
            } else if (isEndDateBeforeBeginDate(beginDate, endDate)) {
                endDateInputLayout.setError(getString(R.string.error_message_end_date_before_begin));
                return false;
            } else if (isDateAfterToday(endDate)) {
                endDateInputLayout.setError(getString(R.string.error_message_date_in_future));
                return false;
            } else {
                endDateInputLayout.setError(null);
                endDateInputLayout.setErrorEnabled(false);
                return true;
            }
        }
        return true;
    }

    // --------------
    // UTILS
    // --------------

    @Override
    protected Boolean isAllDataCorrect(){
        return !(!isQueryTermCorrect() | !isOneSectionSelected() | !isBeginDateCorrect() | !isEndDateCorrect());

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
