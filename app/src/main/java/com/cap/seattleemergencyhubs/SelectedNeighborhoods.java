package com.cap.seattleemergencyhubs;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SelectedNeighborhoods extends AppCompatActivity {

    private ArrayList<Hub> currentNeighborhoodHubs = new ArrayList<>(); // empty at the time
    private static final String TAG = "Neighborhoods Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_neighborhoods);
        Log.i(TAG, "started onCreate");
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            currentNeighborhoodHubs = (ArrayList<Hub>) bundle.getSerializable("neighborhoodName");
            if (currentNeighborhoodHubs != null) {
                Log.i(" *** Current hubs list ", currentNeighborhoodHubs.get(0).getName());
            }
        }

        ImageView imageView = findViewById(R.id.neighborhood_map);


        Button firstNeighborhood = (Button) findViewById(R.id.first_neighborhood);
        Button secondNeighborhood = (Button) findViewById(R.id.second_neighborhood);
        Button thirdNeighborhood = (Button) findViewById(R.id.third_neighborhood);
        FrameLayout editButton = (FrameLayout) findViewById(R.id.Add_edit_button);



        // second part - a list view of hubs

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Default Neighborhoods");

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        HubsListAdapter adapter = new HubsListAdapter(this, currentNeighborhoodHubs);
        final ListView listView = (ListView) findViewById(R.id.hubs_list);
        listView.setAdapter(adapter);




    }




    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}