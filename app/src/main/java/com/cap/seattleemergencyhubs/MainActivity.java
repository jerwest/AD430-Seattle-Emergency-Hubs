package com.cap.seattleemergencyhubs;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.protobuf.MapEntryLite;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner spinner;
    private ImageView image;
    public String nameTrans;
    public Log mmm;
    private HashMap<String, ArrayList<Hub>> allHubs;
    private static final String TAG = "Neighborhoods Activity";
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    String[] neighborAsync;

    /*
    Firebase provides great support when comes to offline data.
    It automatically stores the data offline when there is no internet connection.
    When the device connects to internet, all the data will be pushed to realtime database.
    However enabling disk persistence stores the data offline even though app restarts.
    Disk persistence can be enabled by calling below one line code.
    Here is complete guide about firebase offline capabilities.

     FirebaseDatabase.getInstance().setPersistenceEnabled(true);

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        allHubs = new HashMap<>();

        // building hashmap of hubs from the firebase
        Log.i("****** AllHubs", allHubs.toString());
        readHubs();
        Log.i("****** AllHubs", allHubs.toString());
        //MARQUEE...
        TextView txt = findViewById(R.id.text);
        txt.setSelected(true);
        //SPINNER...
        spinner = findViewById(R.id.spinner);
        image = findViewById(R.id.hubsmap);
        String[] neighbor = {"Select", "Ballard", "Capitol Hill", "Downtown/Central", "Fremont", "Green Lake", "Magnolia", "Northwest seattle", "Queen Ann", "South Seattle", "West Seattle", "Crown Hill"};

        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, neighbor);
        Button mButton = (Button) findViewById(R.id.buttonNeigh);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent trans = new Intent(MainActivity.this, SelectedNeighborhoods.class);
                trans.putExtra("transValue", nameTrans);
                startActivity(trans);

                Log.wtf("myTag", "THIS LOG SHOWS VARIABLE BEFORE GOING TO NHUBSACTIVITY");
                Log.wtf("myTag", "888888888888888888888888888888" + nameTrans);

                startActivity(trans);

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Emergenc Hubs Mail", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public Set<String> readHubs() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        // RETRIEVE DATA FOR ALL THE HUBS FROM THE FIREBASE
        myRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> hubIterator = dataSnapshot.getChildren().iterator();
                        allHubs.clear();

                        while (hubIterator.hasNext()) {
                            DataSnapshot hoodSnapShot = hubIterator.next();
                            Hub hub = hoodSnapShot.getValue(Hub.class);
                            if (hub != null) {
                                //TODO
                                // write few unit tests to test if all the hubs get in the list
                                if (!allHubs.containsKey(hub.getNeighborhood())) {
                                    ArrayList<Hub> hubsInThisNeighborhood = new ArrayList<>();
                                    hubsInThisNeighborhood.add(hub);
                                    allHubs.put(hub.getNeighborhood(), hubsInThisNeighborhood);
                                } else {
                                    if (allHubs.get(hub.getNeighborhood()) != null) {
                                        allHubs.get(hub.getNeighborhood()).add(hub);
                                    }
                                }
                            }
                        }
                        // TESTING MAP CONTENTS - NEEDS TO BE SWITCHED TO THE UNIT TEST
                        for (Map.Entry<String, ArrayList<Hub>> entry : allHubs.entrySet()) {
                            for (Hub hub : entry.getValue()) {
                                Log.i(" *** Neighborhood " + hub.getNeighborhood(), "~~~ " + hub.getName());
                            }
                        }
                        Log.i("List size", "" + allHubs.keySet().size());
                        // finish();
                        // update the list of spinner
                        int i = 0;
                        neighborAsync = new String[allHubs.keySet().size()];
                        for (String name : allHubs.keySet()) {
                            neighborAsync[i++] = name;
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, neighborAsync);


                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                nameTrans = spinner.getSelectedItem().toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        // setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
        Log.i("****** AllHubs", allHubs.toString());
        return allHubs.keySet();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_neighborhood) {
            Log.i("((((((())))))) Keyset", "" + allHubs.keySet());

            Intent intent = new Intent(this, SelectedNeighborhoods.class);
            //intent.putExtra("transVal", nameTrans);
            Bundle bundle = new Bundle();
            bundle.putSerializable("neighborhoodName", allHubs.get(nameTrans));

            Log.i("** name Trans ", nameTrans);
            ArrayList<Hub> sampleHubs = allHubs.get(nameTrans);
            for (int i = 0; i < sampleHubs.size(); i++) {
                Log.i(" %%% CH test hubs ", sampleHubs.get(i).getName());
            }

            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.nav_resources) {
            Intent intent = new Intent(MainActivity.this, ResourseActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_blog) {

        } else if (id == R.id.nav_contact) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Main activity", "started");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Main activity", "paused");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Main activity", "resumed");
    }
}
