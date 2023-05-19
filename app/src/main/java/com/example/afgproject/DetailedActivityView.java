package com.example.afgproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
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
    FloatingActionMenu detailButton;
    String key = "";
    String imageUrl = "";

    /**
     * Import all data from the selected activity and update the view.
     * Check if user is volunteer, then remove the edit and delete options.
     * If user is organization, allow user to transition to edit and delete activity pages
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
        detailButton = findViewById(R.id.detailButton);
        editButton = findViewById(R.id.editButton);
        detailLang = findViewById(R.id.detailLang);
        detailTime = findViewById(R.id.detailTime);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String descriptionText = "Description: " + bundle.getString("Description");
            detailDesc.setText(descriptionText);
            detailTitle.setText(bundle.getString("Title"));
            String date = "Date(s): " + bundle.getString("Language");
            detailLang.setText(date);
            String timeString = "Time: " + bundle.getString("Time");
            detailTime.setText(timeString);
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

        System.out.println("Profile type: " + getSharedPreferences("Profile", Context.MODE_PRIVATE).getInt("ProfileType", 0));
        if(getSharedPreferences("Profile", Context.MODE_PRIVATE).getInt("ProfileType", 0) == 1){
            System.out.println("This should be invisible why aren't you invisible");
            detailButton.setVisibility(View.GONE);
        } else {
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
            editButton.setOnClickListener(view -> {
                Intent intent = new Intent(DetailedActivityView.this, UpdateActivity.class)
                        .putExtra("Title", detailTitle.getText().toString())
                        .putExtra("Description", detailDesc.getText().toString())
                        .putExtra("Language", detailLang.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("Time",detailTime.getText().toString())
                        .putExtra("Key", key);


                startActivity(intent);
            });
        }
        detailButton.bringToFront();
        getWindow().getDecorView().getRootView().getRootView().invalidate();
    }
}