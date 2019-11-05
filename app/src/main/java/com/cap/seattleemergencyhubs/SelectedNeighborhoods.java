package com.cap.seattleemergencyhubs;
import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.protobuf.MapEntryLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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


    /*
    Firebase provides great support when comes to offline data.
    It automatically stores the data offline when there is no internet connection.
    When the device connects to internet, all the data will be pushed to realtime database.
    However enabling disk persistence stores the data offline even though app restarts.
    Disk persistence can be enabled by calling below one line code.
    Here is complete guide about firebase offline capabilities.

     FirebaseDatabase.getInstance().setPersistenceEnabled(true);

     */

    ArrayList<Hub> hubs = new ArrayList<>(); // empty at the time
    HashMap<String, ArrayList<Hub>> greedwoodHubs = new HashMap<>();
    // up to 3 hubs that the user choose
    ArrayList<Hub> choosenDefaults;
    private static final String TAG = "Neighborhoods Activity";
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_neighborhoods);
        Log.i(TAG, "started onCreate");


//        for(Hub hub: hubs){
//            Log.i("Greenwood ", hub.getName());
//        }
//        //Log.i("Greenwood ", hubs.get(0).toString());
//        Log.i("List size", "" + hubs.size());

        ImageView imageView = findViewById(R.id.neighborhood_map);

        Button firstNeighborhood = (Button) findViewById(R.id.first_neighborhood);
        Button secondNeighborhood = (Button) findViewById(R.id.second_neighborhood);
        Button thirdNeighborhood = (Button) findViewById(R.id.third_neighborhood);
        FrameLayout editButton = (FrameLayout) findViewById(R.id.Add_edit_button);
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

    @Override
    public void onStart(){
        super.onStart();
        //populateLists();

    }
}