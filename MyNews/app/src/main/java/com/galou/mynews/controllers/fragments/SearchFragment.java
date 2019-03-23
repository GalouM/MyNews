package com.galou.mynews.controllers.fragments;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.galou.mynews.R;
import com.galou.mynews.controllers.activities.SearchActivity;
import com.galou.mynews.models.ErrorSelection;
import com.galou.mynews.views.DatePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragmentSearch {

    // views
    @BindView(R.id.search_fragment_start_begin_date) EditText beginDateUser;
    @BindView(R.id.search_fragment_search_end_date) EditText endDateUser;

    //for data
    private Calendar beginDate;
    private Calendar endDate;

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd", Locale.CANADA);

    // --------------
    // ACTIONS
    // --------------

    @OnClick(R.id.search_fragment_search_button)
    public void onClickSearchButton(final View view){
        if (getQueryTerm().length() <= 0){
            this.showAlertDialog(ErrorSelection.TERM);
        } else if (getQuerySections().isEmpty()){
            this.showAlertDialog(ErrorSelection.SECTION);
        } else if (getQueryBeginDate().length() <= 0){
            this.showAlertDialog(ErrorSelection.START_DATE);
        } else if(isIncorrectEndDate()){
            this.showAlertDialog(ErrorSelection.INCORRECT_DATE);
        }else {
            SearchActivity activity = (SearchActivity) getActivity();
            activity.setQueryTerm(this.getQueryTerm());
            activity.setQuerySection(this.getQuerySections());
            activity.setQueryBeginDate(this.getQueryBeginDate());
            activity.setQueryEndDate(this.getQueryEndDate());
            mCallback.onButtonClicked(view);
        }
    }

    @OnClick({R.id.search_fragment_start_begin_date, R.id.search_fragment_search_end_date})
    public void onClickDate(final View view){
        final DatePickerDialog.Builder datePickerDialog = new DatePickerDialog.Builder(getActivity());
        final DatePicker datePicker = new DatePicker(getActivity());

        if(view == beginDateUser && beginDate != null) {
            datePicker.updateDate(beginDate.get(Calendar.YEAR),beginDate.get(Calendar.MONTH), beginDate.get(Calendar.DAY_OF_MONTH));

        }
        if(view == endDateUser && endDate != null) {
            datePicker.updateDate(endDate.get(Calendar.YEAR),endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));

        }
        datePickerDialog.setView(datePicker);
        datePickerDialog.setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                int year = datePicker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year,month,day);
                String yearString = String.valueOf(year);
                String monthString;
                String dayString;
                if (month < 9){
                    monthString = "0" + String.valueOf(month+1);
                } else {
                    monthString = String.valueOf(month+1);
                }
                if (day < 10) {
                    dayString = "0" + String.valueOf(day);
                } else {
                    dayString = String.valueOf(day);
                }
                String dateFromUser = monthString + "/" + dayString + "/" + yearString;
                if(view == beginDateUser) {
                    beginDate = calendar;
                    beginDateUser.setText(dateFromUser);
                }
                if (view == endDateUser){
                    endDate = calendar;
                    endDateUser.setText(dateFromUser);
                }
            }
        });
        datePickerDialog.setNegativeButton(getString(R.string.cancel_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        datePickerDialog.show();
    }

    // --------------
    // UTILS
    // --------------

    @Override
    protected int getFragmentLayout() {
        return (R.layout.fragment_search);
    }

    private Boolean isIncorrectEndDate(){
        if(endDateUser.getText().length() > 0) {
            return (!beginDate.getTime().before(endDate.getTime()));
        } else {
            return false;
        }
    }

    private String getQueryBeginDate(){
        if (beginDateUser.getText().length() > 0) {
            return DATE_FORMAT.format(beginDate.getTime());
        } else {
            return "";
        }
    }

    private String getQueryEndDate(){
        if (endDateUser.getText().length() > 0){
            return DATE_FORMAT.format(endDate.getTime());
        } else
            return "";
    }
}
