package com.ag.quizdown.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ag.quizdown.R;
import com.ag.quizdown.Utils;
import com.ag.quizdown.activity.QuestionActivity;

import org.json.JSONArray;

import java.util.Random;

public class QuestionFragment extends Fragment {

    private static final String JSON_QUESTION = "question";
    private static final String JSON_ANSWER = "answer";

    private JSONArray mQuestionArray;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    public void onStart() {
        super.onStart();

        mQuestionArray = ((QuestionActivity) getActivity()).getQuestionArray();

        int randNum = Utils.getRandomInt(mQuestionArray.length());



    }


}
