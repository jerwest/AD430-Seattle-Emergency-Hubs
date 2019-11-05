package com.cap.seattleemergencyhubs;
import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectedNeighborhoods extends AppCompatActivity {

    //TODO
    // the below firebase data retriaval needs to be moved to the main activity as it needs to retrieve all the hubs
    // when the user is on a main page after downloading
    // in this activity - just build a list of hubs for the selected neighborhoods
    // the main activity will have a HashMap < String neighborhood, ArrayList<Hub> someHubs> .
    //TODO
    // When the user selectes the neighborhood, the intent when being unpacked, the name of neighborhood in the
    // intent has to select hub list value from the map of all neighborhoods.
    // and only work with them in the SelectedNeighborhood Activity.

    ArrayList<Hub> hubs = new ArrayList<>(); // empty at the time
    ArrayList<Hub> choosenDefaults;
    private static final String TAG = "Neighborhoods Activity";
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_neighborhoods);
        Log.i(TAG, "started onCreate");
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        //pulling elements values from key on Bundle
        // get the passed from main activity selected neighborhood name
        String neighborhoodName = "";

        if(bundle != null) {
            neighborhoodName = bundle.getString("transVal");
            if(neighborhoodName!=null) {
                Log.i(" *** Intent trans hood ", neighborhoodName);
            }
        }

        ImageView imageView = findViewById(R.id.neighborhood_map);

        Button firstNeighborhood = (Button) findViewById(R.id.first_neighborhood);
        Button secondNeighborhood = (Button) findViewById(R.id.second_neighborhood);
        Button thirdNeighborhood = (Button) findViewById(R.id.third_neighborhood);
        FrameLayout editButton = (FrameLayout) findViewById(R.id.Add_edit_button);
        // second part - a list view of hubs
        HubsListAdapter adapter = new HubsListAdapter(this, hubs);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Default Neighborhoods");

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}