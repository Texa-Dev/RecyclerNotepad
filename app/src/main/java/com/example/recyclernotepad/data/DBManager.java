package com.example.recyclernotepad.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "notes";
    public static final String TAB = "notes";
    public static final String ID = "_id";
    public static final String HEADER = "header";
    public static final String TEXT = "text";

    private SQLiteDatabase db;

    public DBManager(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        this.createTab();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void createTab() {
        db = getWritableDatabase();
        String sql = "create table if not exists %s(" +
                "%s int auto increment primary key, " +
                "%s text, " +
                "%s text" +
                ")";
        sql = String.format(sql, TAB, ID, HEADER, TEXT);
        db.execSQL(sql);
    }

    public void dropTab() {
        db = getWritableDatabase();
        String sql = "drop table if exists " + TAB;
        db.execSQL(sql);
    }

    public void save(Note note) {
        db = getWritableDatabase();
        String sql = "insert into %s(%s,%s) values('%s','%s')";
        sql = String.format(sql, TAB,  HEADER, TEXT,
                note.getHeader(), note.getText());
        db.execSQL(sql);
    }

    @SuppressLint("Range")
    public void findAll(){
        db=getReadableDatabase();
        String sql = "select * from " + TAB;
        Cursor cursor = db.rawQuery(sql, new String[]{});
        /*while (cursor.moveToNext()){
            for (int j = 0; j < 4; j++) {
                Log.d("FF", "" + cursor.getString(j));
            }
            Log.d("FF", "");
        }*/
        Note note;
        while (cursor.moveToNext()){
           /* note = new Note(
                    cursor.getInt(cursor.getColumnIndex(ID)),
                    //...
            );
            System.out.println(note);*/
        }
    }
    @SuppressLint("Range")
    public Cursor findAllCursor(){
        db=getReadableDatabase();
        String sql = "select * from " + TAB;
        return db.rawQuery(sql, new String[]{});

    }
}















