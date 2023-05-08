package com.example.afgproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Objects;

public class FirebaseOnclick extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference organizationsRef = db.collection("Organizations");

    private OrganizationNoteAdapter organizationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_onclick);
        System.out.println("This is a test");
        setUpRecyclerView();

        OrganizationInformation organizationInformation = new OrganizationInformation();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.add(R.id.organization_info_holder, organizationInformation);
        transaction.commit();
    }



    private void setUpRecyclerView() {
        Query query = organizationsRef.orderBy("name", Query.Direction.DESCENDING);
        System.out.println(query);

        FirestoreRecyclerOptions<OrganizationNote> options = new FirestoreRecyclerOptions.Builder<OrganizationNote>()
                .setQuery(query, OrganizationNote.class)
                .build();

        organizationAdapter = new OrganizationNoteAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        System.out.println("tesssstttt" + recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(organizationAdapter);

        organizationAdapter.setOnItemClickListener((documentSnapshot, position) -> {
            System.out.println("Hello world1");
            OrganizationNote note = documentSnapshot.toObject(OrganizationNote.class);
            String id = documentSnapshot.getId();
            String path = documentSnapshot.getReference().getPath();
            Toast.makeText(FirebaseOnclick.this,
                    "Position: " + position + " ID: " + id, Toast.LENGTH_SHORT).show();
            OrganizationInformation organizationInformation = new OrganizationInformation(note);
//            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.travel_down, R.anim.travel_up).show(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.organization_info_holder))).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.organization_info_holder, organizationInformation).commit();

        });
    }


    @Override
    protected void onStart(){
        super.onStart();
        organizationAdapter.startListening();
        ((OrganizationInformation) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.organization_info_holder))).getExitButton().setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().detach(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.organization_info_holder))).commit();
            System.out.println("Hello world2");
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        organizationAdapter.stopListening();
    }

    private void setEventListener() {
        System.out.println("akdjfal;ksdjfaosieujasdkljf " + ((OrganizationInformation) getSupportFragmentManager().findFragmentById(R.id.organization_info_holder)).getExitButton());
    }
}