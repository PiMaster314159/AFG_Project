package com.example.afgproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class T_Recycler extends Fragment {
    protected RecyclerView rv;
//    ArrayList<String> dataSource;
    protected LinearLayoutManager linearLayoutManager;
    protected RecyclerView.LayoutManager layoutManager;
    protected MyAdapter myAdapter;
    protected ArrayList<ObjectMap> dataSource;
    protected int id;

    public enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER_HORIZONTAL,
        LINEAR_LAYOUT_MANAGER_Vertical
    }
    MyAdapter.HolderType holderType;
    int layout;

    public T_Recycler() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public T_Recycler(int id, LayoutManagerType layoutManagerType, ArrayList<ObjectMap> data, MyAdapter.HolderType holderType){
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
        this.myAdapter = new MyAdapter(dataSource, holderType);
    }

    public T_Recycler(int id, LayoutManagerType layoutManagerType, ArrayList<ObjectMap> data, MyAdapter.HolderType holderType, MyAdapter.OnItemClickListener onItemClickListener){
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
        this.myAdapter = new MyAdapter(dataSource, holderType, onItemClickListener);
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

    public MyAdapter getAdapter(){
        return myAdapter;
    }
}