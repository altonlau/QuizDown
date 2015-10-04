package com.ag.quizdown.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ag.quizdown.R;
import com.ag.quizdown.activity.CongratsActivity;
import com.ag.quizdown.activity.QuestionActivity;
import com.ag.quizdown.sound.Sound;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class QuestionFragment extends Fragment implements View.OnClickListener {

    private static final String JSON_QUESTION = "question";
    private static final String JSON_ANSWER = "answer";

    private JSONArray mQuestionArray;

    private TextView mTvQuestion;
    private Button mAnswer1;
    private Button mAnswer2;
    private Button mAnswer3;
    private Button mAnswer4;

    private static Random random;

    private String mQuestion;
    private String mAnswer;

    private int numWrong = 0;

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
        mAnswer1 = (Button) getView().findViewById(R.id.answer1);
        mAnswer2 = (Button) getView().findViewById(R.id.answer2);
        mAnswer3 = (Button) getView().findViewById(R.id.answer3);
        mAnswer4 = (Button) getView().findViewById(R.id.answer4);

        mAnswer1.setOnClickListener(this);
        mAnswer2.setOnClickListener(this);
        mAnswer3.setOnClickListener(this);
        mAnswer4.setOnClickListener(this);

        mQuestionArray = ((QuestionActivity) getActivity()).getQuestionArray();

        int randIndex = getRandomIndex(mQuestionArray.length());

        try {
            JSONObject jsonQuestionObj = mQuestionArray.getJSONObject(randIndex);
            mQuestion = jsonQuestionObj.getString(JSON_QUESTION);
            mAnswer = jsonQuestionObj.getString(JSON_ANSWER);

            mTvQuestion.setText(mQuestion);

            int randomButtonIndex = getRandomIndex(4);

            switch (randomButtonIndex) {
                case 0:
                    mAnswer1.setText(mAnswer);
                    mAnswer2.setText("" + getRandomIndex(10000));
                    mAnswer3.setText("" + getRandomIndex(10000));
                    mAnswer4.setText("" + getRandomIndex(10000));
                    break;
                case 1:
                    mAnswer1.setText("" + getRandomIndex(10000));
                    mAnswer2.setText(mAnswer);
                    mAnswer3.setText("" + getRandomIndex(10000));
                    mAnswer4.setText("" + getRandomIndex(10000));
                    break;
                case 2:
                    mAnswer1.setText("" + getRandomIndex(10000));
                    mAnswer2.setText("" + getRandomIndex(10000));
                    mAnswer3.setText(mAnswer);
                    mAnswer4.setText("" + getRandomIndex(10000));
                    break;

                case 3:
                    mAnswer1.setText("" + getRandomIndex(10000));
                    mAnswer2.setText("" + getRandomIndex(10000));
                    mAnswer3.setText("" + getRandomIndex(10000));
                    mAnswer4.setText(mAnswer);
                    break;
            }

        }
        catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public static int getRandomIndex(int maxSize) {
        random = new Random();
        return random.nextInt(maxSize);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        if (button.getText().equals(mAnswer)) {
            Intent congratsIntent = new Intent(getActivity(), CongratsActivity.class);
            congratsIntent.putExtra("numWrong", numWrong);

            startActivity(congratsIntent);
        }
        else {
            numWrong++;
            Sound.screamSheep(getActivity());
        }
    }




}
