package com.ag.quizdown.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ag.quizdown.R;

public class CongratsFragment extends Fragment {


    public CongratsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_congrats, container, false);
    }

    public void onStart() {
        super.onStart();


    }


}
