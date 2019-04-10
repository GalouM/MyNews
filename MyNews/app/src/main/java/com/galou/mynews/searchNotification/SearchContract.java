package com.galou.mynews.searchNotification;

import android.support.annotation.Nullable;

import com.galou.mynews.BaseContractView;
import com.galou.mynews.models.ArticleSearch;
import com.galou.mynews.models.SectionSearch;

import java.util.List;

/**
 * Created by galou on 2019-04-02
 */
public interface SearchContract {
    interface View extends BaseContractView<Presenter> {
        void showResultResearch(SectionSearch searchQuery);
        void displayErrorQueryTerm(ErrorMessage errorMessage);
        void displayErrorSections(ErrorMessage errorMessage);
        void disableAllErrors();
        void displayErrorBeginDate(ErrorMessage errorMessage);
        void displayErrorEndDate(ErrorMessage errorMessage);
        void onClickSearchButton();
        void showSnackBar();
    }

    interface Presenter {
        void startSearch(String queryTerm, @Nullable String beginDate, @Nullable String endate, List<String> sections);
        void disposeWhenDestroy();
    }
}
