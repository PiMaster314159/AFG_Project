package com.example.afgproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class RecyclerTest extends Fragment {
    RecyclerView rv;
//    ArrayList<String> dataSource;
    private LinearLayoutManager linearLayoutManager;
    protected RecyclerView.LayoutManager layoutManager;
    MyAdapter myAdapter;
    ArrayList<ObjectMap> dataSource;

    MyRvHolder rvHolder;
    public enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER_HORIZONTAL,
        LINEAR_LAYOUT_MANAGER_Vertical
    }
    int layout;

    public RecyclerTest() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public RecyclerTest(LayoutManagerType layoutManagerType, ArrayList<ObjectMap> data, int layout, MyRvHolder rvHolder){
        dataSource = data;
        switch (layoutManagerType){
            case GRID_LAYOUT_MANAGER:
                layoutManager = new GridLayoutManager(getContext(), 3);
                break;
            case LINEAR_LAYOUT_MANAGER_HORIZONTAL:
                layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                break;
            case LINEAR_LAYOUT_MANAGER_Vertical:
                layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                break;
        }
        this.layout = layout;
        this.rvHolder = rvHolder;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = requireView().findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter((ArrayList<ObjectMap>) dataSource, rvHolder, layout);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(myAdapter);

//        myAdapter.setOnItemClickListener((documentSnapshot, position) -> {
//            Toast.makeText(getContext(), "Hello world", Toast.LENGTH_SHORT).show();
//        });
    }
}