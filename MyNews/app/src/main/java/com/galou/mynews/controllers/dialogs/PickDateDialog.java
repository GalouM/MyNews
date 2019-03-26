package com.galou.mynews.controllers.dialogs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import com.galou.mynews.R;

import java.util.Calendar;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * Created by galou on 2019-03-23
 */
public class PickDateDialog extends DialogFragment implements DialogInterface.OnClickListener {

    public interface OnOKButtonListener{
        void onOkButtonListener (Calendar calendar, View view);
    }

    private OnOKButtonListener mCallback;

    public static final String TAG = "PickDateDialog";

    //views
    private DatePicker datePicker;

    //for data
    private Calendar calendar;
    private View viewId;
    private Calendar existingDate;

    public PickDateDialog() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.createCallbackToParent();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final DatePickerDialog.Builder datePickerDialog = new DatePickerDialog.Builder(getActivity());
        datePicker = new DatePicker(getActivity());
        datePickerDialog.setView(datePicker);
        if (existingDate != null){
            datePicker.updateDate(existingDate.get(Calendar.YEAR), existingDate.get(Calendar.MONTH), existingDate.get(Calendar.DAY_OF_MONTH));
        }
        datePickerDialog.setPositiveButton(getString(R.string.ok_button), this);
        datePickerDialog.setNegativeButton(getString(R.string.cancel_button), this);

        return  datePickerDialog.create();
    }


    // --------------
    // ACTIONS
    // --------------
    @Override
    public void onClick(DialogInterface dialogInterface, int button) {
        if(button == BUTTON_POSITIVE){
            this.setDate();
            mCallback.onOkButtonListener(calendar, viewId);


        }
        if(button == BUTTON_NEGATIVE){
            dialogInterface.cancel();

        }

    }

    // --------------
    // SETTERS
    // --------------

    public void setViewId(View view){
        this.viewId = view;

    }

    public void setExistingDate(Calendar date){
        existingDate = date;

    }

    private void setDate(){
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        int year = datePicker.getYear();
        calendar = Calendar.getInstance();
        calendar.set(year,month,day);
    }

    // --------------
    // CALLBACK SUPPORT
    // --------------

    private void createCallbackToParent(){
        try{
            mCallback = (OnOKButtonListener) getTargetFragment();

        } catch (ClassCastException e){
            throw new ClassCastException(e.toString()+"must implement OnOKButtonListener");
        }
    }

}
