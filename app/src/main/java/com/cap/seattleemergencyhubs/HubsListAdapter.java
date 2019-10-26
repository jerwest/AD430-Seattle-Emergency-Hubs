package com.cap.seattleemergencyhubs;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HubsListAdapter extends ArrayAdapter<Hub> {

    ArrayList<Hub> hubs = new ArrayList<>();

    public HubsListAdapter(Context context, ArrayList<Hub> hubs) {
        super(context, 0, hubs);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       Hub hub = getItem(position);
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.hub_row_item_view, parent, false);
       }
       // Lookup view for data population
       TextView hubName = (TextView) convertView.findViewById(R.id.patchName);
       TextView hubAdress = (TextView) convertView.findViewById(R.id.patchAdress);
       // Populate the data into the template view using the data object
        hubName.setText(hub.getHubName());
        hubAdress.setText(hub.getApproxAdress());
       // Return the completed view to render on screen
       return convertView;
   }




/*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_row_item, parent, false);
        }
        //ImageView imageView = findViewById(R.id.movieImage);
        //String url = "movies"

        String[] movie = getItem(position);
        TextView movieTitle = (TextView) convertView.findViewById(R.id.movieTitle);
        movieTitle.setText(movie[0]);

        TextView movieYear = (TextView) convertView.findViewById(R.id.movieYear);
        movieYear.setText(movie[1]);

        ImageView movieImage = convertView.findViewById(R.id.movieImage);
        String url = (movie[3]);

        Picasso.get().load(url).into(movieImage);

        return convertView;
    }

*/
}