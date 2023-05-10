package com.example.afgproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;

public class UserPreferenceHolder extends MyRvHolder {
    private TextView widgetName;
    public UserPreferenceHolder(@NonNull View itemView) {
        super(itemView);
        this.widgetName = itemView.findViewById(R.id.settings_widget_text);
        System.out.println("This is really really good");

    }

    public UserPreferenceHolder() {
        super();
        System.out.println("This is wah");
    }

    public static UserPreferenceHolder newInstance(@NonNull View itemView) {
        return new UserPreferenceHolder(itemView);
    }

    @Override
    public void setUpHolder(ObjectMap objectMap) {
        System.out.println("aaaaaaaaaaaaaaaa");
        widgetName.setText((CharSequence) objectMap.getValue("Widget Text"));
    }
}
