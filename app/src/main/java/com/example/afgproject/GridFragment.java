package com.example.afgproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class GridFragment extends Fragment {
    public GridFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid, container, false);
    }
    //
    public static ArrayList<String> initData(){
        ArrayList<String> dataSource = new ArrayList<>();
        dataSource.add("This");
        dataSource.add("is");
        dataSource.add("a");
        dataSource.add("test");
        dataSource.add("Hello");
        dataSource.add("World");
        dataSource.add("******");
        return dataSource;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        Recycler recycler = new Recycler(Recycler.LayoutManagerType.GRID_LAYOUT_MANAGER, initData());
        transaction.replace(R.id.recyclerView, recycler);
        transaction.commit();
    }
}