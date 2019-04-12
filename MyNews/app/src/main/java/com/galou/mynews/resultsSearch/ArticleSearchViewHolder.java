package com.galou.mynews.resultsSearch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.galou.mynews.R;
import com.galou.mynews.models.ArticleSearch;
import com.galou.mynews.utils.DateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.galou.mynews.utils.TextUtil.convertSectionNameForDisplay;

/**
 * Created by galou on 2019-04-09
 */
public class ArticleSearchViewHolder extends RecyclerView.ViewHolder  {
    @BindView(R.id.recycler_view_title_article) TextView title;
    @BindView(R.id.recycler_view_date) TextView date;
    @BindView(R.id.recycler_view_section_article) TextView sectionNameView;
    @BindView(R.id.recycler_view_image_article) ImageView imageView;

    public ArticleSearchViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithArticles(ArticleSearch article, RequestManager glide) {
        String headline = article.getHeadline().getPrintHeadline();
        if(headline == null){
            headline = article.getHeadline().getMain();
        }
        title.setText(headline);
        date.setText(DateUtil.convertDateFromAPIToDisplay(article.getPubDate()));
        String sectionName = convertSectionNameForDisplay(article.getSectionName());
        String subsection = article.getSubsectoinName();
        if (subsection == null) {
            sectionNameView.setText(sectionName);
        } else {
            sectionNameView.setText(sectionName + " > " + subsection);
        }
        if (article.getMultimedia().size() > 0) {
            String urlMedia = article.getMultimedia().get(0).getUrl();
            glide.load(urlMedia).apply(RequestOptions.centerCropTransform()).into(imageView);

        }
    }

}
