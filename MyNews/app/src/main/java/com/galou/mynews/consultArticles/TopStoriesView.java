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
public class TopStoriesView extends Fragment {


    public TopStoriesView() {
        // Required empty public constructor
    }

    public static TopStoriesView newInstance(){
        return (new TopStoriesView());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_stories, container, false);
    }

}
