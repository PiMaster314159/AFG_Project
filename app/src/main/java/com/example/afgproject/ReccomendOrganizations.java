package com.example.afgproject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afgproject.databinding.ActivityReccomendOrganizationsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ReccomendOrganizations extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference activitiesRef = db.collection("Activities");

    private ArrayList<Activity> totalActivityList;

    private ArrayList<Activity> filteredActivityList;

    private OrganizationNoteAdapter organizationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
        String[] interests = {"Health", "Education"};
        String[] skills = {"Working with Children"};
        String zipCode = "01564";
        editor.putStringSet("Interests", new HashSet<String>(new ArrayList<String>(Arrays.asList(interests)))).apply();
        editor.putStringSet("Skills", new HashSet<String>(new ArrayList<String>(Arrays.asList(skills)))).apply();
        editor.putInt("Zip Code", Integer.parseInt(zipCode));

        try {
            List<Address> addresses = new Geocoder(this).getFromLocationName("01564", 1);
            for(Address address : addresses){

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setContentView(R.layout.activity_reccomend_organizations);
//        setUpRecyclerView();
    }

    private void setUpActivities() {
        activitiesRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot document: task.getResult()){
                    Activity activity = document.toObject(Activity.class);
                    totalActivityList.add(activity);
                }
            }
        });

    }

    private void filterActivities() throws IOException {
//        for(Activity activity : totalActivityList){
//            List<Address> geocoder = new Geocoder(this).getFromLocationName("01564", 1);
//        }
    }

    private void getScore(Activity activity){

    }

    private void setUpRecyclerView() {
//        Query query = organizationsRef.orderBy("name", Query.Direction.DESCENDING);
//        System.out.println(query);
//
//        FirestoreRecyclerOptions<OrganizationNote> options = new FirestoreRecyclerOptions.Builder<OrganizationNote>()
//                .setQuery(query, OrganizationNote.class)
//                .build();
//
//        organizationAdapter = new OrganizationNoteAdapter(options);
//
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        recyclerView.setAdapter(organizationAdapter);
//
//        organizationAdapter.setOnItemClickListener((documentSnapshot, position) -> {
//            Note note = documentSnapshot.toObject(Note.class);
//            String id = documentSnapshot.getId();
//            String path = documentSnapshot.getReference().getPath();
//        });
    }


//    @Override
//    protected void onStart(){
//        super.onStart();
//        organizationAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        organizationAdapter.stopListening();
//    }
}