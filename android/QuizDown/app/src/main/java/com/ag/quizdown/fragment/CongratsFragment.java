package com.ag.quizdown.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ag.quizdown.R;
import com.ag.quizdown.activity.CongratsActivity;
import com.ag.quizdown.activity.MainActivity;
import com.ag.quizdown.sound.Sound;

public class CongratsFragment extends Fragment implements View.OnClickListener {

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

        for (int i = 1; i <= ((CongratsActivity)getActivity()).getNumWrong(); i++) {
            Sound.screamSheep(getActivity());
        }
    }

    public void onClick(View view) {
        Sound.screamSheep(getActivity());
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

}
