package com.codeepisodes.myapplication.Fragment;

import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeepisodes.myapplication.R;

public class Menu1Fragment extends Fragment {

    BottomAppBar bottomAppBar;
    MaterialButton materialButton;

    public Menu1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {

        View view = inflater.inflate ( R.layout.fragment_menu1 , container , false );

        bottomAppBar = view.findViewById(R.id.bottom_appbar);
        materialButton = view.findViewById(R.id.materialButton);

        bottomAppBar.replaceMenu(R.menu.my_menu);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER)
                    bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                else {
                    bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
