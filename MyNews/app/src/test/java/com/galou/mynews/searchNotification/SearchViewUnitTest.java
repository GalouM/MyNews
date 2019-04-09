package com.galou.mynews.searchNotification;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.galou.mynews.R;
import com.galou.mynews.searchNotification.SearchActivity;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.galou.mynews.utils.DateUtil.convertCalendarForDisplay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by galou on 2019-03-22
 */
@RunWith(RobolectricTestRunner.class)
public class SearchViewUnitTest {

    private SearchActivity activity;

    private Button searchButton;
    private EditText queryTerm;
    private EditText beginDate;
    private EditText endDate;
    private TextInputLayout queryTermInputLayout;
    private TextInputLayout querySectionInputLayout;
    private TextInputLayout beginDateInputLayout;
    private TextInputLayout endDateInputLayout;
    private CheckBox artCheck;
    private CheckBox businessCheck;
    private CheckBox entrepreneurCheck;
    private CheckBox politicsCheck;
    private CheckBox sportCheck;
    private CheckBox travelCheck;

    private  List<String> querySections;


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(SearchActivity.class).create().resume().get();

        //set views
        searchButton = activity.findViewById(R.id.search_fragment_search_button);
        queryTerm = (EditText) activity.findViewById(R.id.query_term);
        beginDate = (EditText) activity.findViewById(R.id.search_fragment_start_begin_date);
        endDate = (EditText) activity.findViewById(R.id.search_fragment_search_end_date);
        queryTermInputLayout = (TextInputLayout) activity.findViewById(R.id.query_term_input_layout);
        querySectionInputLayout = (TextInputLayout) activity.findViewById(R.id.query_sections_input_layout);
        beginDateInputLayout = (TextInputLayout) activity.findViewById(R.id.begin_date_input_layout);
        endDateInputLayout = (TextInputLayout) activity.findViewById(R.id.end_date_input_layout);
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

        querySections = new ArrayList<>();
        querySections.add(artCheck.getText().toString());
        querySections.add(businessCheck.getText().toString());
        querySections.add(entrepreneurCheck.getText().toString());
        querySections.add(sportCheck.getText().toString());
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
        artCheck.setChecked(false);
        businessCheck.setChecked(false);
        entrepreneurCheck.setChecked(false);
        sportCheck.setChecked(false);
        querySections.remove(3);
        querySections.remove(2);
        querySections.remove(1);
        querySections.remove(0);

        searchButton.performClick();

        assertTrue(querySectionInputLayout.isErrorEnabled());

    }

    @Test
    public void beginDateInTheFutureShowErrorMessage() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        String calInString = convertCalendarForDisplay(calendar);
        beginDate.setText(calInString);
        searchButton.performClick();

        assertTrue(beginDateInputLayout.isErrorEnabled());

    }

    @Test
    public void endDateInTheFutureShowErrorMessage() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        String calInString = convertCalendarForDisplay(calendar);
        endDate.setText(calInString);
        searchButton.performClick();

        assertTrue(endDateInputLayout.isErrorEnabled());

    }

    @Test
    public void wrongBeginDateShowErrorMessage() throws Exception {
        beginDate.setText("22333");
        searchButton.performClick();

        assertTrue(beginDateInputLayout.isErrorEnabled());

    }

    @Test
    public void wrongEndDateShowErrorMessage() throws Exception {
        endDate.setText("22333");
        searchButton.performClick();

        assertTrue(endDateInputLayout.isErrorEnabled());

    }

    @Test
    public void endDateBeforeBeginDateShowErrorMessage() throws Exception {
        beginDate.setText("03/03/2019");
        endDate.setText("03/02/2019");
        searchButton.performClick();

        assertTrue(endDateInputLayout.isErrorEnabled());

    }

    @Test
    public void clickSearchAllDataCorrectWithNoDateStartSearch() throws Exception {
        searchButton.performClick();

        String[] queryTermList = {"test", "test2"};


        String messageToast = "Query Term: " + Arrays.toString(queryTermList) + "\n"
                + "Begin Date: null" +  "\n"
                + "End Date: null" + "\n"
                + "SectionMostPopular: " + querySections;

        TestCase.assertEquals(ShadowToast.getTextOfLatestToast(), messageToast);
        assertFalse(queryTermInputLayout.isErrorEnabled());
        assertFalse(querySectionInputLayout.isErrorEnabled());
        assertNull(beginDateInputLayout.getError());
        assertNull(endDateInputLayout.getError());

    }

    @Test
    public void clickSearchAllDataCorrectWithDateStartSearch() throws Exception {
        beginDate.setText("03/03/19");
        endDate.setText("04/03/19");
        searchButton.performClick();

        String[] queryTermList = {"test", "test2"};


        String messageToast = "Query Term: " + Arrays.toString(queryTermList) + "\n"
                + "Begin Date: 20190303" +  "\n"
                + "End Date: 20190304" + "\n"
                + "SectionMostPopular: " + querySections;

        TestCase.assertEquals(ShadowToast.getTextOfLatestToast(), messageToast);
        assertFalse(queryTermInputLayout.isErrorEnabled());
        assertFalse(querySectionInputLayout.isErrorEnabled());
        assertFalse(beginDateInputLayout.isErrorEnabled());
        assertFalse(endDateInputLayout.isErrorEnabled());

    }

    @Test
    public void clickSearchAllDataCorrectWithNoBeginDateStartSearch() throws Exception {
        endDate.setText("04/03/19");
        searchButton.performClick();

        String[] queryTermList = {"test", "test2"};


        String messageToast = "Query Term: " + Arrays.toString(queryTermList) + "\n"
                + "Begin Date: null" +   "\n"
                + "End Date: 20190304" + "\n"
                + "SectionMostPopular: " + querySections;

        TestCase.assertEquals(ShadowToast.getTextOfLatestToast(), messageToast);
        assertFalse(queryTermInputLayout.isErrorEnabled());
        assertFalse(querySectionInputLayout.isErrorEnabled());
        assertNull(beginDateInputLayout.getError());
        assertFalse(endDateInputLayout.isErrorEnabled());


    }

    @Test
    public void clickSearchAllDataCorrectWithEndStartSearch() throws Exception {
        beginDate.setText("03/03/19");
        searchButton.performClick();

        String[] queryTermList = {"test", "test2"};


        String messageToast = "Query Term: " + Arrays.toString(queryTermList) + "\n"
                + "Begin Date: 20190303" +  "\n"
                + "End Date: null" + "\n"
                + "SectionMostPopular: " + querySections;

        TestCase.assertEquals(ShadowToast.getTextOfLatestToast(), messageToast);
        assertFalse(queryTermInputLayout.isErrorEnabled());
        assertFalse(querySectionInputLayout.isErrorEnabled());
        assertFalse(beginDateInputLayout.isErrorEnabled());
        assertNull(endDateInputLayout.getError());

    }

    // test date dialogs

    @Test
    public void clickBeginDateOpenDialog() throws Exception{
        beginDate.performClick();
        AlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();

        assertNotNull(alertDialog);

    }

    @Test
    public void clickEndDateOpenDialog() throws Exception{
        endDate.performClick();
        AlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();

        assertNotNull(alertDialog);

    }

    @Test
    public void clickCancelDialogCloseDialog() throws Exception{
        endDate.performClick();
        AlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        Button buttonCancel = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonCancel.performClick();

        assertFalse(alertDialog.isShowing());

    }

    @Test
    public void clickOkDialogEndDateFillEnDate() throws Exception{
        endDate.performClick();
        AlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        Button buttonOK = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonOK.performClick();

        assertTrue(endDate.getText().length() > 0);

    }

    @Test
    public void clickOkDialogBeginDateFillEnDate() throws Exception{
        beginDate.performClick();
        AlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        Button buttonOK = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonOK.performClick();

        assertTrue(beginDate.getText().length() > 0);

    }

    @Test
    public void existingBeginDateSetDatePicker() throws Exception{
        String date = "03/03/19";
        beginDate.setText(date);
        beginDate.performClick();
        AlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        Button buttonOK = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonOK.performClick();

        assertEquals(date, beginDate.getText().toString());

    }

    @Test
    public void existingEndDateSetDatePicker() throws Exception{
        String date = "03/03/19";
        endDate.setText(date);
        endDate.performClick();
        AlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        Button buttonOK = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonOK.performClick();

        assertEquals(date, endDate.getText().toString());

    }


}
