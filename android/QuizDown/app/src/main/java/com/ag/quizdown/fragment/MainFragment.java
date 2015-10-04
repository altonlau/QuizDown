package com.ag.quizdown.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ag.quizdown.R;
import com.ag.quizdown.sound.Sound;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainFragment extends Fragment implements View.OnClickListener {

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

        mMathEasy.setOnClickListener(this);
        mMathMedium.setOnClickListener(this);
        mMathHard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        generateQuestionActivity();
    }

    public void generateQuestionActivity() {
        class GetQuestions extends AsyncTask<String, Void, String> {

            private static final String JSON_TAG = "data";
            private ProgressDialog mProgressDialog = new ProgressDialog(getActivity());

            protected void onPreExecute() {
                mProgressDialog.setMessage("Loading");
                mProgressDialog.show();

                // TODO: UNCOMMENT THE SCREAM AFTER DONE TESTING
                //Sound.screamSheep(getActivity());
            }

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://gtesting.comule.com/sqldata.php");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (inputStream != null) inputStream.close();
                    } catch (Exception squish) {
                    }
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                try {

                    this.mProgressDialog.dismiss();
                    if (result != null) {
                        JSONArray jsonArray = (new JSONObject(result)).getJSONArray(JSON_TAG);
                        System.out.println("asdasd");
                    }
                    else {
                        Toast.makeText(getActivity(), "No questions available :(",
                                Toast.LENGTH_LONG).show();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        GetQuestions g = new GetQuestions();
        g.execute();


    }
}
