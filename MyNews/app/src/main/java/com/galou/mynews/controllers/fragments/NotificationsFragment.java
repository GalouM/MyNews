package com.galou.mynews.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.galou.mynews.R;

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
    public void onClickNotificationSwitch(){
        if(switchNotification.isChecked()) {
            Toast.makeText(getActivity(), "yop", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "nope", Toast.LENGTH_LONG).show();
        }
    }
}
