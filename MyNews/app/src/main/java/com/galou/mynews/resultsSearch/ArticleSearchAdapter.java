package com.galou.mynews.resultsSearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.galou.mynews.R;
import com.galou.mynews.models.ArticleSearch;

import java.util.List;

/**
 * Created by galou on 2019-04-09
 */
public class ArticleSearchAdapter extends RecyclerView.Adapter<ArticleSearchViewHolder>  {

    private List<ArticleSearch> listArticle;
    private RequestManager glide;

    public ArticleSearchAdapter(List<ArticleSearch> listArticle, RequestManager glide) {
        this.listArticle = listArticle;
        this.glide = glide;
    }

    @NonNull
    @Override
    public ArticleSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.article_item_view, parent, false);
        return new ArticleSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleSearchViewHolder articleSearchViewHolder, int position) {
        articleSearchViewHolder.updateWithArticles(this.listArticle.get(position), this.glide);

    }

    @Override
    public int getItemCount() {
        return listArticle.size();
    }

    public ArticleSearch getArticle(int position){
        return this.listArticle.get(position);
    }

    public void update(List<ArticleSearch> articles){
        this.listArticle = articles;
        this.notifyDataSetChanged();
    }

    public void addArticlesUpdate(List<ArticleSearch> articles){
        listArticle.addAll(articles);
        this.notifyDataSetChanged();
    }
}
