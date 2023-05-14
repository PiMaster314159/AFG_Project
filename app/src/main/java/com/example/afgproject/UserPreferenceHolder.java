package com.example.afgproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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

    private void updateView(){
        if(isSelected)
            ((ImageView) getView().findViewById(R.id.select_box)).setBackgroundResource(R.drawable.selected_check_box);
        else
            ((ImageView) getView().findViewById(R.id.select_box)).setBackgroundResource(R.drawable.unselected_check_box);
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
