package com.example.afgproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

/**
 * Home page for organization accounts
 */
public class OrganizationHome extends AppCompatActivity {
    FloatingActionButton fab;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final CollectionReference activityReference = db.collection("Activities");
    ArrayList<ActivityData> dataList;
    ActivityAdapter adapter;
    SearchView searchView;

    MyNavigationView navigationView;

    /**
     * Create activity recycler view that populates with data from fireBase
     * Include search bar that allows user to iterate through titles
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_home);

        navigationView = new MyNavigationView(findViewById(R.id.navigation_menu));

        fab = findViewById(R.id.fab);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        AlertDialog.Builder builder = new AlertDialog.Builder(OrganizationHome.this);
        builder.setCancelable(false);
        builder.setView(R.layout.loading_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        dataList = new ArrayList<>();
        adapter = new ActivityAdapter(dataList);
        dialog.show();
        activityReference.get().addOnCompleteListener(task -> {
            dataList.clear();
            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                ActivityData dataClass = documentSnapshot.toObject(ActivityData.class);
                dataClass.setKey((String) documentSnapshot.get("Key"));
                dataList.add(dataClass);
            }
            setUpRecycler();
            dialog.dismiss();
        });
        activityReference.addSnapshotListener((value, error) -> {
            dataList.clear();
            if(value != null){
                for (QueryDocumentSnapshot doc : value) {
                    ActivityData dataClass = doc.toObject(ActivityData.class);
                    dataClass.setKey((String) doc.get("Key"));
                    dataList.add(dataClass);
                }
                setUpRecycler();
            }

            dialog.dismiss();
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
        fab.setOnClickListener(view -> {
                Intent intent = new Intent(OrganizationHome.this, UploadActivity.class);
                startActivity(intent);
        });
    }

    /**
     * Overwrite fragment with newly updated recycler
     */
    public void setUpRecycler()  {
        ActivityRecycler recycler = new ActivityRecycler(R.id.recyclerView, dataList);
        getSupportFragmentManager().beginTransaction().replace(R.id.recyclerView, recycler).commit();
    }

    /**
     * Ensure that user cannot use back button
     */
    @Override
    public void onBackPressed () {
    }

    /**
     * Filter through activities given the specified text
     * Find activities whose titles match that of the text
     * @param text Search bar text
     */
    public void searchList(String text){
        ArrayList<ActivityData> searchList = new ArrayList<>();
        for (ActivityData dataClass: dataList){
            if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}