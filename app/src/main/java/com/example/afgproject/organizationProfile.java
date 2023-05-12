package com.example.afgproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class organizationProfile extends AppCompatActivity {
    //fb
    Button saveButton;
    String orgName;
    String orgEmail;
    String headEmail;
    int orgPhone;
    int headPhone;
    int orgZip;

    // /fb

    //nav
    DrawerLayout profileDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    // /nav

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_organization_profile);

        // pulling organization's info from firebase needs a set id, so can be future extension.
        /**
         FirebaseFirestore db = FirebaseFirestore.getInstance();
         String id = db.collection("organizations").document().getId();

         DocumentReference thisOrg = db.collection("organizations").document(id);
         thisOrg.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
        @Override public void onSuccess(DocumentSnapshot documentSnapshot) {
        Organization orgWithInfo = documentSnapshot.toObject(Organization.class);

        EditText orgEmailInfo = findViewById(R.id.organizationEmailInput);
        EditText headEmailInfo = findViewById(R.id.headEmailInput);
        EditText orgPhoneInfo = findViewById(R.id.organizationPhoneInput);
        EditText headPhoneInfo = findViewById(R.id.headPhoneInput);
        EditText orgZipInfo = findViewById(R.id.organizationZipCodeInput);

        orgEmailInfo.setText(orgWithInfo.getOrgEmail());


        }
        });

         // /pulling organization's info from firebase needs a set id, so can be future extension.


         SharedPreferences sp = organizationProfile.this.getPreferences(Context.MODE_PRIVATE);

         EditText updatedOrgName = (EditText) findViewById(R.id.organizationName);
         */
//update what the edit text says using the stuff in shared prefs

//navigation menu
        setUpToolbar();
        navigationView = findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:

                    Intent homeIntent = new Intent(organizationProfile.this, MainActivity.class); //changed both o.c.e.a's from main activity
                    startActivity(homeIntent);
                    break;

                case R.id.nav_profileDrawer:

                    Intent profileIntent = new Intent(organizationProfile.this, organizationProfile.class); //changed both o.c.e.a's from main activity
                    startActivity(profileIntent);
                    break;


                case R.id.nav_eventDrawer:

                    Intent eventIntent = new Intent(organizationProfile.this, organizationCreateEventActivity.class); //changed both o.c.e.a's from main activity
                    startActivity(eventIntent);
                    break;

                case R.id.nav_volunteerSearchDrawer:

                    Intent searchIntent = new Intent(organizationProfile.this, volunteerSearch.class);
                    startActivity(searchIntent);
                    break;

                case R.id.nav_main2Drawer:

                    Intent main2Intent = new Intent(organizationProfile.this, MainActivity2.class);
                    startActivity(main2Intent);
                    break;


                case R.id.nav_main3Drawer:

                    Intent main3Intent = new Intent(organizationProfile.this, MainActivity3.class);
                    startActivity(main3Intent);
                    break;


//Paste your privacy policy link

//                    case  R.id.nav_Policy:{
//
//                        Intent browserIntent  = new Intent(Intent.ACTION_VIEW , Uri.parse(""));
//                        startActivity(browserIntent);
//
//                    }
                //       break;
                case R.id.nav_share: {

                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "http://play.google.com/store/apps/detail?id=" + getPackageName();
                    String shareSub = "Try now";
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share using"));

                }
                break;
            }
            return false;
        });

//fb save button
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {
            //temp as in temporary
            EditText tempOrgName = findViewById(R.id.organizationName);
            EditText tempOrgEmail = findViewById(R.id.organizationEmailInput);
            EditText tempHeadEmail = findViewById(R.id.headEmailInput);
            EditText tempOrgPhone = findViewById(R.id.organizationPhoneInput);
            EditText tempHeadPhone = findViewById(R.id.headPhoneInput);
            EditText tempOrgZip = findViewById(R.id.organizationZipCodeInput);

            //assigning strings/ints
            orgName = tempOrgName.getText().toString();
            orgEmail = tempOrgEmail.getText().toString();
            headEmail = tempHeadEmail.getText().toString();
            try {
                orgPhone = Integer.parseInt(tempOrgPhone.getText().toString());
            } catch (NumberFormatException e) {
                orgPhone = -1;
            }
            try {
                headPhone = Integer.parseInt(tempHeadPhone.getText().toString());
            } catch (NumberFormatException e) {
                headPhone = -1;
            }
            try {
                orgZip = Integer.parseInt(tempOrgZip.getText().toString());
            } catch (NumberFormatException e) {
                orgZip = -1;
            }
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            // DatabaseReference myRef = db.getReference("organization test");
            Organization org = new Organization(orgName, orgEmail, headEmail, orgPhone, headPhone, orgZip);
            update(org);
            Toast.makeText(organizationProfile.this, "Saved.", Toast.LENGTH_LONG).show();
            //  db.collection("organizations test").where("orgName","==", org.getOrgName()).get();

            // /fb save button


            //so organization can also see what their info is stored as. Does not save unsaved changes, though.
            //shared pref
/**
 SharedPreferences.Editor editor = sp.edit();
 editor.putString("spOrgName", orgName);
 editor.putString("spOrgEmail", orgEmail);
 editor.putString("spHeadEmail", headEmail);
 editor.putInt("spOrgPhone", orgPhone);
 editor.putInt("spHeadPhone", headPhone);
 editor.putInt("spOrgZip", orgZip);

 editor.apply();
 // /shared pref
 **/
        });
    }

    /**
     * updates the organizations collection in firestore by adding or updating the organization's information.
     *
     * @param org the organization object saved in firestore
     */
    private void update(Organization org) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //    CollectionReference myRef = db.collection("Organizations");
        //so will title the doc with the org name and replace data for the organization if info updated by finding its name. Assumes the organization's name doesn't change...

        db.collection("Organizations").document(orgName).set(org);


    }


    public void setUpToolbar() {
        profileDrawerLayout = findViewById(R.id.profileDrawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, profileDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        profileDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
// /nav
}