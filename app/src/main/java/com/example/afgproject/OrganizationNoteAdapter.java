package com.example.afgproject;

//import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;


public class OrganizationNoteAdapter extends FirestoreRecyclerAdapter<OrganizationNote, OrganizationNoteAdapter.NoteHolder> {
    private OnItemClickListener listener;

    public OrganizationNoteAdapter(@NonNull FirestoreRecyclerOptions<OrganizationNote> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull OrganizationNote model) {
        System.out.println("test: " + model.getName());
        holder.textViewName.setText(model.getName());
        holder.textViewImage.setText(model.getImage());
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.organization_preview,
                parent, false);
        return new NoteHolder(v);
    }

    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView textViewImage;
        TextView textViewName;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewImage = itemView.findViewById(R.id.text_view_image);
            textViewName = itemView.findViewById(R.id.text_view_name);

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

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public OnItemClickListener getListener(){
        return listener;
    }
}
