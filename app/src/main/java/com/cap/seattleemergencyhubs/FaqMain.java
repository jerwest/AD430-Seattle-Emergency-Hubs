package com.cap.seattleemergencyhubs;

import android.os.Bundle;
import android.widget.ExpandableListView;
import androidx.appcompat.app.AppCompatActivity;

public class FaqMain extends AppCompatActivity {


    ExpandableListView expandableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.faqmain);

        expandableTextView=findViewById(R.id.eTV);
        ExpandableTextViewAdapter adapter= new ExpandableTextViewAdapter(FaqMain.this);
        expandableTextView.setAdapter(adapter);

    }




}
