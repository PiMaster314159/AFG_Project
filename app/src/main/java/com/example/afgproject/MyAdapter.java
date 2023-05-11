package com.example.afgproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyRvHolder>{
    ArrayList<ObjectMap> data;
    MyRvHolder holder;

    int layout;
    private OnItemClickListener listener;

    HolderType holderType;

    OnItemClickListener onItemClickListener;

    public enum HolderType {
        USER_SETTINGS,
        ACTIVITY,
        ORGANIZATION
    }

    public MyAdapter(ArrayList<ObjectMap> data, HolderType holderType) {
        this.data = data;
        this.holderType = holderType;
    }

    public MyAdapter(ArrayList<ObjectMap> data, HolderType holderType, OnItemClickListener onItemClickListener) {
        this.data = data;
        this.holderType = holderType;
        this.onItemClickListener = onItemClickListener;
    }

//    public MyAdapter(ArrayList<ObjectMap> data, MyRvHolder holder, View.OnClickListener onClickListener) throws IllegalAccessException, InstantiationException {
//        this.data = data;
//        this.holder = holder;
//        System.out.println("Item count " + getItemCount());
//    }

    @NonNull
    @Override
    public MyRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            System.out.println("Helloooooo");
            View view;
        switch (holderType){
            case USER_SETTINGS:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_widget, parent, false);
                this.holder = new UserPreferenceHolder(view);
                break;
            case ACTIVITY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_widget, parent, false);
                this.holder = new UserPreferenceHolder(view);
                break;
            case ORGANIZATION:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_widget, parent, false);
                this.holder = new UserPreferenceHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRvHolder holder, int position) {
        holder.setUpHolder(data.get(position));
    }

    @Override
    public int getItemCount() {
        if(data != null)
            return data.size();
        return 0;
    }

    public interface OnItemClickListener {
        void onItemClick(MyRvHolder myRvHolder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
