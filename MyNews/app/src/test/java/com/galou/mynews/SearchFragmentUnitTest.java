package com.galou.mynews;

import android.support.v4.app.FragmentManager;
import android.widget.EditText;

import com.galou.mynews.controllers.activities.SearchActivity;
import com.galou.mynews.controllers.fragments.SearchFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowAlertDialog;

/**
 * Created by galou on 2019-03-22
 */
@RunWith(RobolectricTestRunner.class)
public class SearchFragmentUnitTest {

    private SearchActivity activity;
    private FragmentManager fragmentManager;
    private SearchFragment fragment;


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(SearchActivity.class).create().resume().get();
        fragmentManager = (FragmentManager) activity.getSupportFragmentManager();
        fragment = new SearchFragment();
    }

    @Test
    public void noQueryTermEntershowDialog() throws Exception {
        EditText queryTerm = activity.findViewById(R.id.query_term);
        queryTerm.setText("");
        ShadowAlertDialog.getShownDialogs();
    }
}
