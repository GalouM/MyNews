package com.galou.mynews.searchNotification;

import com.galou.mynews.BaseContractView;

import java.util.List;

/**
 * Created by galou on 2019-04-02
 */
public interface NotificationContract {
    interface View extends BaseContractView<Presenter> {
        void displayErrorQueryTerm(ErrorMessage errorMessage);
        void displayErrorSections(ErrorMessage errorMessage);
        void disableAllErrors();
        void onClickNotificationSwitch();
        void enableNotifications();
        void disableNotification();
        void showNotificationEnabledMessage();
        void showNotificationDisableMessage();
        void saveUIState();
        void saveNotificationSettings(String queryTerms, String querySections);
    }
    interface Presenter {
        void setTermsQuery(String queryTerm, List<String> querySection);
        void notifyNotificationDisabled();
    }
}
