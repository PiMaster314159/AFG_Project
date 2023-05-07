package com.example.afgproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

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
    }

    private void setUpRecyclerView() {
        Query query = organizationsRef.orderBy("name", Query.Direction.DESCENDING);
        System.out.println(query);

        FirestoreRecyclerOptions<OrganizationNote> options = new FirestoreRecyclerOptions.Builder<OrganizationNote>()
                .setQuery(query, OrganizationNote.class)
                .build();

        organizationAdapter = new OrganizationNoteAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(organizationAdapter);

        organizationAdapter.setOnItemClickListener((documentSnapshot, position) -> {
            Note note = documentSnapshot.toObject(Note.class);
            String id = documentSnapshot.getId();
            String path = documentSnapshot.getReference().getPath();
            Toast.makeText(FirebaseOnclick.this,
                    "Position: " + position + " ID: " + id, Toast.LENGTH_SHORT).show();
        });
    }


    @Override
    protected void onStart(){
        super.onStart();
        organizationAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        organizationAdapter.stopListening();
    }
}