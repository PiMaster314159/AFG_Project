package com.example.afgproject;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;

public class UserPreferenceHolder extends MyRvHolder {
    private TextView widgetName;
    private boolean isSelected;

    private ChangeListener changelistener;
    private MyAdapter.OnItemClickListener listener;
    public UserPreferenceHolder(View itemView) {
        super(itemView);
        this.widgetName = itemView.findViewById(R.id.settings_widget_text);
    }

    public UserPreferenceHolder(View itemView, MyAdapter.OnItemClickListener listener) {
        super(itemView, listener);
        this.widgetName = itemView.findViewById(R.id.settings_widget_text);
        this.listener = listener;
        setUpOnClickListener();

    }

    @Override
    public void setUpHolder(ObjectMap objectMap) {
        widgetName.setText((CharSequence) objectMap.getValue("Widget Text"));
        this.isSelected = (boolean) objectMap.getValue("isSelected");
        updateView();
    }

//    public void setOnClick(){
//        View itemView = getItemView();
//        itemView.setOnClickListener(v -> {
//            int position = getAdapterPosition();
//            if (position != RecyclerView.NO_POSITION && getListener() != null) {
//                getListener().onItemClick(this, position);
//            }
//        });
//    }

    public String getName(){
        return (String) widgetName.getText();
    }

    public void toggleSelected(){
        this.isSelected = !this.isSelected;
        updateView();
    }

    @SuppressLint({"ResourceAsColor", "ResourceType"})
    private void updateView(){
        ImageView circleSelect = ((ImageView) getView().findViewById(R.id.select_box));
        MaterialCardView widgetCard = (MaterialCardView) getView().findViewById(R.id.widget_card);
        TextView widgetText = (TextView) getView().findViewById(R.id.settings_widget_text);
        if(isSelected) {
            circleSelect.setImageResource(R.drawable.selected_check_box);
            widgetCard.setStrokeColor(R.color.lavender);
        }
        else {
            circleSelect.setImageResource(R.drawable.unselected_check_box);
            widgetCard.setStrokeColor(R.color.Gray);
        }
    }

    public boolean getSelected(){
        return this.isSelected;
    }
    private void setUpOnClickListener(){
        getView().setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && getListener() != null) {
                getListener().onItemClick(this);
            }
        });
    }
}
