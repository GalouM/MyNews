package com.galou.mynews.searchNotification;


import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.widget.Toast;

import com.galou.mynews.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsView extends BaseView implements NotificationContract.View {

    // --------------

    private NotificationContract.Presenter presenter;

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
            presenter.enableNotification(queryTerms, querySections);
        }
    }

    @Override
    public void showNotificationEnabledMessage(String termSearch) {
        Toast.makeText(getContext(), termSearch, Toast.LENGTH_LONG).show();

    }

    @Override
    public void disableNotification() {
        switchNotification.setChecked(false);

    }


    @Override
    public void setPresenter(NotificationContract.Presenter presenter) {
        this.presenter = presenter;

    }


    // --------------
    // SET ERROR MESSAGES
    // --------------

    @Override
    public void displayErrorQueryTerm(ErrorMessage errorMessage) {
        switch (errorMessage){
            case EMPTY:
                queryTermInputLayout.setError(getString(R.string.error_message_query_empty));
                break;
            case INCORRECT:
                queryTermInputLayout.setError(getString(R.string.error_message_query_incorrect));
                break;
        }
    }

    @Override
    public void displayErrorSections(ErrorMessage errorMessage) {
        switch (errorMessage){
            case EMPTY:
                querySectionInputLayout.setError(getString(R.string.error_message_no_section_selected));
                break;
        }
    }

    @Override
    public void disableAllErrors() {
        queryTermInputLayout.setError(null);
        queryTermInputLayout.setErrorEnabled(false);
        querySectionInputLayout.setError(null);
        querySectionInputLayout.setErrorEnabled(false);

    }
}
