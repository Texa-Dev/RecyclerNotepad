package com.example.recyclernotepad;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.PermissionChecker;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
    final String TAG = "FF";

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
      /*  for (Note note : list) {
            manager.save(note);
        }*/
        // manager.deleteAll();

        Cursor allCursor = manager.findAllCursor();
        NoteCursorAdapter noteCursorAdapter = new NoteCursorAdapter(allCursor);

        recyclerView.setAdapter(noteCursorAdapter);

   /*     ActivityResultLauncher<String> resultLauncher = new ActivityResultLauncher<String>() {
            @Override
            public void launch(String input, @Nullable ActivityOptionsCompat options) {

                Log.d("FF", "launch: " + input);
            }

            @Override
            public void unregister() {

            }

            @NonNull
            @Override
            public ActivityResultContract<String, ?> getContract() {
                return null;
            }

        };
        resultLauncher.launch("Test");
        */

        //Первый способ
       /* ActivityResultLauncher<String> launcher = registerForActivityResult(
                new ActivityResultContract<String, String>() {
                    @NonNull
                    @Override
                    public Intent createIntent(@NonNull Context context, String s) {
                        return new Intent(context, NoteActivity.class);
                    }

                    @Override
                    public String parseResult(int status, @Nullable Intent intent) {
                       if(status==RESULT_OK){
                          return intent.getStringExtra("test");
                       }
                        Log.d("FF", "intent: " + status);
                        return null;
                    }
                }, new ActivityResultCallback<String>() {
                    @Override
                    public void onActivityResult(String result) {
                        Log.d("FF", "result: " + result);
                    }
                });

        launcher.launch("Test");*/

        //Второй вариант

        /*ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Log.d(TAG, "result: " + result.getData().getStringExtra("test"));
                    }
                }
        );
        launcher.launch(new Intent(this,NoteActivity.class));*/

        ActivityResultLauncher<String> launcher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                result -> {
                    Log.d(TAG, "result: " + (result));
                }
        );
        launcher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        Log.d(TAG, "result: " + (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE")== PackageManager.PERMISSION_GRANTED));
    }
}

