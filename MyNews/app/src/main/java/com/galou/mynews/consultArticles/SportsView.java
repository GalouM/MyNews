package com.galou.mynews.consultArticles;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galou.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportsView extends Fragment {


    public SportsView() {}

    public static SportsView newInstance(){
        return (new SportsView());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sport, container, false);
    }

}
