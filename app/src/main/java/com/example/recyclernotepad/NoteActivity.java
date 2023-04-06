package com.example.recyclernotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.recyclernotepad.databinding.ActivityNoteBinding;

public class NoteActivity extends AppCompatActivity {
    ActivityNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteBinding.inflate(getLayoutInflater());
     //   setContentView(R.layout.activity_note);

        setContentView(binding.getRoot());

        Intent intent = getIntent();
      //  DBManager manager = new DBManager(this);
     //   Note note = manager.findById(intent.getIntExtra("id",0));
        binding.headerNote.setText(intent.getStringExtra("header"));
        binding.textNote.setText(intent.getStringExtra("text"));
    }
}