package com.galou.mynews.searchNotification;

import android.support.annotation.NonNull;

import com.galou.mynews.utils.TextUtil;

import java.util.List;

import io.reactivex.disposables.Disposable;

import static com.galou.mynews.searchNotification.ErrorMessage.EMPTY;
import static com.galou.mynews.searchNotification.ErrorMessage.INCORRECT;
import static com.galou.mynews.utils.TextUtil.isTextContainsSpecialCharacter;

/**
 * Created by galou on 2019-04-02
 */
public class NotificationPresenter implements NotificationContract.Presenter {

    private String queryTerm;
    private List<String> sections;
    private Disposable disposable;

    private String queryTermsForAPI;
    private String querySectionForAPI;

    private NotificationContract.View notificationView;

    public NotificationPresenter(@NonNull NotificationContract.View notificationView) {
        this.notificationView = notificationView;
        this.notificationView.setPresenter(this);
    }

    @Override
    public void setTermsQuery(String queryTerm, List<String> querySection) {
        this.queryTerm = queryTerm;
        this.sections = querySection;
        notificationView.disableAllErrors();
        if (allDataAreCorrect()){
            convertDataForApi();
            notificationView.enableNotifications(querySectionForAPI, queryTermsForAPI);
            notificationView.showNotificationEnabledMessage();
            notificationView.saveSettingsNotification();
        } else {
            notifyNotificationDisabled();
        }

    }

    @Override
    public void notifyNotificationDisabled() {
        notificationView.disableNotification();
        notificationView.showNotificationDisableMessage();
        notificationView.saveSettingsNotification();

    }

    // --------------
    // CHECK DATA CORRECT
    // --------------

    private boolean allDataAreCorrect(){
        return !(!isQueryTermCorrect() | !isQuerySectionCorrect());

    }

    private boolean isQueryTermCorrect() {
        if (queryTerm.length() <= 0) {
            notificationView.displayErrorQueryTerm(EMPTY);
            return false;
        }
        if (isTextContainsSpecialCharacter(queryTerm)) {
            notificationView.displayErrorQueryTerm(INCORRECT);
            return false;
        }
        return true;


    }

    private boolean isQuerySectionCorrect(){
        if (sections.size() <= 0) {
            notificationView.displayErrorSections(EMPTY);
            return false;
        }

        return true;
    }

    // --------------
    // CONVERT DATA FOR API
    // --------------

    private void convertDataForApi(){
        queryTermsForAPI = TextUtil.convertQueryTermForAPI(queryTerm);
        querySectionForAPI = "news_desk%3A" + TextUtil.convertListInStringForAPI(sections);

    }
}
