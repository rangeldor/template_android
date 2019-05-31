package com.codeepisodes.myapplication.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeepisodes.myapplication.Adapter.FeedAdapter;
import com.codeepisodes.myapplication.R;
import com.pkmmte.pkrss.Article;
import com.pkmmte.pkrss.Callback;
import com.pkmmte.pkrss.PkRSS;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment implements Callback {

    private RecyclerView recyclerView;
    private FeedAdapter adapter;
    private List<Article> articles = new ArrayList<> ();

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {

        View view = inflater.inflate ( R.layout.fragment_feed , container , false );

        recyclerView = view.findViewById(R.id.feed_recyclerview);
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity () ) );

        adapter = new FeedAdapter ( articles );
        recyclerView.setAdapter(adapter);

        PkRSS.with ( getActivity () ).load ( "http://www.androidpro.com.br/feed/" ).skipCache ().callback ( this ).async ();

        return view;
    }

    @Override
    public void onPreload() {

    }

    @Override
    public void onLoaded(List<Article> newArticles) {
        articles.clear();
        articles.addAll(newArticles);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadFailed() {

    }
}
