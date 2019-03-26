package com.galou.mynews;

import android.app.AlertDialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SwitchCompat;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.galou.mynews.controllers.activities.NotificationsActivity;
import com.galou.mynews.controllers.dialogs.PickDateDialog;
import com.galou.mynews.controllers.fragments.NotificationsFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by galou on 2019-03-25
 */
@RunWith(RobolectricTestRunner.class)
public class NotificationFragmentUnitTest {
    private NotificationsActivity activity;
    private SwitchCompat notificationEnabled;
    private EditText queryTerm;


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(NotificationsActivity.class).create().resume().get();
        notificationEnabled = activity.findViewById(R.id.notification_fragment_switch);
        queryTerm = activity.findViewById(R.id.query_term);
    }

    @Test
    public void noQueryTermEnterShowDialog() throws Exception {
        queryTerm.setText("");
        notificationEnabled.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog shadowAlertDialog = shadowOf(dialog);

        assertEquals(activity.getString(R.string.missing_term_message), shadowAlertDialog.getMessage().toString());
        assertFalse(notificationEnabled.isChecked());
    }

    @Test
    public void queryTermIncorrectEnterShowDialog() throws Exception {
        queryTerm.setText("$!@");
        notificationEnabled.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog shadowAlertDialog = shadowOf(dialog);

        assertEquals(activity.getString(R.string.incorrect_term_message), shadowAlertDialog.getMessage().toString());
        assertFalse(notificationEnabled.isChecked());
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
        notificationEnabled.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog shadowAlertDialog = shadowOf(dialog);

        assertEquals(activity.getString(R.string.missing_section_message), shadowAlertDialog.getMessage().toString());
        assertFalse(notificationEnabled.isChecked());
    }
}
