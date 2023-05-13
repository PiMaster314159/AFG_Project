package com.example.afgproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ReccomendActivities extends AppCompatActivity {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference organizationsRef = db.collection("Activities");
    ArrayList<ActivityData> totalActivityList;
    ArrayList<ActivityData> sortedActivityList;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        VolunteerSharedData.putMaxDistance(50.0);
//        VolunteerSharedData.putZipCode("01564");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reccomend_activities);
        AlertDialog.Builder builder = new AlertDialog.Builder(ReccomendActivities.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        this.dialog = builder.create();
        dialog.show();
        this.totalActivityList = getActivities();
        try {
            this.sortedActivityList = filterActivities();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            setUpRecycler();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println();

//        System.out.println("size " + sortedActivityList.size());
        System.out.println("test1" + totalActivityList);
        System.out.println("test2" + sortedActivityList);
        for(ActivityData activity : sortedActivityList){
            System.out.println("activity " + activity);
        }
        //        dialog.dismiss();
        System.out.println("test1" + totalActivityList);
        System.out.println("test2" + sortedActivityList);
    }

    @Override
    public void onBackPressed() {

    }

    public void setUpRecycler() throws IOException {
        ActivityRecycler recycler = new ActivityRecycler(R.layout.recycler_item, T_Recycler.LayoutManagerType.LINEAR_LAYOUT_MANAGER_Vertical, filterActivities(), ActivityAdapter.HolderType.ACTIVITY);
        getSupportFragmentManager().beginTransaction().replace(R.id.activityRecyclerView, recycler).commit();
    }
    protected ArrayList<ActivityData> getActivities(){
        totalActivityList = new ArrayList<>();
        organizationsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                totalActivityList.clear();
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        System.out.println("Query successfulf");
                        System.out.println("successful " + documentSnapshot.toObject(ActivityData.class).getDataTitle());
                        ActivityData activity = documentSnapshot.toObject(ActivityData.class);
                        activity.setKey(documentSnapshot.getId());
                        totalActivityList.add(activity);
                    }
                    try {
                        filterActivities();
                        setUpRecycler();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    dialog.dismiss();
                }
                System.out.println("size " + totalActivityList.size());
            }
        });
        organizationsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                totalActivityList.clear();
                for (QueryDocumentSnapshot doc : value) {
                    System.out.println("Query successfulfl");
                    ActivityData activity = doc.toObject(ActivityData.class);
                    activity.setKey(doc.getId());
                    totalActivityList.add(activity);
                }
                try {
                    filterActivities();
                    setUpRecycler();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                dialog.dismiss();
            }
        });
        System.out.println("aaaaaaaaaaaa");
        for(ActivityData activity : totalActivityList){
            System.out.println(activity.getDataTitle());
        }
        return totalActivityList;
    }

    private ArrayList<ActivityData> filterActivities() throws IOException {
        sortedActivityList = (ArrayList<ActivityData>) totalActivityList.clone();
        ArrayList<Double> scores = new ArrayList<Double>();
        HashMap<ActivityData, Double> activityScore = new HashMap<ActivityData, Double>();
        for(ActivityData activity : totalActivityList){
            System.out.println("zipCode " + activity.getZipCode());
            double distance = Utils.getDistance(VolunteerSharedData.getZipCode(), activity.getZipCode());
            System.out.println("Distance " + distance);
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
        return sortedActivityList;
    }
    // https://www.digitalocean.com/community/tutorials/sort-hashmap-by-value-java
    private ArrayList<ActivityData> organizeActivities(HashMap<ActivityData, Double> map){
        ArrayList<Double> list = new ArrayList<>();
        ArrayList<ActivityData> sortedActivities = new ArrayList<>();
        for (Map.Entry<ActivityData, Double> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);
        for (double num : list) {
            for (Map.Entry<ActivityData, Double> entry : map.entrySet()) {
                if (entry.getValue().equals(num)) {
                    System.out.println(entry.getKey().getDataTitle());
                    sortedActivities.add(entry.getKey());
                }
            }
        }
        for(ActivityData activity : sortedActivities){
            System.out.print(activity.getDataTitle() + " ");
        }

        return sortedActivities;
    }

    private double getScore(ActivityData activity, double distance){
        double score = 0;
        ArrayList<String> userInterests = VolunteerSharedData.getInterests();
        ArrayList<String> activityInterests = activity.getInterests();
        ArrayList<String> userSkills = VolunteerSharedData.getSkills();
        ArrayList<String> activitySkills = activity.getSkills();

        if(activityInterests != null){
            int numInterests = 0;
            for(String interest : activityInterests){
                if(userInterests.contains(interest))
                    numInterests++;
            }
            score += 1.5 * numInterests*Math.pow(2, numInterests/(activityInterests.size()+1));
        }

        if(activitySkills != null){
            int numSkills = 0;
            for(String skill : activitySkills){
                if(userInterests.contains(skill))
                    numSkills++;
            }
            score += numSkills*Math.pow(2, numSkills/(activitySkills.size()+1));
        }
        score+=5-distance*(5/ VolunteerSharedData.getMaxDistance());
        System.out.println(activity.getDataTitle() + " " + score);
        return score;
    }
}