package com.galou.mynews.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galou.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopFragment extends Fragment {


    public MostPopFragment() {}

    public static MostPopFragment newInstance(){
        return (new MostPopFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_most_pop, container, false);
    }

}
