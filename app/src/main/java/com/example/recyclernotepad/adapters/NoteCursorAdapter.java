package com.example.recyclernotepad.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclernotepad.NoteActivity;
import com.example.recyclernotepad.R;
import com.example.recyclernotepad.data.DBManager;
import com.example.recyclernotepad.data.Note;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class NoteCursorAdapter extends RecyclerView.Adapter<NoteCursorAdapter.NoteCursorHolder> {
    private Cursor cursor;

    public NoteCursorAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public NoteCursorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent, false);

        return new NoteCursorHolder(view);
    }

    @SuppressLint("Range")
    @Override
    public void onBindViewHolder(@NonNull NoteCursorHolder holder, int position) {

        if (cursor.moveToPosition(position)) {
            holder.headerView.setText(cursor.getString(cursor.getColumnIndex(DBManager.HEADER)));
            holder.textView.setText(cursor.getString(cursor.getColumnIndex(DBManager.TEXT)));
           /* holder.idView.setText(cursor.getInt(cursor.getColumnIndex(DBManager.ID)));*/
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    static class NoteCursorHolder extends RecyclerView.ViewHolder {

        TextView headerView;
        TextView textView;
        /*TextView idView;*/

        public NoteCursorHolder(@NonNull View itemView) {
            super(itemView);

            headerView = itemView.findViewById(R.id.textView);
            textView = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(view -> {
                /*Snackbar make = Snackbar.make(view, headerView.getText(), BaseTransientBottomBar.LENGTH_INDEFINITE);
                make.setAction("X", view1 -> {
                    make.dismiss();
                });
                make.show();*/
                Context context = itemView.getContext();
                Intent intent = new Intent(context, NoteActivity.class);
                intent.putExtra("header", headerView.getText());
                intent.putExtra("text", textView.getText());
             //   intent.putExtra("id", idView.getText());
                context.startActivity(intent);

                //((Activity)context).finish(); обычно так не делаеться , но так можно
            });
        }
    }
}
