package com.galou.mynews.searchNotification;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.SwitchCompat;
import android.widget.CheckBox;
import android.widget.EditText;

import com.galou.mynews.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by galou on 2019-03-25
 */
@RunWith(RobolectricTestRunner.class)
public class NotificationViewUnitTest {

    private NotificationsActivity activity;
    private SwitchCompat notificationEnabled;
    private EditText queryTerm;
    private TextInputLayout queryTermInputLayout;
    private TextInputLayout querySectionInputLayout;
    private CheckBox artCheck;
    private CheckBox businessCheck;
    private CheckBox entrepreneurCheck;
    private CheckBox politicsCheck;
    private CheckBox sportCheck;
    private CheckBox travelCheck;


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(NotificationsActivity.class).create().resume().get();
        notificationEnabled = activity.findViewById(R.id.notification_fragment_switch);


        //find views
        queryTerm = activity.findViewById(R.id.query_term);
        notificationEnabled.setChecked(false);
        queryTermInputLayout = (TextInputLayout) activity.findViewById(R.id.query_term_input_layout);
        querySectionInputLayout = (TextInputLayout) activity.findViewById(R.id.query_sections_input_layout);
        artCheck = activity.findViewById(R.id.search_item_art);
        businessCheck = activity.findViewById(R.id.search_item_business);
        entrepreneurCheck = activity.findViewById(R.id.search_item_entrepreneurs);
        politicsCheck = activity.findViewById(R.id.search_item_politics);
        sportCheck = activity.findViewById(R.id.search_item_sport);
        travelCheck = activity.findViewById(R.id.search_item_travel);

        // set data correct
        queryTerm.setText("test test2");
        artCheck.setChecked(true);
        businessCheck.setChecked(true);
        entrepreneurCheck.setChecked(true);
        politicsCheck.setChecked(false);
        sportCheck.setChecked(true);
        travelCheck.setChecked(false);


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
        artCheck.setChecked(false);
        businessCheck.setChecked(false);
        entrepreneurCheck.setChecked(false);
        sportCheck.setChecked(false);
        notificationEnabled.performClick();

        assertTrue(querySectionInputLayout.isErrorEnabled());
    }

    @Test
    public void enableNotificationAllDataCorrect() throws Exception {
        notificationEnabled.performClick();

        List<String> querySections = new ArrayList<>();
        querySections.add(artCheck.getText().toString());
        querySections.add(businessCheck.getText().toString());
        querySections.add(entrepreneurCheck.getText().toString());
        querySections.add(sportCheck.getText().toString());

        assertFalse(querySectionInputLayout.isErrorEnabled());
        assertFalse(queryTermInputLayout.isErrorEnabled());

    }


}
