package com.cap.seattleemergencyhubs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HubsListAdapter extends ArrayAdapter<Hub> {

    ArrayList<Hub> hubs = new ArrayList<>();

    public HubsListAdapter(Context context, ArrayList<Hub> hubs) {
        super(context, 0, hubs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hub_row_item_view, parent, false);
        }



        //ImageView imageView = findViewById(R.id.movieImage);
        //String url = "movies"
        Hub hub = getItem(position);
        String neighborhoodName = "";
        String neighborhoodLocation = "";
        if(hub != null) {
            neighborhoodName = hub.getNeighborhood();
            neighborhoodLocation = hub.getHub_location();
        }

        TextView hubName = (TextView) convertView.findViewById(R.id.hub_name);
        TextView hubAddress = (TextView) convertView.findViewById(R.id.hub_adress);
        hubName.setText(neighborhoodName);
        hubAddress.setText(neighborhoodLocation);

        return convertView;
    }

}