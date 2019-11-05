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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner spinner;
    private ImageView image;
    public String nameTrans;
    public Log mmm;
    HashMap<String, ArrayList<Hub>> allHubs = new HashMap<>();
    private static final String TAG = "Neighborhoods Activity";
    FirebaseDatabase database;


    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        // building hashmap of hubs from the firebase
        readHubs();

        //MARQUEE...
        TextView txt = findViewById(R.id.text);
        txt.setSelected(true);

        //SPINNER...

        spinner = findViewById(R.id.spinner);
        image = findViewById(R.id.hubsmap);
        String[] neighbor = {"Select", "Ballards", "Capitol Hill", "Downtown/Central", "Fremont", "Green Lake", "Magnolia", "Northwest seattle", "Queen Ann", "South Seattle", "West Seattle"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, neighbor);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        image.setImageResource(R.drawable.mainmap);
                        nameTrans = "";
                        break;
                    case 1:
                        image.setImageResource(R.drawable.ballardmap);
                        nameTrans = "Ballard";
                        break;
                    case 2:
                        image.setImageResource(R.drawable.capitolhillmap);
                        nameTrans = "Capitol Hill";
                        break;
                    case 3:
                        image.setImageResource(R.drawable.downtowncentralmap);
                        nameTrans = "Downtown/Central";
                        break;
                    case 4:
                        image.setImageResource(R.drawable.fremontmap);
                        nameTrans = "Fremont";
                        break;
                    case 5:
                        image.setImageResource(R.drawable.greenlakemap);
                        nameTrans = "Green Lake";
                        break;
                    case 6:
                        image.setImageResource(R.drawable.magnoliamap);
                        nameTrans = "Magnolia";
                        break;
                    case 7:
                        image.setImageResource(R.drawable.northwestmap);
                        nameTrans = "Northwest Seattle";
                        break;
                    case 8:
                        image.setImageResource(R.drawable.queenannmap);
                        nameTrans = "Quee Ann";
                        break;
                    case 9:
                        image.setImageResource(R.drawable.southseattlemap);
                        nameTrans = "South Seattle";
                        break;
                    case 10:
                        image.setImageResource(R.drawable.westseattlemap);
                        nameTrans = "West Seattle";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button mButton = (Button) findViewById(R.id.buttonNeigh);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent trans = new Intent(MainActivity.this, SelectedNeighborhoods.class);
                trans.putExtra("transValue", nameTrans);

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

    public void readHubs() {
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
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
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        // setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
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
            Intent defaultNeighborhoods = new Intent(this, SelectedNeighborhoods.class);
            startActivity(defaultNeighborhoods);

        } else if (id == R.id.nav_resources) {

        } else if (id == R.id.nav_blog) {

        } else if (id == R.id.nav_contact) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
