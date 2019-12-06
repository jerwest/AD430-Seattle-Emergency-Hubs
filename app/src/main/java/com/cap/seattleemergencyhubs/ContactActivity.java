package com.cap.seattleemergencyhubs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



    public class ContactActivity extends AppCompatActivity {


        public String theUrl;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_contact);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }

        public void buttonOnClick(View view) {

            switch(view.getId())
            {
                case R.id.contactBtn:

                    theUrl = "http://seattleemergencyhubs.org/contact-us/";
                    uriGo(theUrl);
                    break;


            }
        }

        private void uriGo(String theurl) {

            Uri urlstr = Uri.parse(theurl);
            Intent urlintent = new Intent();
            urlintent.setData(urlstr);
            urlintent.setAction(Intent.ACTION_VIEW);
            startActivity(urlintent);
        }


    }

