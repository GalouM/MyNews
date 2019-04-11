package com.galou.mynews.searchNotification;

import com.galou.mynews.utils.TextUtil;

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
    private String queryTermsForAPI;
    private String querySectionForAPI;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        presenter = new NotificationPresenter(notificationView);

        // set correct data
        mockedQueryTerm = "term1 term2";
        mockedSectionQuery = new ArrayList<>();
        mockedSectionQuery.add("Arts");
        mockedSectionQuery.add("Travel");
        queryTermsForAPI = TextUtil.convertQueryTermForAPI(mockedQueryTerm);
        querySectionForAPI = "news_desk%3A" + TextUtil.convertListInStringForAPI(mockedSectionQuery);

    }

    @Test
    public void enableNotificationWithCorrectData_onNotification(){
        presenter.setTermsQuery(mockedQueryTerm, mockedSectionQuery);

        verify(notificationView).disableAllErrors();
        verify(notificationView).showNotificationEnabledMessage();
        verify(notificationView).saveSettingsNotification();
        verify(notificationView).enableNotifications(querySectionForAPI, queryTermsForAPI);

    }

    @Test
    public void enableNotificationMissingQueryTerm_showError(){
        mockedQueryTerm = "";

        presenter.setTermsQuery(mockedQueryTerm, mockedSectionQuery);

        verify(notificationView).disableAllErrors();
        verify(notificationView).displayErrorQueryTerm(ErrorMessage.EMPTY);
        verify(notificationView).disableNotification();
        verify(notificationView).showNotificationDisableMessage();
        verify(notificationView).saveSettingsNotification();

    }

    @Test
    public void clickSearchButtonWrongQueryTerm_showErrorQueryTerm(){
        mockedQueryTerm = "@@@ 555 geer";

        presenter.setTermsQuery(mockedQueryTerm, mockedSectionQuery);

        verify(notificationView).disableAllErrors();
        verify(notificationView).displayErrorQueryTerm(ErrorMessage.INCORRECT);
        verify(notificationView).disableNotification();
        verify(notificationView).showNotificationDisableMessage();
        verify(notificationView).saveSettingsNotification();

    }

    @Test
    public void clickSearchButtonNoSectionSelected_showErrorQueryTerm(){
        mockedSectionQuery.remove(0);
        mockedSectionQuery.remove(0);

        presenter.setTermsQuery(mockedQueryTerm, mockedSectionQuery);

        verify(notificationView).disableAllErrors();
        verify(notificationView).displayErrorSections(ErrorMessage.EMPTY);
        verify(notificationView).disableNotification();
        verify(notificationView).showNotificationDisableMessage();
        verify(notificationView).saveSettingsNotification();

    }

    @Test
    public void disableNotification_showMessage(){
        presenter.notifyNotificationDisabled();

        verify(notificationView).showNotificationDisableMessage();
        verify(notificationView).disableNotification();
        verify(notificationView).saveSettingsNotification();
    }
}
