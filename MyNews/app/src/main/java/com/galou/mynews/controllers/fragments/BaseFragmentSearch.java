package com.galou.mynews.controllers.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.galou.mynews.R;
import com.galou.mynews.utils.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by galou on 2019-03-21
 */
public abstract class BaseFragmentSearch extends Fragment {

    protected abstract int getFragmentLayout();
    protected abstract void createCallbackToParentActivity();
    protected abstract Boolean isAllDataCorrect();

    // views
    @BindView(R.id.query_term) EditText userTerm;
    @BindView(R.id.search_item_art) CheckBox boxArts;
    @BindView(R.id.search_item_business) CheckBox boxBusiness;
    @BindView(R.id.search_item_entrepreneurs) CheckBox boxEntrepreneurs;
    @BindView(R.id.search_item_politics) CheckBox boxPolitics;
    @BindView(R.id.search_item_sport) CheckBox boxSport;
    @BindView(R.id.search_item_travel) CheckBox boxTravel;
    @BindView(R.id.query_term_input_layout) TextInputLayout queryTermInputLayout;
    @BindView(R.id.query_sections_input_layout) TextInputLayout querySectionInputLayout;


    //data
    protected String queryTerms;
    protected String[] listQueryTerms;
    protected List<String> querySections;

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

    // --------------
    // SET DATA
    // --------------

    protected void setQueryTerm(){
        queryTerms = userTerm.getText().toString();
    }

    protected void setQuerySections(){
        this.querySections = new ArrayList<>();
        if(this.boxArts.isChecked()){
            querySections.add(boxArts.getText().toString());
        }
        if(this.boxBusiness.isChecked()){
            querySections.add(boxBusiness.getText().toString());
        }
        if(this.boxEntrepreneurs.isChecked()){
            querySections.add(boxEntrepreneurs.getText().toString());
        }
        if(this.boxPolitics.isChecked()){
            querySections.add(boxPolitics.getText().toString());
        }
        if(this.boxSport.isChecked()){
            querySections.add(boxSport.getText().toString());
        }
        if(this.boxTravel.isChecked()){
            querySections.add(boxTravel.getText().toString());
        }

    }

    // --------------
    // TEST FOR ERROR MESSAGE
    // --------------

    protected Boolean isQueryTermCorrect(){
        if (queryTerms.length() <= 0) {
            queryTermInputLayout.setError(getString(R.string.error_message_query_empty));
            queryTermInputLayout.setErrorEnabled(true);
            return false;
        } else if (TextUtil.isTextContainsSpecialCharacter(queryTerms)){
            queryTermInputLayout.setError(getString(R.string.error_message_query_incorrect));
            return false;
        } else {
            queryTermInputLayout.setError(null);
            queryTermInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    protected Boolean isOneSectionSelected(){
        if (querySections.isEmpty()){
            querySectionInputLayout.setError(getString(R.string.error_message_no_section_selected));
            return false;
        } else {
            querySectionInputLayout.setError(null);
            querySectionInputLayout.setErrorEnabled(false);
            return true;
        }
    }


}
