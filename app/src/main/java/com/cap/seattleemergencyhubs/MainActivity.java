package com.cap.seattleemergencyhubs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Neighborhoods Activity";
    public String nameTrans;
    public Log mmm;
    int selectedPosition;
    private Spinner spinner;
    private ImageView image;
    private HashMap<String, ArrayList<Hub>> allHubs;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String[] neighborAsync;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.cap.seattleemergencyhubs";
    private SharedPreferences lastSelection;
    private String SELECTED_NAME = "selected_neighborhood";
    private boolean initialDisplay = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        spinner = findViewById(R.id.spinner);
        image = findViewById(R.id.hubsmap);
        TextView txt = findViewById(R.id.text);
        setSupportActionBar(toolbar);
        allHubs = new HashMap<>();

        retrieveHubsFromFirebase();

        txt.setSelected(true);
        //SPINNER...
        image.setImageResource(R.drawable.mainmap);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onButtonClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   SelectedNeighborhoods.class);
        startActivity(myIntent);
        Intent intent = new Intent(this, SelectedNeighborhoods.class);

        // saving the spinner selection in the shared prefferences, unless it is the initial selection
        selectedPosition = spinner.getSelectedItemPosition();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        Toast.makeText(MainActivity.this, neighborAsync[selectedPosition], Toast.LENGTH_LONG).show();
        preferencesEditor.putInt("spinnerSelection", selectedPosition);
        preferencesEditor.apply();

        Bundle bundle = new Bundle();
        bundle.putSerializable("neighborhoodName", allHubs.get(nameTrans));

        logBundleValues();
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public Set<String> retrieveHubsFromFirebase() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        // RETRIEVE DATA FOR ALL THE HUBS FROM THE FIREBASE
        myRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                        // make sure that clicked item is saved, not the first position when spinner is shown
                        if (!initialDisplay) {
                            spinner.setSelection(selectedPosition);
                        }
                        buildAllHubs(dataSnapshot);
                        logTheNeighborhoods();
                        // update the list of spinner
                        createSpinnerListNamesFromNeighHubsMap(allHubs);
                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, neighborAsync);
                        setUpSpinner(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                nameTrans = spinner.getSelectedItem().toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                return;
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_neighborhood) {

            Intent intent = new Intent(this, SelectedNeighborhoods.class);

            // saving the spinner selection in the shared prefferences, unless it is the initial selection
            selectedPosition = spinner.getSelectedItemPosition();
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            Toast.makeText(MainActivity.this, neighborAsync[selectedPosition], Toast.LENGTH_LONG).show();
            preferencesEditor.putInt("spinnerSelection", selectedPosition);
            preferencesEditor.apply();

            Bundle bundle = new Bundle();
            bundle.putSerializable("neighborhoodName", allHubs.get(nameTrans));

            logBundleValues();
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

    // ******************* HELPER METHODS **************************

    private void buildAllHubs(@NotNull DataSnapshot dataSnapshot) {
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
    }
    //TODO
    // Replace this printout with unit testing

    private void logTheNeighborhoods() {
        for (Map.Entry<String, ArrayList<Hub>> entry : allHubs.entrySet()) {
            for (Hub hub : entry.getValue()) {
                Log.i(" *** Neighborhood " + hub.getNeighborhood(), "~~~ " + hub.getName());
            }
        }
        Log.i("List size", "" + allHubs.keySet().size());
    }

    private void createSpinnerListNamesFromNeighHubsMap(HashMap<String, ArrayList<Hub>> map) {
        int i = 0;
        neighborAsync = new String[map.keySet().size()];
        for (String name : map.keySet()) {
            neighborAsync[i++] = name;
        }
    }

    private void setUpSpinner(ArrayAdapter<String> adapter) {
        spinner.setAdapter(adapter);
        selectedPosition = mPreferences.getInt("spinnerSelection", 0);
        spinner.setSelection(selectedPosition);
    }

    //TODO
    // Replace this printout with unit testing
    private void logBundleValues() {
        Log.i("** name Trans ", nameTrans);
        ArrayList<Hub> sampleHubs = allHubs.get(nameTrans);
        for (int i = 0; i < sampleHubs.size(); i++) {
            Log.i(" %%% CH test hubs ", sampleHubs.get(i).getName());
        }
    }
}
