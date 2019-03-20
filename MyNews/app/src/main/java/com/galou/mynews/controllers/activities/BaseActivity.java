package com.galou.mynews.controllers.activities;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
        ButterKnife.bind(this);
        this.configureToolbar();
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    protected void configureToolbar(){
        setSupportActionBar(toolbar);
        if (getActivityLayout() != R.layout.activity_main) {
            ActionBar ab = getSupportActionBar();
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }
}
