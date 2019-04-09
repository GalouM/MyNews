package com.galou.mynews.consultArticles;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * <b>
 *     Represent a Page Adapter
 * </b>
 *
 * <p>
 *
 *
 *     A {@link FragmentStatePagerAdapter} subclass
 * </p>
 *
 */
public class PageAdapter extends FragmentStatePagerAdapter {

    private ArticleListPresenter presenter;
    private ArticleListView articleListView;


    public PageAdapter(FragmentManager mgr){
        super(mgr);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                articleListView = ArticleListView.newInstance("home");
                break;
            case 1:
                articleListView = ArticleListView.newInstance("mostpopular");
                break;
            case 2:
                articleListView = ArticleListView.newInstance("sports");
                break;

        }
        presenter = new ArticleListPresenter(articleListView);
        return articleListView;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "TOP STORIES";
            case 1:
                return "MOST POPULAR";
            case 2:
                return "SPORTS";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return (3);
    }
}
