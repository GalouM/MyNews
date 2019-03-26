package com.galou.mynews;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
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
import org.robolectric.shadows.ShadowDatePickerDialog;
import org.robolectric.shadows.ShadowDialog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by galou on 2019-03-22
 */
@RunWith(RobolectricTestRunner.class)
public class SearchFragmentUnitTest {

    private SearchActivity activity;
    private Button searchButton;
    private EditText queryTerm;


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(SearchActivity.class).create().resume().get();
        searchButton = activity.findViewById(R.id.search_fragment_search_button);
        queryTerm = activity.findViewById(R.id.query_term);
    }

    @Test
    public void noQueryTermEnterShowDialog() throws Exception {
        queryTerm.setText("");
        searchButton.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog shadowAlertDialog = shadowOf(dialog);

        assertEquals(activity.getString(R.string.missing_term_message), shadowAlertDialog.getMessage().toString());
    }

    @Test
    public void queryTermIncorrectEnterShowDialog() throws Exception {
        queryTerm.setText("$!@");
        searchButton.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog shadowAlertDialog = shadowOf(dialog);

        assertEquals(activity.getString(R.string.incorrect_term_message), shadowAlertDialog.getMessage().toString());
    }

    @Test
    public void noSectionSelectedShowDialog() throws Exception {
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
        searchButton.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog shadowAlertDialog = shadowOf(dialog);

        assertEquals(activity.getString(R.string.missing_section_message), shadowAlertDialog.getMessage().toString());
    }

}
