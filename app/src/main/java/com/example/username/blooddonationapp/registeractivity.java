package com.example.username.blooddonationapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mayank on 26/2/17.
 */

public class registeractivity extends AppCompatActivity {

    private ListView mMessageListView;
    private userdataAdapter userdataAdapter;

    private userdataAdapter muserdataAdapter;
    private ChildEventListener mChildEventListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference userdataDatabaseReference;
    private ListView mdataListView;
    private String b;
    private TextView textViewname;
    private TextView textViewgender;
    private TextView textViewaddress;
    private TextView textViewgroup;
    private TextView textViewphone;
    TextView wordView;
    TextView wordView1;
    TextView wordView2;
    TextView wordView3;
    TextView wordView4;
    TextView wordView5;
    private LinearLayout rootView;
    private ArrayList<String> data;

    int cntr = 1;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dataformat);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        userdataDatabaseReference = mFirebaseDatabase.getReference();


        textViewname = (TextView) findViewById(R.id.name);

        textViewgender = (TextView) findViewById(R.id.gender);

        textViewaddress = (TextView) findViewById(R.id.address);

        textViewphone = (TextView) findViewById(R.id.phone);

        textViewgroup = (TextView) findViewById(R.id.blood);

        data = new ArrayList<String>();

        // Get a reference to our posts
        //final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference ref = database.getReference("BloodDonor").child(ref.getKey());

// Attach a listener to read the data at our posts reference
       /* userdataDatabaseReference.child("BloodDonor").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userdata post = dataSnapshot.getValue(userdata.class);
                System.out.println("Post "+post.name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

  */

        if(x==0)
        {

            Toast.makeText(registeractivity.this, "LOADING ", Toast.LENGTH_LONG).show();
        }
       rootView = (LinearLayout) findViewById(R.id.rootView);


        userdataDatabaseReference.child("BloodDonor").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Toast.makeText(registeractivity.this, "LOADING ", Toast.LENGTH_SHORT).show();

               if(dataSnapshot == null)
               {
                   Toast.makeText(registeractivity.this, "Database Empty ", Toast.LENGTH_SHORT).show();
               }
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    String name = postSnapshot.getValue().toString();
                    //                        userdata Userdata = postSnapshot.getValue(userdata.class);

                    System.out.println("data :" + name);

                   // Toast.makeText(registeractivity.this, "LOADING ", Toast.LENGTH_LONG).show();

                    String[] parts = name.split(",");
                    String[] parts2 = parts[0].split("=");
                    String[] parts4 = parts[1].split("=");  // take parts4[1] for phone
                    String[] parts5 = parts[2].split("="); //take parts5[1] for group
                    String[] parts6 = parts[3].split("="); // take parts6[1] for address
                    String[] parts7 = parts[4].split("="); // take parts7[1] for name
                    StringBuilder sb = new StringBuilder(parts7[1]);
                    sb.deleteCharAt(sb.length() - 1);


                    // System.out.println("Parts"+ parts2[2] + " "+ parts4[1] +" "+ parts5[1] +  " " + parts6[1] + " "+ sb) ;
                    // create(sb) ;
                    //


                     wordView = new TextView(getApplicationContext());
                    wordView1 = new TextView(getApplicationContext());
                    wordView2 = new TextView(getApplicationContext());
                    wordView3 = new TextView(getApplicationContext());
                    wordView4 = new TextView(getApplicationContext());
                    wordView5 = new TextView(getApplicationContext());

                    rootView.addView(wordView);
                    rootView.addView(wordView1);
                    rootView.addView(wordView2);
                    rootView.addView(wordView3);
                    rootView.addView(wordView4);
                    rootView.addView(wordView5);
                         wordView.setText("NAME:                     " +sb) ;
                         wordView.setTextColor(Color.WHITE);
                         wordView.setTextSize(17);
                         wordView.setBackgroundColor(Color.RED);
                         wordView1.setText("GENDER:                "+parts2[1]) ;
                    wordView1.setTextColor(Color.WHITE);
                    wordView1.setTextSize(17);
                    wordView1.setBackgroundColor(Color.RED);
                         wordView2.setText("ADDRESS:              "+parts6[1]) ;
                    wordView2.setTextColor(Color.WHITE);
                    wordView2.setTextSize(17);
                    wordView2.setBackgroundColor(Color.RED);
                         wordView3.setText("PHONE:                  "+parts4[1]) ;
                    wordView3.setTextColor(Color.WHITE);
                    wordView3.setTextSize(17);
                    wordView3.setBackgroundColor(Color.RED);
                         wordView4.setText("BLOOD GROUP:    "+parts5[1]) ;
                    wordView4.setTextColor(Color.WHITE);
                    wordView4.setTextSize(17);
                    wordView4.setBackgroundColor(Color.RED);
                         wordView5.setText(" ") ;

                    x = 1;

                   // data.add(sb.toString());
                    //data.add(parts2[1]);

                       /* textViewname.setText(sb);
                        textViewgender.setText(parts2[1]);
                        textViewaddress.setText(parts6[1]);
                        textViewphone.setText(parts4[1]);
                        textViewgroup.setText(parts5[1]);
                  */
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


          }


}



/*

       ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

             for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    //Getting the data from snapshot
                    userdata Userdata = postSnapshot.getValue(userdata.class);
                     System.out.println("Userdata name"+ Userdata.getname()) ;
                    //Adding it to a string
                    String string = "Name: "+Userdata.getname()+"\nAddress: "+Userdata.getaddress()+"\n\n";

                    //Displaying it on textview
                    textViewPersons.setText(string);
               }

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        };


        userdataDatabaseReference.addValueEventListener(postListener);
*/







