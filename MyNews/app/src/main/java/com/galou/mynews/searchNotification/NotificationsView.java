package com.galou.mynews.searchNotification;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galou.mynews.R;
import com.galou.mynews.utils.NotificationReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_QUERY_SECTIONS;
import static com.galou.mynews.searchNotification.SearchView.BUNDLE_KEY_QUERY_TERM;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsView extends BaseView implements NotificationContract.View {

    // --------------

    private NotificationContract.Presenter presenter;

    public static final int NOTIFICATION_ID = 333;

    private PendingIntent pendingIntent;
    private Intent notificationIntent;
    private SharedPreferences preferences;

    public static final String KEY_PREF_ART = "artSection";
    public static final String KEY_PREF_SPORT = "sportSection";
    public static final String KEY_PREF_BUSINESS = "businessSection";
    public static final String KEY_PREF_POLITICS = "politicSection";
    public static final String KEY_PREF_TRAVEL = "travelSection";
    public static final String KEY_PREF_ENTREPRENEURS = "entrepreneursSection";
    public static final String KEY_PREF_TERM = "terms";
    public static final String KEY_PREF_NOTIFICATION_ENABLE = "notificationEnabled";
    public static final String KEY_PREF = "prefNotification";

    //views
    @BindView(R.id.notification_fragment_switch) SwitchCompat switchNotification;
    @BindView(R.id.notifiction_view_root_view) CoordinatorLayout rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        ButterKnife.bind(this, view);
        configureNotificationIntent();
        getNotificationSettingsFromPreferences();
        setWatcherEditText();
        return view;
    }

    @Override
    public void setPresenter(NotificationContract.Presenter presenter) {
        this.presenter = presenter;

    }

    private void configureNotificationIntent(){
        notificationIntent = new Intent(getContext(), NotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getContext(), 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    // --------------
    // ACTIONS
    // --------------
    @OnClick(R.id.notification_fragment_switch)
    public void onClickNotificationSwitch(){
        this.setQueryTerm();
        this.setQuerySections();
        if(switchNotification.isChecked()) {
            presenter.setTermsQuery(queryTerms, querySections);
        } else {
            presenter.notifyNotificationDisabled();
        }
    }

    @OnClick({R.id.search_item_art, R.id.search_item_business, R.id.search_item_politics,
            R.id.search_item_entrepreneurs, R.id.search_item_travel, R.id.search_item_sport})
    public void onClickBoxesSection(){
        if(switchNotification.isChecked()) {
            this.setQuerySections();
            presenter.setTermsQuery(queryTerms, querySections);
        }
    }

    private void setWatcherEditText(){
        userTerm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                setQueryTerm();
                presenter.setTermsQuery(queryTerms, querySections);

            }
        });
    }

    // --------------
    // SAVE UI STATE
    // --------------

    @Override
    public void saveSettingsNotification(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_PREF_TERM, queryTerms);
        editor.putBoolean(KEY_PREF_NOTIFICATION_ENABLE, switchNotification.isChecked());
        editor.putBoolean(KEY_PREF_BUSINESS, boxBusiness.isChecked());
        editor.putBoolean(KEY_PREF_ART, boxArts.isChecked());
        editor.putBoolean(KEY_PREF_TRAVEL, boxTravel.isChecked());
        editor.putBoolean(KEY_PREF_SPORT, boxSport.isChecked());
        editor.putBoolean(KEY_PREF_ENTREPRENEURS, boxEntrepreneurs.isChecked());
        editor.putBoolean(KEY_PREF_POLITICS, boxPolitics.isChecked());
        editor.apply();
    }

    public void getNotificationSettingsFromPreferences(){
        preferences = getContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        boolean notificationEnabled = preferences.getBoolean(KEY_PREF_NOTIFICATION_ENABLE, false);
        switchNotification.setChecked(notificationEnabled);
        if (notificationEnabled){
            String termFromPref = preferences.getString(KEY_PREF_TERM, "");
            boolean isPoliticsChecked = preferences.getBoolean(KEY_PREF_POLITICS, false);
            boolean isArtsChecked = preferences.getBoolean(KEY_PREF_ART, false);
            boolean isBusinessChecked = preferences.getBoolean(KEY_PREF_BUSINESS, false);
            boolean isSportChecked = preferences.getBoolean(KEY_PREF_SPORT, false);
            boolean isTravelChecked = preferences.getBoolean(KEY_PREF_TRAVEL, false);
            boolean isEntrepreneurChecked = preferences.getBoolean(KEY_PREF_ENTREPRENEURS, false);
            userTerm.setText(termFromPref);
            boxPolitics.setChecked(isPoliticsChecked);
            boxEntrepreneurs.setChecked(isEntrepreneurChecked);
            boxSport.setChecked(isSportChecked);
            boxBusiness.setChecked(isBusinessChecked);
            boxArts.setChecked(isArtsChecked);
            boxTravel.setChecked(isTravelChecked);


        }
    }

    // -----------------
    // ERROR MESSAGE
    // -----------------

    @Override
    public void showNotificationEnabledMessage() {
        Snackbar snackbar = Snackbar.make(rootView, R.string.notification_enabled_message, Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    @Override
    public void showNotificationDisableMessage() {
        Snackbar snackbar = Snackbar.make(rootView, R.string.notification_disabled_message, Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    // --------------
    // NOTIFICATION MANAGEMENT
    // --------------

    @Override
    public void enableNotifications(String querySection, String queryTerm) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_QUERY_SECTIONS, querySection);
        bundle.putString(BUNDLE_KEY_QUERY_TERM, queryTerm);
        pendingIntent = PendingIntent.getBroadcast(getContext(), 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationIntent.putExtras(bundle);
        AlarmManager manager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,0, AlarmManager.INTERVAL_DAY, pendingIntent);



    }

    @Override
    public void disableNotification() {
        switchNotification.setChecked(false);
        AlarmManager manager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);

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
