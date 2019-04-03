package com.galou.mynews.consultArticles;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galou.mynews.R;
import com.galou.mynews.models.ApiStreams;
import com.galou.mynews.models.Article;
import com.galou.mynews.models.ArticleAdapter;
import com.galou.mynews.models.Section;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularView extends Fragment {

    private List<Article> articles;
    private ArticleAdapter adapter;
    private Disposable disposable;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;


    public MostPopularView() {}

    public static MostPopularView newInstance(){
        return (new MostPopularView());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_most_pop, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView();
        this.getArticlesFromNYT();

        return view;
    }

    private void configureRecyclerView(){
        articles = new ArrayList<>();
        adapter = new ArticleAdapter(articles);
        recyclerView.setAdapter(adapter);
    }

    private void getArticlesFromNYT(){
        this.disposable = ApiStreams.streamsFetchMostPopSection().subscribeWith(new DisposableObserver<Section>() {
            @Override
            public void onNext(Section section) {
                updateUI(section.getResults());

            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "On error"+Log.getStackTraceString(e));

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void updateUI(List<Article> articles){
        this.articles.addAll(articles);
        adapter.update(this.articles);
        Log.e("size", String.valueOf(articles.size()));
    }




}
