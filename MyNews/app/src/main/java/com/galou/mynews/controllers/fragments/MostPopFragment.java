package com.galou.mynews.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galou.mynews.R;
import com.galou.mynews.models.Article;
import com.galou.mynews.models.Section;
import com.galou.mynews.utils.ApiStreams;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

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
        View view = inflater.inflate(R.layout.fragment_most_pop, container, false);



        return view;
    }


}
