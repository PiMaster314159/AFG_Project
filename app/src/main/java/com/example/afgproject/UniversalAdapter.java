package com.example.afgproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Universal Recycler View adapter
 */
public class UniversalAdapter extends RecyclerView.Adapter<UniversalHolder>{
    ArrayList<ObjectMap> data;
    UniversalHolder holder;

    private OnItemClickListener listener;

    HolderType holderType;

    OnItemClickListener onItemClickListener;

    ArrayList<UniversalHolder> holderList;

    /**
     * When creating a new holder that is compatible with the UniversalHolder specify in enum such that it can be referenced when creating
     */
    public enum HolderType {
        USER_SETTINGS,
    }

    /**
     * Create universal RvAdapter that does not use an onClick listener
     * @param data - ObjectMap array list with indices that correspond to the holder position
     * @param holderType - Enum value that corresponds to the type of holder that will be instantiated within adapter
     */
    public UniversalAdapter(ArrayList<ObjectMap> data, HolderType holderType) {
        this.data = data;
        this.holderType = holderType;
        this.holderList = new ArrayList<>();
    }

    /**
     * Create universal RvAdapter that does not use an onClick listener
     * @param data - ObjectMap array list with indices that correspond to the holder position
     * @param holderType - Enum value that corresponds to the type of holder that will be instantiated within adapter
     * @param onItemClickListener - onClickListener to be assigned to its holders
     */
    public UniversalAdapter(ArrayList<ObjectMap> data, HolderType holderType, OnItemClickListener onItemClickListener) {
        this.data = data;
        System.out.println("data " + data);
        this.holderType = holderType;
        this.onItemClickListener = onItemClickListener;
        this.holderList = new ArrayList<>();
    }

    /**
     * Create RvHolder that corresponds to the specified enum holder type
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return Holder corresponding to the specified enum holder type
     */
    @NonNull
    @Override
    public UniversalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (holderType){
            case USER_SETTINGS:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_widget, parent, false);
                this.holder = new SettingsRvHolder(view, listener);
                break;
        }
        return holder;
    }

    /**
     * Populate holder with objectMap data
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull UniversalHolder holder, int position) {
        holder.setUpHolder(data.get(position));
        holderList.add(holder);
    }

    /**
     * Get number of data items/holders tied to the adapter
     * @return Number of data items/holders tied to the adapter
     */
    @Override
    public int getItemCount() {
        if(data != null)
            return data.size();
        return 0;
    }

    /**
     * Get the list of UniversalHolders within the RvAdapter
     * @return the list of UniversalHolders within the RvAdapter
     */
    public ArrayList<UniversalHolder> getHolderList() {
        return holderList;
    }

    /**
     * OnItem click listener to determine whether any of the holders are tapped by the user
     */
    public interface OnItemClickListener {
        void onItemClick(UniversalHolder UniversalHolder);
    }

    /**
     * Set the actions conducted when holder is tapped by the user
     * @param listener - Command determining the actions conducted when holder is tapped by the user
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
