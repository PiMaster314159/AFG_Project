package com.example.afgproject;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Base class for the universal recycler view
 * Exists so that there is universal applicability of the UniversalRecycler methods
 */
public class UniversalHolder extends RecyclerView.ViewHolder {
    private UniversalAdapter.OnItemClickListener listener;
    private View itemView;

    /**
     * Create empty RvHolder
     * @param itemView - RvHolder view
     */
    public UniversalHolder(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * Create empty RvHolder with an onClick listener
     * @param itemView - RvHolder view
     * @param listener - onClick listener for holder - used for the settings widget to see if user selected skill or interest
     */
    public UniversalHolder(@NonNull View itemView, UniversalAdapter.OnItemClickListener listener) {
        super(itemView);
        this.itemView = itemView;
        this.listener = listener;
    }

    /**
     * Get the view associated with RvHolder
     * @return view associated with RvHolder
     */
    public View getView(){
        return itemView;
    }

    /**
     * Assign object map to view holder to populate RvHolder fields (empty but used for overrides)
     * @param objectMap - ObjectMap which is used to assign values to RvHolder
     */
    public void setUpHolder(ObjectMap objectMap){
    }

    /**
     * Get the onClick listener tied to holder
     * @return onClick listener tied to holder
     */
    protected UniversalAdapter.OnItemClickListener getListener(){
        return listener;
    }

    /**
     * Get the name/title given to the holder (empty but used for overrides)
     * @return name/title given to the holder
     */
    public String getName(){
        return "";
    }
}
