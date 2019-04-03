package com.galou.mynews.searchNotification;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.galou.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by galou on 2019-03-19
 */
public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    protected abstract int getActivityLayout();
    protected abstract void setPresenter();
    protected abstract void configureAndShowFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
        ButterKnife.bind(this);
        this.configureToolbar();
        this.configureAndShowFragment();
        this.setPresenter();
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    protected void configureToolbar(){
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

}
