package com.galou.mynews.controllers.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.galou.mynews.R;
import com.galou.mynews.models.ErrorSelection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by galou on 2019-03-21
 */
public abstract class BaseFragmentSearch extends Fragment {

    protected OnButtonClickedListener mCallback;

    public interface OnButtonClickedListener{
        public void onButtonClicked(View view);
    }

    // --------------

    protected abstract int getFragmentLayout();

    // views
    @BindView(R.id.query_term) EditText userTerm;
    @BindView(R.id.search_item_art) CheckBox boxArts;
    @BindView(R.id.search_item_business) CheckBox boxBusiness;
    @BindView(R.id.search_item_entrepreneurs) CheckBox boxEntrepreneurs;
    @BindView(R.id.search_item_politics) CheckBox boxPolitics;
    @BindView(R.id.search_item_sport) CheckBox boxSport;
    @BindView(R.id.search_item_travel) CheckBox boxTravel;

    protected BaseFragmentSearch(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }

    protected String getQueryTerm(){
        return userTerm.getText().toString();
    }

    protected List<String> getQuerySections(){
        List<String> querySection = new ArrayList<>();
        if(this.boxArts.isChecked()){
            querySection.add(boxArts.getText().toString());
        }
        if(this.boxBusiness.isChecked()){
            querySection.add(boxBusiness.getText().toString());
        }
        if(this.boxEntrepreneurs.isChecked()){
            querySection.add(boxEntrepreneurs.getText().toString());
        }
        if(this.boxPolitics.isChecked()){
            querySection.add(boxPolitics.getText().toString());
        }
        if(this.boxSport.isChecked()){
            querySection.add(boxSport.getText().toString());
        }
        if(this.boxTravel.isChecked()){
            querySection.add(boxTravel.getText().toString());
        }

        return querySection;

    }

    protected void showAlertDialog(ErrorSelection errorSelection){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        switch (errorSelection){
            case TERM:
                alertDialog.setTitle(getString(R.string.missing_query_term_title));
                alertDialog.setMessage(getString(R.string.missing_term_message));
                break;
            case START_DATE:
                alertDialog.setTitle(getString(R.string.mising_begin_date_title));
                alertDialog.setMessage(getString(R.string.missing_date_message));
                break;
            case SECTION:
                alertDialog.setTitle(getString(R.string.missing_section_title));
                alertDialog.setMessage(getString(R.string.missing_section_message));
                break;
            case INCORRECT_DATE:
                alertDialog.setTitle("Incorrect Date");
                alertDialog.setMessage("Please select an end date after the begin date");
        }

        alertDialog.setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertDialog.show();
    }

    // --------------
    // FRAGMENT SUPPORT
    // --------------

    protected void createCallbackToParentActivity(){
        try{
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString()+"must implement OnButtonClickedListener");
        }
    }
}
