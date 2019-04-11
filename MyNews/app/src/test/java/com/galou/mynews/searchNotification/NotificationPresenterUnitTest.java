package com.galou.mynews.searchNotification;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by galou on 2019-04-03
 */
@RunWith(MockitoJUnitRunner.class)
public class NotificationPresenterUnitTest {


    @Mock
    private NotificationContract.View notificationView;

    private NotificationPresenter presenter;

    private String mockedQueryTerm;
    private List<String> mockedSectionQuery;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        presenter = new NotificationPresenter(notificationView);

        // set correct data
        mockedQueryTerm = "term1 term2";
        mockedSectionQuery = new ArrayList<>();
        mockedSectionQuery.add("Arts");
        mockedSectionQuery.add("Travel");

    }

    @Test
    public void enableNotificationWithCorrectData_onNotification(){
        String termsSearch = "Query Term: [term1, term2]" + "\n"
                + "SectionMostPopular: [Arts, Travel]";

        presenter.setTermsQuery(mockedQueryTerm, mockedSectionQuery);
        verify(notificationView).disableAllErrors();
        //verify(notificationView).showNotificationEnabledMessage(termsSearch);

    }

    @Test
    public void enableNotificationMissingQueryTerm_showError(){
        mockedQueryTerm = "";

        presenter.setTermsQuery(mockedQueryTerm, mockedSectionQuery);
        verify(notificationView).disableAllErrors();
        verify(notificationView).displayErrorQueryTerm(ErrorMessage.EMPTY);

    }

    @Test
    public void clickSearchButtonWrongQueryTerm_showErrorQueryTerm(){
        mockedQueryTerm = "@@@ 555 geer";

        presenter.setTermsQuery(mockedQueryTerm, mockedSectionQuery);
        verify(notificationView).disableAllErrors();
        verify(notificationView).displayErrorQueryTerm(ErrorMessage.INCORRECT);

    }

    @Test
    public void clickSearchButtonNoSectionSelected_showErrorQueryTerm(){
        mockedSectionQuery.remove(0);
        mockedSectionQuery.remove(0);

        presenter.setTermsQuery(mockedQueryTerm, mockedSectionQuery);
        verify(notificationView).disableAllErrors();
        verify(notificationView).displayErrorSections(ErrorMessage.EMPTY);

    }
}
