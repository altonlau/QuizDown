package com.ag.quizdown.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ag.quizdown.R;
import com.ag.quizdown.activity.QuestionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class QuestionFragment extends Fragment {

    private static final String JSON_QUESTION = "question";
    private static final String JSON_ANSWER = "answer";

    private JSONArray mQuestionArray;

    private TextView mTvQuestion;

    private static Random random;

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

        mTvQuestion = (TextView) getView().findViewById(R.id.question);

        mQuestionArray = ((QuestionActivity) getActivity()).getQuestionArray();

        int randIndex = getRandomIndex(mQuestionArray.length());

        try {
            JSONObject jsonQuestionObj = mQuestionArray.getJSONObject(randIndex);
            String question = jsonQuestionObj.getString(JSON_QUESTION);
            String answer = jsonQuestionObj.getString(JSON_ANSWER);

            mTvQuestion.setText(question);
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static int getRandomIndex(int maxSize) {
        random = new Random();
        return random.nextInt(maxSize);
    }

}
