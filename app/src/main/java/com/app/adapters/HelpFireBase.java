package com.app.adapters;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.app.classes.Note;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import static androidx.core.content.ContextCompat.getSystemService;

public class HelpFireBase {

    static  DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("notes");


    public static boolean addValue(Note note){


        if( hostAvailable()) {
            note.setSaveFire(true);
            Thread thread = new Thread() {
                @Override
                public void run() {

                    databaseReference.push().setValue(note);
                }
            };
            thread.start();
            return true;
        }
        else return false;


    }

















    public static boolean hostAvailable() {

        return true;
    }


}
