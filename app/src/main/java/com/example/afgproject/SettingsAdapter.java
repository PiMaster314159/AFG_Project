package com.example.afgproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;


public class SettingsAdapter extends FirestoreRecyclerAdapter<SettingsAdapter.SettingsNote, SettingsAdapter.SettingsHolder> {
    private OnItemClickListener listener;

    public SettingsAdapter(@NonNull FirestoreRecyclerOptions<SettingsNote> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SettingsHolder holder, int position, @NonNull SettingsNote model) {
        holder.name.setText(model.getName());
    }

    @NonNull
    @Override
    public SettingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,
                parent, false);
        return new SettingsHolder(v);
    }

    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class SettingsHolder extends RecyclerView.ViewHolder {
        TextView name;

        public SettingsHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.settings_widget_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    class SettingsNote {
        private String name;
        public SettingsNote(){

        }
        public SettingsNote(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
