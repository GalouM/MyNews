package com.galou.mynews.webViewArticle;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.galou.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewArticleActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.web_view) WebView webView;
    @BindView(R.id.web_view_root_view) CoordinatorLayout rootView;

    private String url;

    public static final String KEY_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_article);
        ButterKnife.bind(this);
        configureToolbar();
        getUrl();
        setWebView();
    }

    private void configureToolbar(){
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    private void getUrl(){
        url = getIntent().getStringExtra(KEY_URL);
        if (url == null || url.isEmpty()){
            showSnackBar();
        }

    }

    private void showSnackBar(){
        Snackbar snackbar = Snackbar.make(rootView, R.string.incorrect_url, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.hide_button_snackbar, view -> snackbar.dismiss());
        snackbar.show();

    }

    private void setWebView(){
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
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
