package com.galou.mynews.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import com.galou.mynews.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * Created by galou on 2019-03-23
 */
public class DatePickerView extends DialogFragment implements DialogInterface.OnClickListener {

    // for bundle
    Bundle bundle;
    public static final String KEY_BUNDLE_DATE_DISPLAY = "dateDisplay";
    public static final String KEY_BUNDLE_DATE_API = "dateAPI";

    public static final SimpleDateFormat DATE_FORMAT_API = new SimpleDateFormat("yyyyMMdd", Locale.CANADA);
    public static final SimpleDateFormat DATE_FORMAT_DISPLAY = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);


    private DatePicker datePicker;

    private String dateToDisplay;
    private String dateForAPI;

    public DatePickerView() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = new Bundle();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final DatePickerDialog.Builder datePickerDialog = new DatePickerDialog.Builder(getActivity());
        datePicker = new DatePicker(getActivity());
        datePickerDialog.setView(datePicker);
        datePickerDialog.setPositiveButton(getString(R.string.ok_button), this);
        datePickerDialog.setNegativeButton(getString(R.string.cancel_button), this);


        return  datePickerDialog.create();
    }


    @Override
    public void onClick(DialogInterface dialogInterface, int button) {
        if(button == BUTTON_POSITIVE){
            this.setDate();
            bundle.putString(KEY_BUNDLE_DATE_DISPLAY,dateToDisplay);
            bundle.putString(KEY_BUNDLE_DATE_API, dateForAPI);
            this.setArguments(bundle);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent());


        }
        if(button == BUTTON_NEGATIVE){
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, getActivity().getIntent());

        }

    }

    private void checkIfDateAlreadySet(){

    }


    private void setDate(){
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        int year = datePicker.getYear();
        Calendar date = Calendar.getInstance();
        date.set(year,month,day);
        dateForAPI = DATE_FORMAT_API.format(date.getTime());
        dateToDisplay= DATE_FORMAT_DISPLAY.format(date.getTime());
    }



}
