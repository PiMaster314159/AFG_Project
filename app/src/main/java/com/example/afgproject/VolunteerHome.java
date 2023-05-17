package com.example.afgproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;

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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Home page for volunteer profiles
 */
public class VolunteerHome extends AppCompatActivity {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference organizationsRef = db.collection("Activities");
    ArrayList<ActivityData> totalActivityList;
    ArrayList<ActivityData> sortedActivityList;
    AlertDialog dialog;
    MyNavigationView navigationView;

    /**
     * Initialize list of all activities as well as the sorted list, and call the sort function
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        VolunteerSharedData.putMaxDistance(50.0);
//        VolunteerSharedData.putZipCode("01564");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_home);
        this.navigationView = new MyNavigationView(findViewById(R.id.navigation_menu));
        AlertDialog.Builder builder = new AlertDialog.Builder(VolunteerHome.this);
        builder.setCancelable(false);
        builder.setView(R.layout.loading_layout);
        this.dialog = builder.create();
        dialog.show();
        this.sortedActivityList = new ArrayList<>();
        this.totalActivityList = getActivities();



    }

    /**
     * Override to ensure that the user cannot use the back button
     */
    @Override
    public void onBackPressed() {

    }

    /**
     * Instantiate a new recycler
     */
    public void setUpRecycler() {
        ActivityRecycler recycler = new ActivityRecycler(R.id.recyclerView, sortedActivityList);
        getSupportFragmentManager().beginTransaction().replace(R.id.activityRecyclerView, recycler).commit();
    }

    /**
     * Create event listeners for changing and pull completion. Once an alteration is made, filtering will occur and create a list of recommended activities
     * @return list of all activities within the database
     */
    protected ArrayList<ActivityData> getActivities(){
        totalActivityList = new ArrayList<>();
        organizationsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                totalActivityList.clear();
                sortedActivityList.clear();
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
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
                for (QueryDocumentSnapshot doc : value) {
                    ActivityData activity = doc.toObject(ActivityData.class);
                    activity.setKey(doc.getId());
                    totalActivityList.add(activity);
                }
                try {
                    sortedActivityList.clear();
                    filterActivities();
                    setUpRecycler();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                dialog.dismiss();
            }
        });
        for(ActivityData activity : totalActivityList){
        }
        return totalActivityList;
    }

    /**
     * Filter through all activities to form a list of recommended activities for the user
     * Filter by distance, then sort by fit (skills, interests, and distance)
     */
    private void filterActivities() throws IOException {
        sortedActivityList = (ArrayList<ActivityData>) totalActivityList.clone();
        ArrayList<Double> scores = new ArrayList<Double>();
        HashMap<ActivityData, Double> activityScore = new HashMap<ActivityData, Double>();
        for(ActivityData activity : totalActivityList){
            double distance = Utils.getDistance(VolunteerData.getZipCode(), activity.getZipCode());
            if(distance >= VolunteerData.getMaxDistance()) {
                sortedActivityList.remove(activity);
                continue;
            }
            double score = getScore(activity, distance);
            activityScore.put(activity, score);
            scores.add(score);
        }
        System.out.println("ACTIVITY LIST " + sortedActivityList);
        ArrayList<Double> unsortedScores = (ArrayList<Double>) scores.clone();
        Collections.sort(scores, Collections.reverseOrder());
        sortedActivityList = organizeActivities(activityScore);
    }
    // https://www.digitalocean.com/community/tutorials/sort-hashmap-by-value-java

    /**
     * Sort activities using hashmap and sorting hashmaps.
     * @param map Hashmap relating specific ativities to a 'fit score'
     * @return sorted list of activities
     */
    private ArrayList<ActivityData> organizeActivities(HashMap<ActivityData, Double> map){
        ArrayList<Double> list = new ArrayList<>();
        System.out.println("SORTEDACTIVITYLIST " + sortedActivityList);
        ArrayList<ActivityData> sortedActivities = new ArrayList<>();
        for (Map.Entry<ActivityData, Double> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);
        ArrayList<ActivityData> mapList = new ArrayList<>(map.keySet());
        for (int i = 0; i<list.size(); i++) {//Map.Entry<ActivityData, Double> entry : map.entrySet()
            for (int j = 0; j<mapList.size(); j++) {
                if (Objects.equals(map.get(mapList.get(j)), list.get(i))) {
                    sortedActivities.add(mapList.get(j));
                    list.remove(list.get(i));
                    mapList.remove(mapList.get(j));
                    i--;
                    j--;
                    break;
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
        ArrayList<String> userInterests = VolunteerData.getInterests();
        ArrayList<String> activityInterests = activity.getInterests();
        ArrayList<String> userSkills = VolunteerData.getSkills();
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
        score+=5-distance*(5/ VolunteerData.getMaxDistance());
        System.out.println(activity.getDataTitle() + " " + score);
        return score;
    }
}