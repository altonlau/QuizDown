package com.ag.quizdown.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ag.quizdown.R;

public class MainFragment extends Fragment {

    private Button mMathEasy;
    private Button mMathMedium;
    private Button mMathHard;
    private Button mClickMe;

    public MainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        mMathEasy = (Button) getView().findViewById(R.id.btn_math_easy);
        mMathMedium = (Button) getView().findViewById(R.id.btn_math_medium);
        mMathHard = (Button) getView().findViewById(R.id.btn_math_hard);
        mClickMe = (Button) getView().findViewById(R.id.btn_click_me);

        mMathEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}
