package com.example.afgproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Custom recycler view holder that is adapted for the skills and interest sections of the volunteer settings page
 */
public class SettingsRvHolder extends UniversalHolder{
    private final TextView widgetName;
    private boolean isSelected;

    /**
     * Create rv holder
     * @param itemView - RvHolder view
     * @param listener - onClick listener to detect if settings widget is selected
     */
    public SettingsRvHolder(View itemView, UniversalAdapter.OnItemClickListener listener) {
        super(itemView, listener);
        this.widgetName = itemView.findViewById(R.id.settings_widget_text);
        setUpOnClickListener();

    }

    /**
     * Assign the setting widget's name and whether or not the widget is selected
     * @param objectMap - ObjectMap which is used to assign values to RvHolder
     */
    @Override
    public void setUpHolder(ObjectMap objectMap) {
        widgetName.setText((CharSequence) objectMap.getValue("Widget Text"));
        System.out.println(objectMap.getValue("isSelected"));
        this.isSelected = (boolean) objectMap.getValue("isSelected");
        updateView();
    }

    /**
     * Get the skill/interest name associated with the RvHolder
     * @return skill/interest name associated with the RvHolder
     */
    public String getName(){
        return (String) widgetName.getText();
    }

    /**
     * Reverse current settings widget selection status
     */
    public void toggleSelected(){
        this.isSelected = !this.isSelected;
        updateView();
    }

    /**
     * Transform selected settings widget's check box to indicate that it is selected/deselected
     */
    private void updateView(){
        ImageView circleSelect = getView().findViewById(R.id.select_box);
        if(isSelected) {
            circleSelect.setImageResource(R.drawable.selected_check_box);
        }
        else {
            circleSelect.setImageResource(R.drawable.unselected_check_box);
        }
    }

    /**
     * Get whether the settings widget is selected
     * @return whether the settings widget is selected
     */
    public boolean getSelected(){
        return this.isSelected;
    }

    /**
     * Create/set an onClick listener which is used to alter the selection status of the settings widget
     */
    private void setUpOnClickListener(){
        getView().setOnClickListener(v -> {
            int position = getAbsoluteAdapterPosition();
            if (position != RecyclerView.NO_POSITION && getListener() != null) {
                getListener().onItemClick(this);
            }
        });
    }
}
