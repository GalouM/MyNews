package com.galou.mynews.consultArticles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.galou.mynews.R;
import com.galou.mynews.models.ArticleTopStories;

import java.util.List;

/**
 * Created by galou on 2019-03-30
 */
public class ArticleTopStoriesAdapter extends RecyclerView.Adapter<ArticleTopStoriesViewHolder> {

    private List<ArticleTopStories> listArticle;
    private RequestManager glide;

    public ArticleTopStoriesAdapter(List<ArticleTopStories> listArticle, RequestManager glide) {
        this.listArticle = listArticle;
        this.glide = glide;
    }

    @NonNull
    @Override
    public ArticleTopStoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.article_item_view, parent, false);
        return new ArticleTopStoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleTopStoriesViewHolder articleMostPopularViewHolder, int position) {
        articleMostPopularViewHolder.updateWithArticles(this.listArticle.get(position), this.glide);

    }

    @Override
    public int getItemCount() {
        return listArticle.size();
    }

    public void update(List<ArticleTopStories> articles){
        this.listArticle = articles;
        this.notifyDataSetChanged();
    }

    public ArticleTopStories getArticle(int position){
        return this.listArticle.get(position);
    }
}
