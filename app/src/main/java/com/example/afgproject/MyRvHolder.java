package com.example.afgproject;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MyRvHolder extends RecyclerView.ViewHolder {

    public MyRvHolder(@NonNull View itemView) {
        super(itemView);
        System.out.println("ok this is fine");
    }

    public MyRvHolder(){
        super(new View(MyApplication.getContext()));
    }

    public static MyRvHolder newInstance(@NonNull View itemView) {
        return new MyRvHolder(itemView);
    }

    public void setUpHolder(){
    }

    public void setUpHolder(ObjectMap objectMap){
        System.out.println("bbbbbbbbbbbbbbb");
    }
}
