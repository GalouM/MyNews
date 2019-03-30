package com.galou.mynews.controllers.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.galou.mynews.R;
import com.galou.mynews.controllers.activities.SearchActivity;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by galou on 2019-03-22
 */
@RunWith(RobolectricTestRunner.class)
public class SearchFragmentUnitTest {

    private SearchActivity activity;
    private Button searchButton;
    private EditText queryTerm;
    private TextInputLayout queryTermInputLayout;
    private TextInputLayout querySectionInputLayout;
    private TextInputLayout beginDateInputLayout;
    private TextInputLayout endDateInputLayout;


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(SearchActivity.class).create().resume().get();
        searchButton = activity.findViewById(R.id.search_fragment_search_button);
        queryTerm = activity.findViewById(R.id.query_term);
        queryTermInputLayout = (TextInputLayout) activity.findViewById(R.id.query_sections_input_layout);
        querySectionInputLayout = (TextInputLayout) activity.findViewById(R.id.query_sections_input_layout);
        beginDateInputLayout = (TextInputLayout) activity.findViewById(R.id.begin_date_input_layout);
        endDateInputLayout = (TextInputLayout) activity.findViewById(R.id.end_date_input_layout);
    }

    @Test
    public void noQueryTermShowErrorMessage() throws Exception {
        queryTerm.setText("");
        searchButton.performClick();

        assertTrue(queryTermInputLayout.isErrorEnabled());
    }

    @Test
    public void queryTermIncorrectShowErrorMessage() throws Exception {
        queryTerm.setText("$!@");
        searchButton.performClick();

        assertTrue(queryTermInputLayout.isErrorEnabled());
    }

    @Test
    public void noSectionSelectedShowError() throws Exception {
        CheckBox artCheck = activity.findViewById(R.id.search_item_art);
        artCheck.setChecked(false);
        CheckBox businessCheck = activity.findViewById(R.id.search_item_business);
        businessCheck.setChecked(false);
        CheckBox entrepreneurCheck = activity.findViewById(R.id.search_item_entrepreneurs);
        entrepreneurCheck.setChecked(false);
        CheckBox politicsCheck = activity.findViewById(R.id.search_item_politics);
        politicsCheck.setChecked(false);
        CheckBox sportCheck = activity.findViewById(R.id.search_item_sport);
        sportCheck.setChecked(false);
        CheckBox travelCheck = activity.findViewById(R.id.search_item_travel);
        travelCheck.setChecked(false);
        searchButton.performClick();

        assertTrue(querySectionInputLayout.isErrorEnabled());
    }

    @Test
    public void clickSearchAllDataCorrect() throws Exception {
        queryTerm.setText("test test2");
        CheckBox artCheck = activity.findViewById(R.id.search_item_art);
        artCheck.setChecked(true);
        CheckBox businessCheck = activity.findViewById(R.id.search_item_business);
        businessCheck.setChecked(true);
        CheckBox entrepreneurCheck = activity.findViewById(R.id.search_item_entrepreneurs);
        entrepreneurCheck.setChecked(true);
        CheckBox politicsCheck = activity.findViewById(R.id.search_item_politics);
        politicsCheck.setChecked(false);
        CheckBox sportCheck = activity.findViewById(R.id.search_item_sport);
        sportCheck.setChecked(true);
        CheckBox travelCheck = activity.findViewById(R.id.search_item_travel);
        travelCheck.setChecked(false);
        searchButton.performClick();

        List<String> querySections = new ArrayList<>();
        querySections.add(artCheck.getText().toString());
        querySections.add(businessCheck.getText().toString());
        querySections.add(entrepreneurCheck.getText().toString());
        querySections.add(sportCheck.getText().toString());


        String[] queryTermList = {"test", "test2"};


        String messageToast = "Query Term: " + Arrays.toString(queryTermList) + "\n"
                + "Begin Date: null" +  "\n"
                + "End Date: null" + "\n"
                + "Section: " + querySections;

        TestCase.assertEquals(ShadowToast.getTextOfLatestToast(), messageToast);
    }

}
