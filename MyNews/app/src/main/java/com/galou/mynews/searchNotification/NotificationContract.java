package com.galou.mynews.searchNotification;

import com.galou.mynews.BaseContractView;

import java.util.List;

/**
 * Created by galou on 2019-04-02
 */
public interface NotificationContract {
    interface ContractView extends BaseContractView<Presenter> {
        void displayErrorQueryTerm(ErrorMessage errorMessage);
        void displayErrorSections(ErrorMessage errorMessage);
        void disableAllErrors();
        void onClickNotificationSwitch();
        void showNotificationEnabledMessage(String termSearch);
        void disableNotification();
    }
    interface Presenter {
        void enableNotification(String queryTerm, List<String> querySection);
    }
}
