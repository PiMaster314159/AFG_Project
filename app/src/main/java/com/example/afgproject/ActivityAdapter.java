package com.example.afgproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter class for the ActivityRecycler
 */
public class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder> {
    ArrayList<ActivityData> data;
    Context context;
    ActivityHolder holder;

    ArrayList<ActivityHolder> holderList;

    /**
     * Create new activity adapter object
     * @param data - array list of activity data to populate among its holder children. Index in array corresponds to order it is listed
     */
    public ActivityAdapter(ArrayList<ActivityData> data) {
        this.data = data;
        this.holderList = new ArrayList<>();
    }

    /**
     * Set the holder type and the context of the adapter
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    public ActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card, parent, false);
        this.holder = new ActivityHolder(view);
        this.context = parent.getContext();
        return holder;
    }

    /**
     * Set up holder data and create an onClick listener that brings the user onto the detailed activity page once a holder is clicked
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ActivityHolder holder, int position) {
        holder.setUpHolder(data.get(position));
        holder.recCard.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailedActivityView.class);
            intent.putExtra("Image", data.get(holder.getAbsoluteAdapterPosition()).getDataImage());
            intent.putExtra("Description", data.get(holder.getAbsoluteAdapterPosition()).getDataDesc());
            intent.putExtra("Title", data.get(holder.getAbsoluteAdapterPosition()).getDataTitle());
            intent.putExtra("Key",data.get(holder.getAbsoluteAdapterPosition()).getKey());
            intent.putExtra("Language", data.get(holder.getAbsoluteAdapterPosition()).getDataLang());
            intent.putExtra("Time", data.get(holder.getAbsoluteAdapterPosition()).getTime());

            context.startActivity(intent);
        });
        holderList.add(holder);
    }

    /**
     * Get number of holders within adapter
     * @return number of holders within adapter
     */
    @Override
    public int getItemCount() {
        if(data != null)
            return data.size();
        return 0;
    }

    /**
     * Refresh the adapter and holders
     * @param searchList - new list of data to include within the adapter
     * TODO find alternative to the notifyDataSetChanged() method
     */
    public void searchDataList(ArrayList<ActivityData> searchList){
        data = searchList;
        notifyDataSetChanged();
    }


}
