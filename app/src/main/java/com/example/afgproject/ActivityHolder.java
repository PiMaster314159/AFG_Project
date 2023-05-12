package com.example.afgproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ActivityHolder extends MyRvHolder{
    ImageView recImage;
    TextView recTitle, recDesc, recLang;
    CardView recCard;
    String key;
    public ActivityHolder(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recLang);
        recTitle = itemView.findViewById(R.id.recTitle);
        System.out.println("helloooo222");
    }

    public void setUpHolder(OrganizationActivity activity) {
//        Glide.with(getView().getContext()).load(activity.getDataImage()).into(recImage);
        recTitle.setText(activity.getDataTitle());
        recDesc.setText(activity.getDataDesc());
        recLang.setText(activity.getDataLang());
        this.key = activity.getKey();
        System.out.println("helloooo");
    }

    public String getKey(){
        return key;
    }

    public ImageView getRecImage() {
        return recImage;
    }

    public TextView getRecTitle() {
        return recTitle;
    }

    public TextView getRecDesc() {
        return recDesc;
    }

    public TextView getRecLang() {
        return recLang;
    }

    public CardView getRecCard() {
        return recCard;
    }
}
