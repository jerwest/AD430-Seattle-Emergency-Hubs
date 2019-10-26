package com.cap.seattleemergencyhubs;
import android.support.v7.app.ActionBar;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectedNeighborhoods extends AppCompatActivity {

    ArrayList<Hub> hubs = new ArrayList<>(); // empty at the time

    private static final String TAG = "Neighborhoods Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // first part - map and neightborhood names
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_neighborhood_activity);

        Log.i(TAG, "started onCreate");

        ImageView imageView = findViewById(R.id.neighborhood_map);

        Button firstNeighborhood = (Button) findViewById(R.id.first_neighborhood);
        Button secondNeighborhood = (Button) findViewById(R.id.second_neighborhood);
        Button thirdNeighborhood = (Button) findViewById(R.id.third_neighborhood);

        FrameLayout editButton = (FrameLayout) findViewById(R.id.Add_edit_button);

        ImageView map = findViewById(R.id.neighborhood_map);

        // second part - a list view of hubs
        HubsListAdapter adapter = new HubsListAdapter(this, hubs);

        /*
        final ListView listView = (ListView) findViewById(R.id.movielist);
        listView.setAdapter(adapter);
*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Default Neighborhoods");

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}