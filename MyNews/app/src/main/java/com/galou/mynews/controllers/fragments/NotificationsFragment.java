package com.galou.mynews.controllers.fragments;


import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;

import com.galou.mynews.R;
import com.galou.mynews.utils.TextUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends BaseFragmentSearch {

    // interface
    protected OnButtonClickedListener mCallback;

    public interface OnButtonClickedListener{
        void onButtonNotificationClicked(Boolean isNotificationEnabled);
        void onNotificationActivated(String[] queryTerm, List<String> querySections);
    }

    // --------------

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
        this.setQueryTerm();
        this.setQuerySections();
        if(switchNotification.isChecked()) {
            if (!isAllDataCorrect()) {
                switchNotification.setChecked(false);
            } else {
                listQueryTerms = TextUtil.separateTextBySpace(queryTerms);
                mCallback.onNotificationActivated(listQueryTerms, querySections);
            }
        }
        Boolean isNotificationOn = switchNotification.isChecked();
        mCallback.onButtonNotificationClicked(isNotificationOn);
    }

    @Override
    protected Boolean isAllDataCorrect() {
        return  !(!isQueryTermCorrect() | !isOneSectionSelected());
    }

    // --------------
    // FRAGMENT SUPPORT
    // --------------

    @Override
    protected void createCallbackToParentActivity(){
        try{
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString()+"must implement OnButtonClickedListener");
        }
    }

}
