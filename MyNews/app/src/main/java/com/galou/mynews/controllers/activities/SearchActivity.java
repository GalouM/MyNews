package com.galou.mynews.controllers.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.galou.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        this.configureToolbar();
    }

    private void configureToolbar(){
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
