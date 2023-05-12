package com.example.afgproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ReccomendActivities extends AppCompatActivity {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference organizationsRef = db.collection("Activities");
    ArrayList<OrganizationActivity> totalActivityList;
    ArrayList<OrganizationActivity> sortedActivityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reccomend_activities);
        //        AlertDialog.Builder builder = new AlertDialog.Builder(ReccomendActivities.this);
        //        builder.setCancelable(false);
        //        builder.setView(R.layout.progress_layout);
        //        AlertDialog dialog = builder.create();
        //        dialog.show();
                getActivities();
        try {
            filterActivities();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("size " + sortedActivityList.size());
        System.out.println("test1" + totalActivityList);
        System.out.println("test2" + sortedActivityList);
        //        dialog.dismiss();
        ActivityRecycler recycler = new ActivityRecycler(R.layout.recycler_item, RecyclerTest.LayoutManagerType.LINEAR_LAYOUT_MANAGER_Vertical, totalActivityList, ActivityAdapter.HolderType.ACTIVITY);
        getSupportFragmentManager().beginTransaction().replace(R.id.activityRecyclerView, recycler).commit();
    }
    protected void getActivities(){
        totalActivityList = new ArrayList<>();
        organizationsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                totalActivityList.clear();
                if(task.isSuccessful()) {
                    System.out.println("Query successful");
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        System.out.println("successful");
                        totalActivityList.add(documentSnapshot.toObject(OrganizationActivity.class));
                    }
                }
                System.out.println("size " + totalActivityList.size());
                try {
                    filterActivities();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        organizationsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                totalActivityList.clear();
                for (QueryDocumentSnapshot doc : value) {
                    totalActivityList.add(doc.toObject(OrganizationActivity.class));
                }
                try {
                    filterActivities();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        System.out.println("aaaaaaaaaaaa");
        for(OrganizationActivity activity : totalActivityList){
            System.out.println(activity.getDataTitle());
        }
    }

    private void filterActivities() throws IOException {
        sortedActivityList = (ArrayList<OrganizationActivity>) totalActivityList.clone();
        ArrayList<Double> scores = new ArrayList<Double>();
        HashMap<OrganizationActivity, Double> activityScore = new HashMap<OrganizationActivity, Double>();
        for(OrganizationActivity activity : totalActivityList){
            double distance = Utils.getDistance(VolunteerSharedData.getZipCode(), activity.getZipCode());
            if(distance >= VolunteerSharedData.getMaxDistance()) {
                sortedActivityList.remove(activity);
                continue;
            }
            double score = getScore(activity, distance);
            activityScore.put(activity, score);
            scores.add(score);
        }
        ArrayList<Double> unsortedScores = (ArrayList<Double>) scores.clone();
        Collections.sort(scores, Collections.reverseOrder());
        sortedActivityList = organizeActivities(activityScore);
    }
    // https://www.digitalocean.com/community/tutorials/sort-hashmap-by-value-java
    private ArrayList<OrganizationActivity> organizeActivities(HashMap<OrganizationActivity, Double> map){
        ArrayList<Double> list = new ArrayList<>();
        ArrayList<OrganizationActivity> sortedActivities = new ArrayList<>();
        for (Map.Entry<OrganizationActivity, Double> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        for (double num : list) {
            for (Map.Entry<OrganizationActivity, Double> entry : map.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedActivities.add(entry.getKey());
                }
            }
        }

        return sortedActivities;
    }

    private double getScore(OrganizationActivity activity, double distance){
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
        score += 1.5 * numInterests*Math.pow(2, numInterests/(activityInterests.size()+1));

        int numSkills = 0;
        for(String skill : activitySkills){
            if(userInterests.contains(skill))
                numSkills++;
        }
        score += numSkills*Math.pow(2, numSkills/(activitySkills.size()+1));

        score+=5-distance*(5/ VolunteerSharedData.getMaxDistance());

        return score;
    }
}