package com.cap.seattleemergencyhubs;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectedNeighborhoods extends AppCompatActivity {

    private ArrayList<Hub> currentNeighborhoodHubs = new ArrayList<>();
    private static final String TAG = "Neighborhoods Activity";
    private String firstSelectedNeighbName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_neighborhoods);
        Log.i(TAG, "started onCreate");
        // extracting the neighborhood info of which the user selected in the main activity
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            currentNeighborhoodHubs = (ArrayList<Hub>) bundle.getSerializable("neighborhoodName");
            Log.i("ArrayList ", currentNeighborhoodHubs.toArray().toString() );
            if (currentNeighborhoodHubs != null) {
                firstSelectedNeighbName = currentNeighborhoodHubs.get(0).getNeighborhood().toUpperCase();
                Log.i(" *** Current hubs list ", firstSelectedNeighbName);
            }
        }

        ImageView imageView = findViewById(R.id.neighborhood_map);

        Button firstNeighborhood = (Button) findViewById(R.id.first_neighborhood);
        firstNeighborhood.setText(firstSelectedNeighbName);
        Button secondNeighborhood = (Button) findViewById(R.id.second_neighborhood);
        Button thirdNeighborhood = (Button) findViewById(R.id.third_neighborhood);
//        thirdNeighborhood.setVisibility(View.GONE);
//        secondNeighborhood.setVisibility(View.GONE);
        // add and change neighborhoods button
        FrameLayout editButton = (FrameLayout) findViewById(R.id.Add_edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectedNeighborhoods.this, MainActivity.class);
                //TODO
                //put the currently selected neigborhood into the intent
                // when moving to the previouse activity - keep it's name in the selection
                startActivity(intent);
            }
        });
        // second part - a list view of hubs
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Default Neighborhoods");

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        HubsListAdapter adapter = new HubsListAdapter(this, currentNeighborhoodHubs);

        final ListView listView = (ListView) findViewById(R.id.hubs_list);
        //TODO
        // handle if nothing is selected from the neighborhood

        listView.setAdapter(adapter);
        ColorDrawable separator = new ColorDrawable(this.getResources().getColor(R.color.lightGreen));
        listView.setDivider(separator);
        listView.setDividerHeight(10);

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