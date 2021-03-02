package com.app.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.MainActivity;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="NoteDataBase.db";
    public static final String DATABASE_TABLE="noteTable";
    public static final String DATABASE_CO1="ID";
    public static final String DATABASE_CO2="title";
    public static final String DATABASE_CO3="note";
    public static final String DATABASE_CO4="saveFire";


    private static volatile DataBase instance;

    public static synchronized DataBase getInstance(@Nullable Context context){
        if(instance==null){
            instance=new DataBase(context);
        }
        return instance;
    }

    private DataBase(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+DATABASE_TABLE+
                " ( "+DATABASE_CO1+
                " TEXT PRIMARY KEY , "+DATABASE_CO2 +
                " TEXT, "+DATABASE_CO3 +
                " TEXT, "+DATABASE_CO4 +
                " TEXT )");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
    }


    public boolean insertNote(String ID, String title, String note, String saveSQL ) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();


        contentValues.put(DATABASE_CO1,ID);
        contentValues.put(DATABASE_CO2,title);
        contentValues.put(DATABASE_CO3,note);
        contentValues.put(DATABASE_CO4,saveSQL);




        long result=db.insert(DATABASE_TABLE,null,contentValues);

        if (result==-1)
            return false;
        else
            return true;


    }
    public boolean updateNote(String ID, String title, String note, String saveSQL ) {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();



        contentValues.put(DATABASE_CO2,title);
        contentValues.put(DATABASE_CO3,note);
        contentValues.put(DATABASE_CO4,saveSQL);



        long result=db.update(DATABASE_TABLE,contentValues,DATABASE_CO1+" =?",new String[]{ID});

        if (result==-1)
            return false;
        else
            return true;


    }



    public ArrayList<Note> getAllNotes(){

        ArrayList<Note> ArrayData = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor res =db.rawQuery("SELECT * FROM "+DATABASE_TABLE,null);

        if (res.moveToFirst()) {
            while (!res.isAfterLast()) {
                Note note = new Note(res.getString(res.getColumnIndex(DATABASE_CO1)),
                                      res.getString(res.getColumnIndex(DATABASE_CO2)),
                                        res.getString(res.getColumnIndex(DATABASE_CO3)));

                ArrayData.add(note);
                res.moveToNext();
            }
        }
        return ArrayData;



    }



}
