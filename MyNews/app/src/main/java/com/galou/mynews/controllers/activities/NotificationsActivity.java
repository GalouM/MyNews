package com.galou.mynews.controllers.activities;

import android.os.Bundle;
import android.widget.Toast;

import com.galou.mynews.R;
import com.galou.mynews.controllers.fragments.NotificationsFragment;

import java.util.Arrays;
import java.util.List;

public class NotificationsActivity extends BaseActivity implements NotificationsFragment.OnButtonClickedListener {

    private NotificationsFragment notificationsFragment;

    //for data
    private String queryTerm;
    private List<String> querySections;
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
    public void onButtonNotificationClicked(Boolean isNotificationEnabled) {
        if(isNotificationEnabled){
            String text = "Notifications enabled";
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        } else {
            String text = "Notifications disabled";
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNotificationActivated(String[] queryTerm, List<String> querySections) {
        String text = "Notifications enabled" + "\n"
                + "Query Term: " + Arrays.toString(queryTerm) + "\n"
                + "Sections: " + querySections;
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();

    }
}
