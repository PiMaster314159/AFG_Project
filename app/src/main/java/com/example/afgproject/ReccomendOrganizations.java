package com.example.afgproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ReccomendOrganizations extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference activitiesRef = db.collection("Activities");

    private ArrayList<OrgActivity> totalActivityList;

    private ArrayList<OrgActivity> filteredActivityList;

    private OrganizationNoteAdapter organizationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.user_preferences_map), Context.MODE_PRIVATE).edit();
        String[] interests = {"Health", "Education"};
        String[] skills = {"Working with Children"};
        String zipCode = "01564";
        int maxDistance = 50;
        editor.putStringSet("interests", new HashSet<>(new ArrayList<>(Arrays.asList(interests)))).apply();
        editor.putStringSet("skills", new HashSet<>(new ArrayList<>(Arrays.asList(skills)))).apply();
        editor.putString("zipCode", zipCode).apply();
        editor.putInt("maxDist", maxDistance).apply();

        try {
            List<Address> addresses = new Geocoder(this).getFromLocationName("01564", 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("Test1 " + new Geocoder(MyApplication.getContext()).getFromLocationName("01604", 1).get(0).getFeatureName());
            System.out.println("Test2 " + new Geocoder(MyApplication.getContext()).getFromLocationName(VolunteerSharedData.getZipCode(), 1).get(0).getFeatureName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("Distance: " + Utils.getDistance(VolunteerSharedData.getZipCode(), "01604"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Testing... " + (new Location("01604")).getLatitude());

        setContentView(R.layout.activity_reccomend_organizations);
//        setUpRecyclerView();
    }

    private void setUpActivities() {
        activitiesRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot document: task.getResult()){
                    OrgActivity activity = document.toObject(OrgActivity.class);
                    totalActivityList.add(activity);
                }
            }
        });
    }

    private void filterActivities() throws IOException {
        filteredActivityList = (ArrayList<OrgActivity>) totalActivityList.clone();
        ArrayList<Double> scores = new ArrayList<Double>();
        HashMap<OrgActivity, Double> activityScore = new HashMap<OrgActivity, Double>();
        for(OrgActivity activity : totalActivityList){
            double distance = Utils.getDistance(VolunteerSharedData.getZipCode(), activity.getZipCode());
            if(distance >= VolunteerSharedData.getMaxDistance()) {
                filteredActivityList.remove(activity);
                continue;
            }
            double score = getScore(activity, distance);
            activityScore.put(activity, score);
            scores.add(score);
        }
        ArrayList<Double> unsortedScores = (ArrayList<Double>) scores.clone();
        Collections.sort(scores, Collections.reverseOrder());
        filteredActivityList = organizeActivities(activityScore);
    }
    // https://www.digitalocean.com/community/tutorials/sort-hashmap-by-value-java
    private ArrayList<OrgActivity> organizeActivities(HashMap<OrgActivity, Double> map){
        ArrayList<Double> list = new ArrayList<>();
        ArrayList<OrgActivity> sortedActivities = new ArrayList<>();
        for (Map.Entry<OrgActivity, Double> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        for (double num : list) {
            for (Map.Entry<OrgActivity, Double> entry : map.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedActivities.add(entry.getKey());
                }
            }
        }
        return sortedActivities;
    }

    private double getScore(OrgActivity activity, double distance){
        double score = 0;
        ArrayList<String> userInterests = VolunteerSharedData.getInterests();
        ArrayList<String> activityInterests = activity.getInterests();
        ArrayList<String> userSkills = VolunteerSharedData.getSkills();
        ArrayList<String> activitySkills = activity.getSkills();
        int numInterests = 0;
        for(String interest : activityInterests){
            if(userInterests.contains(interest))
                numInterests++;
        }
        score += 1.5 * numInterests*Math.pow(2, numInterests/activityInterests.size());

        int numSkills = 0;
        for(String skill : activitySkills){
            if(userInterests.contains(skill))
                numSkills++;
        }
        score += numSkills*Math.pow(2, numSkills/activitySkills.size());

        score+=5-distance*(5/ VolunteerSharedData.getMaxDistance());

        return score;
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