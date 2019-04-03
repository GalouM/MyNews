package com.galou.mynews.searchNotification;

import android.support.annotation.NonNull;

import com.galou.mynews.utils.TextUtil;

import java.util.Arrays;
import java.util.List;

import static com.galou.mynews.searchNotification.ErrorMessage.EMPTY;
import static com.galou.mynews.searchNotification.ErrorMessage.INCORRECT;
import static com.galou.mynews.utils.TextUtil.isTextContainsSpecialCharacter;

/**
 * Created by galou on 2019-04-02
 */
public class NotificationPresenter implements NotificationContract.Presenter {

    private String queryTerm;
    private List<String> sections;

    private NotificationContract.ContractView notificationView;

    public NotificationPresenter(@NonNull NotificationContract.ContractView notificationView) {
        this.notificationView = notificationView;
        this.notificationView.setPresenter(this);
    }

    @Override
    public void enableNotification(String queryTerm, List<String> querySection) {
        this.queryTerm = queryTerm;
        this.sections = querySection;
        notificationView.disableAllErrors();
        if (allDataAreCorrect()){
            String[] queryTerms = TextUtil.separateTextBySpace(queryTerm);

            String termsSearch = "Query Term: " + Arrays.toString(queryTerms) + "\n"
                    + "Section: " + sections;

            notificationView.showNotificationEnabledMessage(termsSearch);
        } else {
            notificationView.disableNotification();
        }

    }

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
}
