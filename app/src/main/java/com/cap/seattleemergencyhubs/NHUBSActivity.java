package com.cap.seattleemergencyhubs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.cap.seattleemergencyhubs.R;

public class NHUBSActivity extends AppCompatActivity {

    private static final String TAG = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhubs);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nameTrans = extras.getString("transValue");

            Log.wtf("myTag", "THIS LOG SHOWS VARIABLE AFTER GOING TO NHUBSACTIVITY" );
            Log.wtf("myTag", "666666666666666666666666" + nameTrans);

        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
