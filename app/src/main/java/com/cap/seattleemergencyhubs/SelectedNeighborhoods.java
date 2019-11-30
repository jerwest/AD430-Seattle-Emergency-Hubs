package com.cap.seattleemergencyhubs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

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
            Log.i("ArrayList ", currentNeighborhoodHubs.toArray().toString());
            if (currentNeighborhoodHubs != null) {
                firstSelectedNeighbName = currentNeighborhoodHubs.get(0).getNeighborhood().toUpperCase();
                Log.i(" *** Current hubs list ", firstSelectedNeighbName);
            }
        }

        // ------- map image-------
        //TODO
        // Set up cashing and unable offline view
        final ImageView imageView = findViewById(R.id.neighborhood_map);
        // get a imageUrl from Firebase storage or Google Drive


        StorageReference storageRef = FirebaseStorage.getInstance()
                .getReference().child("fremontmap.JPG");

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //TODO
                //create a static class
                Picasso.get().load(uri).//networkPolicy(NetworkPolicy.OFFLINE).
                        into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //TODO
                Toast.makeText(SelectedNeighborhoods.this, "Didn't load URL", Toast.LENGTH_LONG).show();
            }
        });

        Button firstNeighborhood = (Button) findViewById(R.id.first_neighborhood);

        firstNeighborhood.setText(firstSelectedNeighbName);

        Button secondNeighborhood = (Button) findViewById(R.id.second_neighborhood);
        secondNeighborhood.setVisibility(View.GONE);
        Button thirdNeighborhood = (Button) findViewById(R.id.third_neighborhood);
        thirdNeighborhood.setVisibility(View.GONE);

        // add and change neighborhoods button
        FrameLayout editButton = (FrameLayout) findViewById(R.id.Add_edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectedNeighborhoods.this, MainActivity.class);

                startActivity(intent);
            }
        });
        // second part - a list view of hubs
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MY DEFAULT NEIGHBORHOODS");

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


    //-----------------Checking connection-------------------------

//    public boolean checkNetworkConnections() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//
//        if (networkInfo != null && networkInfo.isConnected()) {
//
//            //different types of connection
//
//            WIFIconnected = networkInfo.getType() == connectivityManager.TYPE_WIFI;
//            mobileConnected = networkInfo.getType() == connectivityManager.TYPE_MOBILE;
//            if (WIFIconnected) {
//                Log.i("WIFI connected", "successfully");
//                return true;
//            } else if (mobileConnected) {
//                Log.i("Mobile connected ", "successfuly");
//                return true;
//            }
//        } else {
//            Log.i("Connection status ", "No connection");
//            return false;
//        }
//        return false;
//    }
//


}