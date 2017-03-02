package com.example.username.blooddonationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.username.blooddonationapp.myApplication.* ;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayank on 25/2/17.
 */

public class nameActivity extends AppCompatActivity {
    private EditText userdata1 ,userdata2, userdata3, userdata4 , userdata5, userdata6;
    private FirebaseDatabase mFirebaseDatabase ;
    private DatabaseReference userdataDatabaseReference ;
    private userdataAdapter muserdataAdapter ;
    private ChildEventListener mChildEventListener ;
    private ListView mdataListView ;

    private FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener ;
    private Button mSendButton ;
    private TextView textViewPersons ;
    public static final int RC_SIGN_IN = 1 ;
    public int a = 0 ;
    private TextView textViewname ;
    private TextView textViewgender ;
    private TextView textViewaddress ;
    private TextView textViewgroup ;
    private TextView textViewphone ;
    private CheckBox terms;

    //java mApp = ((java)getApplicationContext());
    //final int globalVarValue = mApp.mGlobalVarValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);


        mFirebaseDatabase =  FirebaseDatabase.getInstance() ;
        userdataDatabaseReference = mFirebaseDatabase.getReference().child("BloodDonor") ;


        //userdata6 = (EditText) findViewById(R.id.name) ;
        userdata1 = (EditText) findViewById(R.id.name) ;
        userdata2 = (EditText) findViewById(R.id.gender) ;
        userdata3 = (EditText) findViewById(R.id.address) ;
        userdata4 = (EditText) findViewById(R.id.phone) ;
        userdata5 = (EditText) findViewById(R.id.blood) ;
        textViewname = (TextView) findViewById(R.id.name);
        terms = (CheckBox)findViewById(R.id.terms);
        terms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(nameActivity.this);
                    LayoutInflater inflater = nameActivity.this.getLayoutInflater();
                    View v = inflater.inflate(R.layout.termlayout, null);
                    final TextView send = (TextView) v.findViewById(R.id.textView2);

                    builder.setView(v);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

                    java mApp = ((java)getApplicationContext());



        mApp.mGlobalVarValue++ ;
        final int globalVarValue = mApp.mGlobalVarValue;

        System.out.println("Global Variable"+globalVarValue) ;

        mSendButton = (Button)findViewById(R.id.register) ;






        mSendButton.setOnClickListener(new View.OnClickListener() {

                                           @Override
                                           public void onClick(View view) {
                                               userdata Userdata = new userdata(userdata1.getText().toString(), userdata2.getText().toString(), userdata3.getText().toString(), userdata4.getText().toString(), userdata5.getText().toString());


                                               if (userdata1.getText().toString().isEmpty())
                                               {
                                                   //toast for all entry are not filled
                                                   Toast.makeText(nameActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                                               }
                                            else if (userdata2.getText().toString().isEmpty())
                                               {
                                                   //all entries are not filled
                                                   Toast.makeText(nameActivity.this, "Please enter gender", Toast.LENGTH_SHORT).show();
                                               }

                                               else if (userdata3.getText().toString().isEmpty())
                                               {
                                                   //all entries are not filled
                                                   Toast.makeText(nameActivity.this, "Please enter address", Toast.LENGTH_SHORT).show();
                                               }
                                               else if (userdata4.getText().toString().isEmpty())
                                               {
                                                   //all entries are not filled
                                                   Toast.makeText(nameActivity.this, "Please enter phone", Toast.LENGTH_SHORT).show();
                                               }
                                               else if (userdata5.getText().toString().isEmpty())
                                               {
                                                   //all entries are not filled
                                                   Toast.makeText(nameActivity.this, "Please enter blood group", Toast.LENGTH_SHORT).show();
                                               }


                                               else {
                                                   int x = 1;

                                                   String s = userdata3.getText().toString();
                                                   char a[] = s.toCharArray();
                                                   for (int i = 0; i < a.length; i++) {
                                                       if (a[i] == ',')
                                                           x = 0;


                                                   }
                                                   if (x == 1) {

                                                       userdataDatabaseReference.push().setValue(Userdata);
                                                       userdata1.setText("");
                                                       userdata2.setText("");
                                                       userdata3.setText("");
                                                       userdata4.setText("");
                                                       userdata5.setText("");


                                                       Intent intent = new Intent(nameActivity.this, MainActivity.class);
                                                       startActivity(intent);
                                                       Toast.makeText(nameActivity.this, "You're now Successfully Registered ", Toast.LENGTH_SHORT).show();

                                                   }
                                                   else
                                                       Toast.makeText(nameActivity.this, "No comma should be used ", Toast.LENGTH_SHORT).show();

                                               }
                                           }


        } );

        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser() ;
                if(user != null) {
                    Toast.makeText(nameActivity.this,"You're now Signed in. Welcome to BLOOD DONATION APP! ", Toast.LENGTH_SHORT).show() ;
                }
                else
                {
                    startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setIsSmartLockEnabled(false).setProviders(AuthUI.EMAIL_PROVIDER,AuthUI.GOOGLE_PROVIDER).build(),RC_SIGN_IN);
                }

            }

        } ;
    }
    @Override
    protected void onPause() {
        super.onPause() ;
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        //AuthUI.getInstance().signOut(this);
       // mApp.mGlobalVarValue = globalVarValue;

    }
    @Override
    protected void onResume() {
        super.onResume() ;
        mFirebaseAuth.addAuthStateListener(mAuthStateListener) ;

    }




    }





