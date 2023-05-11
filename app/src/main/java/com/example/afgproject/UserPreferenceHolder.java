package com.example.afgproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserPreferenceHolder extends MyRvHolder {
    private TextView widgetName;
    public UserPreferenceHolder(View itemView) {
        super(itemView);
        this.widgetName = itemView.findViewById(R.id.settings_widget_text);
        System.out.println("This is really really good");

    }

    @Override
    public void setUpHolder(ObjectMap objectMap) {
        System.out.println("aaaaaaaaaaaaaaaa");
        widgetName.setText((CharSequence) objectMap.getValue("Widget Text"));
    }

    public void setOnClick(){
        View itemView = getItemView();
        itemView.setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && getListener() != null) {
                getListener().onItemClick(this, position);
            }
        });
    }
}
