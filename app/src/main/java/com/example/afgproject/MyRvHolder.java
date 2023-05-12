package com.example.afgproject;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.slider.Slider;

import java.util.ArrayList;

class MyRvHolder extends RecyclerView.ViewHolder {
    private MyAdapter.OnItemClickListener listener;
    private View itemView;
    private ChangeListener changeListener;

    public MyRvHolder(@NonNull View itemView) {
        super(itemView);
        System.out.println("ok this is fine");
    }

    public MyRvHolder(@NonNull View itemView, MyAdapter.OnItemClickListener listener) {
        super(itemView);
        this.itemView = itemView;
        this.listener = listener;
    }

    public static MyRvHolder newInstance(@NonNull View itemView) {
        return new MyRvHolder(itemView);
    }

    public void setUpHolder(){
    }

    public View getView(){
        return itemView;
    }

    public void setUpHolder(ObjectMap objectMap){
    }

    protected View getItemView(){
        return itemView;
    }

    protected MyAdapter.OnItemClickListener getListener(){
        return listener;
    }

    public interface ChangeListener {
        void onChange();
    }


    public ChangeListener getChangeListener() {
        return changeListener;
    }

    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public String getName(){
        return "";
    }
}
