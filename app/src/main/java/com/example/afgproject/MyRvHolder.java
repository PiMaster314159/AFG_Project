package com.example.afgproject;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MyRvHolder extends RecyclerView.ViewHolder {
    private MyAdapter.OnItemClickListener listener;
    private View itemView;

    public MyRvHolder(@NonNull View itemView) {
        super(itemView);
        System.out.println("ok this is fine");
    }

    public MyRvHolder(@NonNull View itemView, MyAdapter.OnItemClickListener listener) {
        super(itemView);
        this.itemView = itemView;
        System.out.println("ok this is fine");
    }

    public static MyRvHolder newInstance(@NonNull View itemView) {
        return new MyRvHolder(itemView);
    }

    public void setUpHolder(){
    }

    public void setUpHolder(ObjectMap objectMap){
        System.out.println("bbbbbbbbbbbbbbb");
    }

    protected View getItemView(){
        return itemView;
    }

    protected MyAdapter.OnItemClickListener getListener(){
        return listener;
    }

    public void setOnClick(){

    }
}
