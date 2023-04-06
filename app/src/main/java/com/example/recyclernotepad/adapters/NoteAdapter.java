package com.example.recyclernotepad.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclernotepad.R;
import com.example.recyclernotepad.data.Note;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private final Context context;
    private final List<Note> notes;
    private final LayoutInflater inflater;

    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      // View noteItemView = inflater.inflate(R.layout.note_layout, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent, false);

        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.headerView.setText(currentNote.getHeader());
        holder.textView.setText(currentNote.getText());

        /*holder.headerView.setOnClickListener(view -> {
                Snackbar.make(view,"PTN PNH", BaseTransientBottomBar.LENGTH_LONG).show();
        });*/

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    static class NoteHolder extends RecyclerView.ViewHolder {

        TextView headerView;
        TextView textView;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            headerView = itemView.findViewById(R.id.textView);
            textView = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(view -> {
                Snackbar make = Snackbar.make(view, headerView.getText(), BaseTransientBottomBar.LENGTH_INDEFINITE);
                make.setAction("X", view1 -> {
                   make.dismiss();
                });
                make.show();
            });
        }
    }
}
