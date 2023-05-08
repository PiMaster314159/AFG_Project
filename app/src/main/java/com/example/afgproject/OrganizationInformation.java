package com.example.afgproject;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
public class OrganizationInformation extends Fragment {

    private String image;
    private String name;
    private String description;
    private String contactInformation;
    private ArrayList<String> fields;

    public OrganizationInformation() {
        // Required empty public constructor
    }

    public OrganizationInformation(OrganizationNote organizationNote) {
        this.image = organizationNote.getImage();
        this.name = organizationNote.getName();
        this.description = organizationNote.getDescription();
        this.contactInformation = organizationNote.getContactInfo();
        System.out.println("Fields " + organizationNote.getFields());
        this.fields = organizationNote.getFields();
        System.out.println("Fields2 " + fields);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("AAAAAAAAAAAAAAAAAA");
        View view = inflater.inflate(R.layout.fragment_organization_information, container, false);



        System.out.println("BBBBBBBBBBBBBBBBBB");

        return view;
    }

    public ImageButton getExitButton(){
        return (ImageButton) getView().findViewById(R.id.exit_info);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("Everything has been created");
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        System.out.println("layoutManagerstuff2" + R.id.organization_fields);
        System.out.println("layoutManagerstuff3 " + this.fields);
        Recycler recycler = new Recycler(fields);
        System.out.println("layoutManagerstuff " + R.id.organization_fields);
        transaction.replace(R.id.organization_fields, recycler);
        transaction.commit();

        getExitButton().setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction().hide(getActivity().getSupportFragmentManager().findFragmentById(R.id.organization_info_holder)).commit();
            System.out.println("Hello worlddddddd");
        });

        ((TextView) getView().findViewById(R.id.organization_Name)).setText(name);
        ((TextView) getView().findViewById(R.id.organization_image)).setText(image);
        ((TextView) getView().findViewById(R.id.organization_description)).setText(description);
        ((TextView) getView().findViewById(R.id.organization_contactInfo)).setText(contactInformation);
//        ((TextView) getView().findViewById(R.id.organization_Name)).setText(name);
//        ((TextView) getView().findViewById(R.id.organization_Name)).setText(name);

//        System.out.println("View " + getView().findViewById(R.id.exit_info));
    }
}