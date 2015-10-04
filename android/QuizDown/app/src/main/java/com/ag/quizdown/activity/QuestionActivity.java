package com.ag.quizdown.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ag.quizdown.R;
import com.ag.quizdown.fragment.QuestionFragment;
import com.ag.quizdown.sound.Sound;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuestionActivity extends AppCompatActivity {

    private static final String JSON_TAG = "data";

    private QuestionFragment mQuestionFragment;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras() != null) {
            Bundle intentExtras = getIntent().getExtras();
            try {
                // Used to the json array from the activity
                jsonArray = (new JSONObject(intentExtras.getString("json"))).getJSONArray(JSON_TAG);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        getSupportActionBar().setTitle("Question #");

        mQuestionFragment = new QuestionFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(android.R.id.content, mQuestionFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_mute) {
            Sound.screamSheep(this);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Sound.screamSheep(this);
        moveTaskToBack(true);
    }

    public JSONArray getQuestionArray() {
        return jsonArray;
    }

}
