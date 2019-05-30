package com.codeepisodes.myapplication.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeepisodes.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    private RecyclerView recyclerView;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {

        View view = inflater.inflate ( R.layout.fragment_feed , container , false );

        recyclerView = view.findViewById(R.id.recyclerview);
       // recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity () ) );

        return view;
    }

}
