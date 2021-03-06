package com.cap.seattleemergencyhubs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;



public class ResourseActivity extends AppCompatActivity {


    public String theUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resourse);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("RESOURCES");

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void buttonOnClick(View view) {

        switch(view.getId())
        {
            case R.id.urbSurvBtn:

                theUrl = "http://seattleemergencyhubs.org/resources/urban-survival-skills-fair/";
                uriGo(theUrl);
                break;

            case R.id.planningBtn:

                theUrl  = "http://seattleemergencyhubs.org/resources/planning-tips/";
                uriGo(theUrl);
                break;

            case R.id.createHubBtn:

                theUrl = "http://seattleemergencyhubs.org/resources/how-to-create-a-hub/";
                uriGo(theUrl);
                break;

            case R.id.firstAidBtn:

                theUrl  = "http://seattleemergencyhubs.org/resources/medical-first-aid/";
                uriGo(theUrl);
                break;

            case R.id.safetyBtn :

                theUrl = "http://seattleemergencyhubs.org/resources/safety-tips/";
                uriGo(theUrl);
                break;

            case R.id.trainningBtn:

                theUrl = " http://seattleemergencyhubs.org/resources/training-education/";
                uriGo(theUrl);
                break;
        }
    }

    private void uriGo(String theurl) {

        Uri urlstr = Uri.parse(theurl);
        Intent urlintent = new Intent();
        urlintent.setData(urlstr);
        urlintent.setAction(Intent.ACTION_VIEW);
        startActivity(urlintent);
    }

}
