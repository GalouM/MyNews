package com.galou.mynews.webViewArticle;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.galou.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewArticleActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    private String url;

    public static final String KEY_URL = "url";
    private WebViewView webViewView;
    private WebViewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_article);
        ButterKnife.bind(this);
        configureToolbar();
        getUrl();
        configureAndShowFragment();
        setPresenter();
    }

    private void configureToolbar(){
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    private void getUrl(){
        url = getIntent().getStringExtra(KEY_URL);

    }

    private void configureAndShowFragment(){
        webViewView = (WebViewView) getSupportFragmentManager().findFragmentById(R.id.web_view_frame_layout);
        if (webViewView == null){
            webViewView = new WebViewView();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.web_view_frame_layout, webViewView)
                    .commit();
        }

    }

    private void setPresenter(){
        presenter = new WebViewPresenter(webViewView, url);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
