package com.cap.seattleemergencyhubs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;

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

        Hub hub = getItem(position);
        String neighborhoodName = "";
        String hName = "";
        String hubLocation = "";
        String hubCaptainName = "";
        String hEmail = "";
        String hPhone = "";

        if(hub != null) {
            neighborhoodName = hub.getNeighborhood();
            hName = hub.getName();
            hubLocation = hub.getHub_location();
            hubCaptainName = hub.getCaptain();
            hEmail = hub.getEmail();
            hPhone = hub.getPhone();
        }
        TextView hubName = (TextView) convertView.findViewById(R.id.hub_name);
        TextView hubAddress = (TextView) convertView.findViewById(R.id.hub_adress);
        TextView hubCaptain = (TextView) convertView.findViewById(R.id.hub_captain);
        TextView hubEmail = (TextView) convertView.findViewById(R.id.hub_email);
        TextView hubPhone = (TextView) convertView.findViewById(R.id.hub_phone);

        TextView[] fields = {hubName, hubAddress,hubCaptain, hubEmail, hubPhone };
        hubName.setText(hName);
        hubAddress.setText(hubLocation);
        hubCaptain.setText(hubCaptainName);
        hubEmail.setText(hEmail);
        hubPhone.setText(hPhone);
        // hide the empty space in the list if the hub doesn't have some info
        for(TextView field: fields){
            if(field.length() <1){
                field.setVisibility(View.GONE);
            }
        }



        return convertView;
    }

}