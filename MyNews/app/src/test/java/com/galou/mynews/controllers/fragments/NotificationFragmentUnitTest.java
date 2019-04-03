package com.galou.mynews.controllers.fragments;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SwitchCompat;
import android.widget.CheckBox;
import android.widget.EditText;

import com.galou.mynews.R;
import com.galou.mynews.searchNotification.NotificationsActivity;
import com.galou.mynews.searchNotification.NotificationsView;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by galou on 2019-03-25
 */
@RunWith(RobolectricTestRunner.class)
public class NotificationFragmentUnitTest {

    private NotificationsActivity activity;
    private SwitchCompat notificationEnabled;
    private EditText queryTerm;
    private NotificationsView fragment;
    private FragmentManager fragmentManager;
    private TextInputLayout queryTermInputLayout;
    private TextInputLayout querySectionInputLayout;


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(NotificationsActivity.class).create().resume().get();
        notificationEnabled = activity.findViewById(R.id.notification_fragment_switch);
        fragmentManager = (FragmentManager) activity.getSupportFragmentManager();
        fragment = new NotificationsView();
        queryTerm = activity.findViewById(R.id.query_term);
        notificationEnabled.setChecked(false);
        queryTermInputLayout = (TextInputLayout) activity.findViewById(R.id.query_term_input_layout);
        querySectionInputLayout = (TextInputLayout) activity.findViewById(R.id.query_sections_input_layout);
    }


    @Test
    public void noQueryTermShowErrorMessage() throws Exception {
        queryTerm.setText("");
        notificationEnabled.performClick();

        assertTrue(queryTermInputLayout.isErrorEnabled());
    }

    @Test
    public void queryTermIncorrectShowErrorMessage() throws Exception {
        queryTerm.setText("$!@");
        notificationEnabled.performClick();

        assertTrue(queryTermInputLayout.isErrorEnabled());
    }

    @Test
    public void noSectionSelectedShowErrorMessage() throws Exception {
        queryTerm.setText("test");
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
        notificationEnabled.performClick();

        assertTrue(querySectionInputLayout.isErrorEnabled());
    }

    @Test
    public void enableNotificationAllDataCorrect() throws Exception {
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
        sportCheck.setChecked(false);
        CheckBox travelCheck = activity.findViewById(R.id.search_item_travel);
        travelCheck.setChecked(false);
        notificationEnabled.performClick();

        List<String> querySections = new ArrayList<>();
        querySections.add(artCheck.getText().toString());
        querySections.add(businessCheck.getText().toString());
        querySections.add(entrepreneurCheck.getText().toString());

        String[] queryTermList = {"test", "test2"};


        String messageToast = "Notifications enabled" + "\n"
                + "Query Term: " + Arrays.toString(queryTermList) + "\n"
                + "Sections: " + querySections;

        TestCase.assertEquals(ShadowToast.getTextOfLatestToast(), messageToast);
    }

}
