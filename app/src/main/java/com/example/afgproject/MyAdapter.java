package com.example.afgproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyRvHolder>{
    ArrayList<ObjectMap> data;
    MyRvHolder holder;

    Class holderType;
    int layout;
    private OrganizationNoteAdapter.OnItemClickListener listener;

    public MyAdapter(ArrayList<ObjectMap> data, Object holder, int layout) {
        this.data = data;
        this.holder = (MyRvHolder) holder;
        this.layout = layout;
    }

    public MyAdapter(ArrayList<ObjectMap> data, MyRvHolder holder, View.OnClickListener onClickListener) throws IllegalAccessException, InstantiationException {
        this.data = data;
        this.holder = holder;
        System.out.println("Item count " + getItemCount());
    }

    @NonNull
    @Override
    public MyRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            System.out.println("Helloooooo");
            this.holder = holder.newInstance(view);
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
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OrganizationNoteAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
