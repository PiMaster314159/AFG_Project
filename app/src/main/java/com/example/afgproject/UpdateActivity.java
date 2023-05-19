package com.example.afgproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UpdateActivity extends AppCompatActivity {
    ImageView updateImage;
    Button updateButton;
    EditText updateDesc, updateTitle, updateLang, UpdateZipCode, UpdateTime;
    String title, desc, lang, ti, zip;
    String imageUrl;
    String key, oldImageURL;
    Uri uri;
    AlertDialog dialog;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference organizationsRef = db.collection("Activities");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_activity);
        updateButton = findViewById(R.id.updateButton);
        updateDesc = findViewById(R.id.updateDesc);
        updateImage = findViewById(R.id.updateImage);
        updateLang = findViewById(R.id.updateLang);
        updateTitle = findViewById(R.id.updateTitle);
        UpdateTime = findViewById(R.id.UpdateTime);
        UpdateZipCode = findViewById(R.id.UpdateUserZipCode);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            updateImage.setImageURI(uri);
                        } else {
                            Toast.makeText(UpdateActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Glide.with(UpdateActivity.this).load(bundle.getString("Image")).into(updateImage);
            updateTitle.setText(bundle.getString("Title"));
            updateDesc.setText(bundle.getString("Description"));
            updateLang.setText(bundle.getString("Language"));
            UpdateTime.setText(bundle.getString("Time"));
            UpdateZipCode.setText(bundle.getString("ZipCode"));
            key = bundle.getString("Key");
            oldImageURL = bundle.getString("Image");
        }
        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(validData()){
                        saveData();
                        Intent intent = new Intent(UpdateActivity.this, OrganizationHome.class);
                        startActivity(intent);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void saveData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.loading_layout);
        dialog = builder.create();
        dialog.show();
        updateData();

    }
    public void updateData(){
        title = updateTitle.getText().toString().trim();
        desc = updateDesc.getText().toString().trim();
        lang = updateLang.getText().toString();
        zip = UpdateZipCode.getText().toString();
        ti = UpdateTime.getText().toString();
        imageUrl = updateImage.toString();
        ActivityData dataClass = new ActivityData(title, desc, lang, imageUrl, zip, ti);
        organizationsRef.document(uri.getLastPathSegment()).set(dataClass).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }
    private boolean validData() throws IOException {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(UpdateActivity.this)
                .setTitle("Invalid input")
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {})
                .setIcon(android.R.drawable.ic_dialog_alert);
        if(String.valueOf(updateTitle.getText()).isEmpty()){
            alertDialog.setMessage("Please enter a valid activity title.");
            alertDialog.show();
            return false;
        }
        if(!Utils.isValidZipCode(String.valueOf(UpdateZipCode.getText()))){
            alertDialog.setMessage("Please enter a valid zip code.");
            alertDialog.show();
            return false;
        }
        if(String.valueOf(UpdateTime.getText()).isEmpty()){
            alertDialog.setMessage("Please enter a valid activity time.");
            alertDialog.show();
            return false;
        }
        if(String.valueOf(updateLang.getText()).isEmpty()){
            alertDialog.setMessage("Please enter a valid activity date.");
            alertDialog.show();
            return false;
        }
        if(String.valueOf(updateDesc.getText()).isEmpty()){
            alertDialog.setMessage("Please enter a valid activity description.");
            alertDialog.show();
            return false;
        }
        return true;
    }
}