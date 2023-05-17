package com.example.afgproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class UploadActivity extends AppCompatActivity {
    ImageView uploadImage;
    Button saveButton;
    EditText uploadTopic, uploadDesc, uploadLang, userZipCode, time;
    String imageURL;
    AlertDialog dialog;
    Uri uri;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference organizationsRef = db.collection("Activities");

    /**
     * Initialize view and prepare for data input
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_activity);
        uploadImage = findViewById(R.id.uploadImage);
        uploadDesc = findViewById(R.id.uploadDesc);
        uploadTopic = findViewById(R.id.uploadTopic);
        uploadLang = findViewById(R.id.uploadLang);
        saveButton = findViewById(R.id.saveButton);
        userZipCode = findViewById(R.id.userZipCode);
        time = findViewById(R.id.time);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        uri = data.getData();
                        uploadImage.setImageURI(uri);
                    } else {
                        Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        saveButton.setOnClickListener(view -> saveData());
    }

    /**
     * Once save button is pressed, push all data to FireBase
     */
    public void saveData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.loading_layout);
        dialog = builder.create();
        dialog.show();
        uploadData();
    }

    /**
     * Upload data to FireBase
     */
    public void uploadData(){
        String title = uploadTopic.getText().toString();
        String desc = uploadDesc.getText().toString();
        String lang = uploadLang.getText().toString();
        String zip = userZipCode.getText().toString();
        String ti = time.getText().toString();
        imageURL = uploadImage.toString();
        ActivityData dataClass = new ActivityData(title, desc, lang, imageURL,zip, ti);

        //We are changing the child from title to currentDate,
        // because we will be updating title as well and it may affect child value.
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        organizationsRef.add(dataClass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(UploadActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}