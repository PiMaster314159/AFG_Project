package com.example.afgproject;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

/**
 * Holder class for event/activity data. Works in conjunction with ActivityAdapter and ActivityRecycler
 */
public class ActivityHolder extends UniversalHolder{
    ImageView recImage;
    TextView recTitle, recDesc, recLang;
    CardView recCard;
    String key;
    View itemView;

    /**
     * Create new activity holder
     * @param itemView - View of holder within adapter
     */
    public ActivityHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recLang);
        recTitle = itemView.findViewById(R.id.recTitle);
    }

    /**
     * Get context of holder
     * @return context of holder
     */
    public Context getContext(){
        return itemView.getContext();
    }

    /**
     * Populate ActivityHolder with data from activity
     * Alter visible portions of the activity content
     * @param activity data from ActivityData class and populated by FireBase
     */
    public void setUpHolder(ActivityData activity) {
        Glide.with(getContext()).load(activity.getDataImage()).into(recImage);
        recTitle.setText(activity.getDataTitle());
        recDesc.setText(activity.getDataDesc());
        recLang.setText(activity.getDataLang());
        this.key = activity.getKey();
    }

/* Unused getMethods
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
    }*/
}
