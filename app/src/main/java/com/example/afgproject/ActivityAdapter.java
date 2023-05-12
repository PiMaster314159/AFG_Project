//package com.example.afgproject;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class ActivityAdapter extends RecyclerView.Adapter<MyRvHolder> {
//    ArrayList<OrganizationActivity> data;
//    MyRvHolder holder;
//
//    int layout;
//    private MyAdapter.OnItemClickListener listener;
//
//    MyAdapter.HolderType holderType;
//
//    MyAdapter.OnItemClickListener onItemClickListener;
//
//    ArrayList<MyRvHolder> holderList;
//
//    public enum HolderType {
//        USER_SETTINGS,
//        ACTIVITY,
//        ORGANIZATION
//    }
//
//    public ActivityAdapter(ArrayList<OrganizationActivity> data, MyAdapter.HolderType holderType) {
//        this.data = data;
//        this.holderType = holderType;
//        this.holderList = new ArrayList<>();
////        System.out.println("yayyyyy2");
//    }
//
//    public ActivityAdapter(ArrayList<OrganizationActivity> data, MyAdapter.HolderType holderType, MyAdapter.OnItemClickListener onItemClickListener) {
//        this.data = data;
//        this.holderType = holderType;
//        this.onItemClickListener = onItemClickListener;
//        this.holderList = new ArrayList<>();
////        System.out.println("yayyyyy1");
////        this.setOnItemClickListener(onItemClickListener);
//    }
//
////    public MyAdapter(ArrayList<ObjectMap> data, MyRvHolder holder, View.OnClickListener onClickListener) throws IllegalAccessException, InstantiationException {
////        this.data = data;
////        this.holder = holder;
////        System.out.println("Item count " + getItemCount());
////    }
//
//    @NonNull
//    @Override
//    public MyRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
////        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
//        System.out.println("Helloooooo11111 " + holderType);
//        View view;
//        switch (holderType){
//            case USER_SETTINGS:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_widget, parent, false);
//                this.holder = new UserPreferenceHolder(view, listener);
//                break;
//            case ACTIVITY:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
//                this.holder = new ActivityHolder(view);
//                System.out.println("this should follow all right");
//                break;
//            case ORGANIZATION:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_widget, parent, false);
//                this.holder = new UserPreferenceHolder(view);
//                break;
//        }
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyRvHolder holder, int position) {
//
//    }
//
//    public void onBindViewHolder(@NonNull ActivityHolder holder, int position) {
//        holder.setUpHolder(data.get(position));
//        holderList.add(holder);
//    }
//
//    @Override
//    public int getItemCount() {
//        if(data != null)
//            return data.size();
//        return 0;
//    }
//
//    public ArrayList<MyRvHolder> getHolderList() {
//        return holderList;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(MyRvHolder myRvHolder);
//    }
//
//    public void setOnItemClickListener(MyAdapter.OnItemClickListener listener) {
//        this.listener = listener;
//    }
//}

package com.example.afgproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.google.android.material.slider.Slider;
//import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder>{
    ArrayList<OrganizationActivity> data;
    Context context;
    ActivityHolder holder;

    int layout;
    private OnItemClickListener listener;

    HolderType holderType;

    MyAdapter.OnItemClickListener onItemClickListener;

    ArrayList<ActivityHolder> holderList;

    public enum HolderType {
        USER_SETTINGS,
        ACTIVITY,
        ORGANIZATION
    }

    public ActivityAdapter(ArrayList<OrganizationActivity> data, HolderType holderType) {
        this.data = data;
        this.context = context;
        this.holderType = holderType;
        this.holderList = new ArrayList<>();
    }

    public ActivityAdapter(ArrayList<OrganizationActivity> data, HolderType holderType, MyAdapter.OnItemClickListener onItemClickListener) {
        this.data = data;
        this.context = context;
        this.holderType = holderType;
        this.onItemClickListener = onItemClickListener;
        this.holderList = new ArrayList<>();
        System.out.println("yayyyyy");
//        this.setOnItemClickListener(onItemClickListener);
    }

//    public MyAdapter(ArrayList<ObjectMap> data, MyRvHolder holder, View.OnClickListener onClickListener) throws IllegalAccessException, InstantiationException {
//        this.data = data;
//        this.holder = holder;
//        System.out.println("Item count " + getItemCount());
//    }

    @NonNull
    @Override
    public ActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        System.out.println("Helloooooo");
        View view;
        switch (holderType){
            case USER_SETTINGS:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_widget, parent, false);
                break;
            case ACTIVITY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
                this.holder = new ActivityHolder(view);
                System.out.println("this should follow all right");
                break;
            case ORGANIZATION:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_widget, parent, false);
                break;
        }
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityHolder holder, int position) {
        holder.setUpHolder(data.get(position));
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image", data.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Description", data.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("Title", data.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Key",data.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("Language", data.get(holder.getAdapterPosition()).getDataLang());
                context.startActivity(intent);
            }
        });
        holderList.add(holder);
    }

    @Override
    public int getItemCount() {
        if(data != null)
            return data.size();
        return 0;
    }

    public ArrayList<ActivityHolder> getHolderList() {
        return holderList;
    }

    public interface OnItemClickListener {
        void onItemClick(ActivityHolder activityHolder);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

