package com.galou.mynews.controllers.fragments;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.galou.mynews.R;
import com.galou.mynews.controllers.dialogs.PickDateDialog;
import com.galou.mynews.models.ErrorSelection;

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
        void onButtonSearchClicked(String queryTerm, @Nullable String queryBeginDate,
                                   @Nullable String queryEndDate, List<String> querySections);
    }

    // --------------

    // views
    @BindView(R.id.search_fragment_start_begin_date) EditText beginDateUser;
    @BindView(R.id.search_fragment_search_end_date) EditText endDateUser;

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

    private Boolean isAllDataCorrect(){
        if (queryTerm.length() <= 0){
            this.showAlertDialog(ErrorSelection.TERM);
            return false;
        } else if (querySections.isEmpty()){
            this.showAlertDialog(ErrorSelection.SECTION);
            return false;
        } else if(isIncorrectEndDate()) {
            this.showAlertDialog(ErrorSelection.INCORRECT_DATE);
            return false;
        } else {
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
        mCallback.onButtonSearchClicked(this.queryTerm, beginDateApi, endDateApi, querySections);

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
