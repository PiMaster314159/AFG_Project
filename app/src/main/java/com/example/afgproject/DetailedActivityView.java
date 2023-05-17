package com.example.afgproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Clickable view of activity to find more information
 */
public class DetailedActivityView extends AppCompatActivity {
    TextView detailDesc, detailTitle, detailLang, detailTime;
    ImageView detailImage;
    FloatingActionButton deleteButton, editButton;
    String key = "";
    String imageUrl = "";

    /**
     * Initialize the activity data, as well as the edit and delete data buttons, when applicable
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_activity);
        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        detailLang = findViewById(R.id.detailLang);
        detailTime = findViewById(R.id.detailTime);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            detailLang.setText(bundle.getString("Language"));
            detailTime.setText(bundle.getString("Time"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
        deleteButton.setOnClickListener(view -> {
            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
            storageReference.delete().addOnSuccessListener(unused -> {
                reference.child(key).removeValue();
                Toast.makeText(DetailedActivityView.this, "Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), OrganizationHome.class));
                finish();
            });
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailedActivityView.this, UpdateActivity.class)
                        .putExtra("Title", detailTitle.getText().toString())
                        .putExtra("Description", detailDesc.getText().toString())
                        .putExtra("Language", detailLang.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("Time",detailTime.getText().toString())
                        .putExtra("Key", key);


                startActivity(intent);
            }
        });
    }
}