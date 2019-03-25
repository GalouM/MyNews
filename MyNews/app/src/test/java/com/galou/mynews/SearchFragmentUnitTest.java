package com.galou.mynews;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.widget.CheckBox;
import android.widget.EditText;

import com.galou.mynews.controllers.activities.NotificationsActivity;
import com.galou.mynews.controllers.activities.SearchActivity;
import com.galou.mynews.controllers.dialogs.PickDateDialog;
import com.galou.mynews.controllers.fragments.SearchFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowDialog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by galou on 2019-03-22
 */
@RunWith(RobolectricTestRunner.class)
public class SearchFragmentUnitTest {

    private SearchActivity activity;
    private FragmentManager fragmentManager;
    private SearchFragment fragment;
    private PickDateDialog pickDateDialog;


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(SearchActivity.class).create().resume().get();
        fragmentManager = (FragmentManager) activity.getSupportFragmentManager();
        fragment = new SearchFragment();
        pickDateDialog = new PickDateDialog();
    }

    @Test
    public void noQueryTermEnterShowDialog() throws Exception {
        EditText queryTerm = activity.findViewById(R.id.query_term);
        queryTerm.setText("");
        assertNotNull(ShadowAlertDialog.getShownDialogs());
    }

    @Test
    public void queryTermIncorrectEnterShowDialog() throws Exception {
        EditText queryTerm = activity.findViewById(R.id.query_term);
        queryTerm.setText("$!@");
        assertNotNull(ShadowAlertDialog.getShownDialogs());
    }

    @Test
    public void noSectionSelectedShowDialog() throws Exception {
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
        assertNotNull(ShadowAlertDialog.getShownDialogs());
    }

    @Test
    public void beginDateClickOkSetText() throws Exception {
        EditText beginDate = activity.findViewById(R.id.search_fragment_start_begin_date);
        beginDate.performClick();
        ShadowAlertDialog.getLatestAlertDialog().getButton(AlertDialog.BUTTON_POSITIVE).performClick();
        assertNotEquals(beginDate.getText(), "");
    }
    


}
