package com.example.afgproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityRecycler extends T_Recycler{
    protected RecyclerView rv;
    //    ArrayList<String> dataSource;
    protected LinearLayoutManager linearLayoutManager;
    protected RecyclerView.LayoutManager layoutManager;
    protected ActivityAdapter myAdapter;
    protected ArrayList<ActivityData> dataSource;
    protected int id;

    public enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER_HORIZONTAL,
        LINEAR_LAYOUT_MANAGER_Vertical
    }

    ActivityAdapter.HolderType holderType;
    int layout;

    public ActivityRecycler() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ActivityRecycler(int id, T_Recycler.LayoutManagerType layoutManagerType, ArrayList<ActivityData> data, ActivityAdapter.HolderType holderType){
        this.id = id;
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
        this.holderType = holderType;
        this.myAdapter = new ActivityAdapter(dataSource, holderType);
    }

    public ActivityRecycler(int id, T_Recycler.LayoutManagerType layoutManagerType, ArrayList<ActivityData> data, ActivityAdapter.HolderType holderType, ActivityAdapter.OnItemClickListener onItemClickListener){
        this.id = id;
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
        this.holderType = holderType;
        this.myAdapter = new ActivityAdapter(dataSource, holderType, (MyAdapter.OnItemClickListener) onItemClickListener);
        myAdapter.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_t_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = requireView().findViewById(R.id.recyclerView);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(myAdapter);
    }

    public ActivityAdapter getActivityAdapter(){
        return myAdapter;
    }
}
