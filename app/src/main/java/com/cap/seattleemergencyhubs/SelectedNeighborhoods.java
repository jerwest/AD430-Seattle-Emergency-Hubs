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
import java.util.List;

public class SelectedNeighborhoods extends AppCompatActivity {

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

        final DatabaseReference myRef = database.getReference();
        Log.i("Reference ", myRef.toString());
        Log.i(TAG, "started onCreate");





        myRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                        // Get user value
                        for (DataSnapshot hoodSnapShot : dataSnapshot.getChildren()) {
                            //
                        //for(DataSnapshot hoodSnapShot: dataSnapshot.getRef())
                         //  Log.i("DATA SNAPSHOT ", dataSnapshot.getValue().toString());
                           Hub hub = hoodSnapShot.getValue(Hub.class);

                           if(hub != null) {
                              // if(hub.getNeighborhood().equals("Greenwood")) {
                                   // works
//                               Log.i(" Captain ", hub.getCaptain());
//                               Log.i("Name ", hub.getName());
//                               Log.i("Hub Description ", hub.getDescription());
//                               Log.i("HUB email ", hub.getEmail());
//                               Log.i("HUB type ", hub.getHub_type());
//                               Log.i("HUB phone ", hub.getPhone());
//                               Log.i("HUB state ", hub.getState());
//                               Log.i("HUB city ", hub.getCity() + "\n");
                                   hubs.add(hub);
                               Log.i("List size", "" + hubs.size());
                                   //TODO
                                   // write few unit tests to test if all the hubs get in the list
                              // }
                           }
                    }
                        finish();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                       // setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });



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