package com.galou.mynews.searchNotification;

import com.galou.mynews.R;

public class NotificationsActivity extends BaseActivity{

    private NotificationsView notificationsView;
    private NotificationPresenter presenter;

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_notifications;
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    @Override
    protected void configureAndShowFragment(){
        notificationsView = (NotificationsView) getSupportFragmentManager().findFragmentById(R.id.notifications_activity_frame_layout);
        if (notificationsView == null){
            notificationsView = new NotificationsView();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.notifications_activity_frame_layout, notificationsView)
                    .commit();
        }

    }

    @Override
    protected void setPresenter() {
        presenter = new NotificationPresenter(notificationsView);

    }
}
