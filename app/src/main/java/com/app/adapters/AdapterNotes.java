package com.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.app.R;
import com.app.classes.Note;
import java.util.ArrayList;

public class AdapterNotes  extends RecyclerView.Adapter<AdapterNotes.MyViewHolder> {

    protected ArrayList<Note> Notes;
    protected onClickNotes mOnClickNotes;

    public AdapterNotes(ArrayList<Note> notes, onClickNotes mOnClickNotes) {
        Notes = notes;
        this.mOnClickNotes = mOnClickNotes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new MyViewHolder(itemView, mOnClickNotes);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(Notes.get(position).getTitle());
        holder.title.setTag(Notes.get(position).getId());
        holder.note.setText(Notes.get(position).getNote());
    }



    @Override
    public int getItemCount() {
        return Notes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, note;

        onClickNotes onClickNotes;

        public MyViewHolder(@NonNull View itemView, onClickNotes onClickNotes) {
            super(itemView);

            title =itemView.findViewById(R.id.item_Title);
            note =itemView.findViewById(R.id.item_Note);
            this.onClickNotes =onClickNotes;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.onClickNotes.onClickNote(getAdapterPosition());
        }
    }
    public interface onClickNotes {

        void onClickNote(int position);
    }
}
