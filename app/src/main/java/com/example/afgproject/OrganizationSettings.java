package com.example.afgproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragment used for organization  to edit their settings
 */
public class OrganizationSettings extends Fragment {
    /*
     Button saveButton;
     String orgName;
     String orgEmail;
     String headEmail;
     int orgPhone;
     int headPhone;
     int orgZip;
    */

    /**
     * Empty constructor for instantiation and construction
     */
    public OrganizationSettings(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_organization_settings, container, false);
    }

    /**
     * TODO populate method such that all data is saved to firebase once save button is pressed
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // pulling organization's info from firebase needs a set id, so can be future extension.
        /*
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
            Intent intent = new Intent(v.getContext(), OrganizationHome.class);
            startActivity(intent);
            Toast.makeText(organizationCreateProfile.this, "Saved.", Toast.LENGTH_LONG).show();
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
         */
    }
}