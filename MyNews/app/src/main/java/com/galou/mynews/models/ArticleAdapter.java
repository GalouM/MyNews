package com.galou.mynews.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galou.mynews.R;

import java.util.List;

/**
 * Created by galou on 2019-03-30
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<Article> listArticle;

    public ArticleAdapter(List<Article> listArticle) {
        this.listArticle = listArticle;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.article_item_view, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder articleViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return listArticle.size();
    }

    public void update(List<Article> articles){
        this.listArticle = articles;
        this.notifyDataSetChanged();
    }
}
