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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.view.LayoutInflater;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;


import java.util.ArrayList;

public class SelectedNeighborhoods extends AppCompatActivity {



    private ArrayList<Hub> currentNeighborhoodHubs = new ArrayList<>();
    private static final String TAG = "Neighborhoods Activity";
    private String firstSelectedNeighbName = currentNeighborhoodHubs.get(0).getNeighborhood().toUpperCase();
    Button popUpMapLegendButton, closePopupBtn;
    PopupWindow popupWindow;
    ScrollView linearLayout1;

    ListView list;
    String[] itemname ={
            "Communication Hub",
            "Hub",
            "Block Watch",
            "SNAP",
            "CERT",
            "MYN"
    };

    Integer[] imgid={
            R.drawable.emer_hub_icon,
            R.drawable.non_emer_icon,
            R.drawable.block_watch_icon,
            R.drawable.snap_icon,
            R.drawable.cert_icon,
            R.drawable.myn_icon
    };

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


        CustomListAdapter adapter2=new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter2);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });


//        popUpMapLegendButton = (Button) findViewById(R.id.popUpMapLegendButton);
//        linearLayout1 = (ScrollView) findViewById(R.id.ScrollView01);
//
//        popUpMapLegendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //instantiate the popup.xml layout file
//                LayoutInflater layoutInflater = (LayoutInflater) SelectedNeighborhoods.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.map_legend,null);
//
//                closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);
//
//                //instantiate popup window
//                popupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//
//                //display the popup window
//                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
//
//                //close the popup window on button click
//                closePopupBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        popupWindow.dismiss();
//                    }
//                });
//
//            }
//        });


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