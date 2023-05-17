package com.example.afgproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityRecycler extends Fragment {
        protected RecyclerView rv;
        protected RecyclerView.LayoutManager layoutManager;
        protected ActivityAdapter myAdapter;
        protected int replaceId;

        public ActivityRecycler() {

        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        }

        public ActivityRecycler(int replaceId, ArrayList<ActivityData> data){
                this.replaceId = replaceId;
                layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                this.myAdapter = new ActivityAdapter(data);
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                return inflater.inflate(R.layout.fragment_universal_recycler, container, false);
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

        public void setMyAdapter(ActivityAdapter activityAdapter){
                rv.setAdapter(activityAdapter);
        }

}
