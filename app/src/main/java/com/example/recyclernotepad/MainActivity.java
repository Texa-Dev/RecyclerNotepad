package com.example.recyclernotepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.recyclernotepad.adapters.NoteAdapter;
import com.example.recyclernotepad.adapters.NoteCursorAdapter;
import com.example.recyclernotepad.data.DBManager;
import com.example.recyclernotepad.data.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.notesRecycler);

        List<Note> list = List.of(
                new Note(1, "h1", "t1"),
                new Note(2, "h2", "t2"),
                new Note(3, "h3", "t3")
        );

        /*NoteAdapter adapter = new NoteAdapter(this,list);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);*/

        /*recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                Log.d("FF", "EptaOnIntercept" + e);
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                Log.d("FF", "DISALLOWEptaOnIntercept" + e);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/

        DBManager manager = new DBManager(this);
        for (Note note : list) {
            manager.save(note);
        }

        Cursor allCursor = manager.findAllCursor();
        NoteCursorAdapter noteCursorAdapter = new NoteCursorAdapter(allCursor);

        recyclerView.setAdapter(noteCursorAdapter);
    }
}