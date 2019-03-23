package com.galou.mynews.controllers.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.galou.mynews.R;
import com.galou.mynews.controllers.fragments.BaseFragmentSearch;
import com.galou.mynews.controllers.fragments.NotificationsFragment;

import java.util.List;

public class NotificationsActivity extends BaseActivity implements BaseFragmentSearch.OnButtonClickedListener {

    private NotificationsFragment notificationsFragment;

    //for data
    private String queryTerm;
    private List<String> querySection;
    private boolean isNotificationsEnabled;

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_notifications;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.configureAndShowNotificationsFragment();
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    private void configureAndShowNotificationsFragment(){
        notificationsFragment = (NotificationsFragment) getSupportFragmentManager().findFragmentById(R.id.notifications_activity_frame_layout);
        if (notificationsFragment == null){
            notificationsFragment = new NotificationsFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.notifications_activity_frame_layout, notificationsFragment)
                    .commit();
        }

    }

    // -------------------
    // ACTION
    // -------------------

    @Override
    public void onButtonClicked() {
        if(isNotificationsEnabled){
            String text = "Notifications enabled" + "\n"
                    + "Query Term: " + queryTerm + "\n"
                    + "Sections: " + querySection;
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        } else {
            String text = "Notifications disabled";
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }

    }

    // -------------------
    // SETTERS
    // -------------------

    public void setQueryTerm(String queryTerm) {
        this.queryTerm = queryTerm;
    }

    public void setQuerySection(List<String> querySection) {
        this.querySection = querySection;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        isNotificationsEnabled = notificationsEnabled;
    }
}
