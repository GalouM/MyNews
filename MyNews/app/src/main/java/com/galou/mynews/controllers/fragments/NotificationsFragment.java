package com.galou.mynews.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.galou.mynews.R;
import com.galou.mynews.controllers.activities.NotificationsActivity;
import com.galou.mynews.models.ErrorSelection;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends BaseFragmentSearch {

    //views
    @BindView(R.id.notification_fragment_switch) SwitchCompat switchNotification;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_notifications;
    }

    // --------------
    // ACTIONS
    // --------------
    @OnClick(R.id.notification_fragment_switch)
    public void onClickNotificationSwitch(final View view){
        NotificationsActivity activity = (NotificationsActivity) getActivity();
        if(switchNotification.isChecked()) {
            if (getQueryTerm().length() <= 0){
                this.showAlertDialog(ErrorSelection.TERM);
                switchNotification.setChecked(false);
            } else if (getQuerySections().isEmpty()) {
                this.showAlertDialog(ErrorSelection.SECTION);
                switchNotification.setChecked(false);
            } else {
                activity.setQueryTerm(this.getQueryTerm());
                activity.setQuerySection(this.getQuerySections());
            }
        }
        activity.setNotificationsEnabled(switchNotification.isChecked());
        mCallback.onButtonClicked();
    }
}
