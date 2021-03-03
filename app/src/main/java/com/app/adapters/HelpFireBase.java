package com.app.adapters;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;

import com.app.classes.Note;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

import static androidx.core.content.ContextCompat.getSystemService;


public class HelpFireBase {

    static  DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("notes");


    public static void addValue(Note note,Context context){




            Thread thread = new Thread() {
                @Override
                public void run() {

                    note.setSaveFire(true);
                    Task<Void> query = databaseReference.push().setValue(note);

                }
            };
            thread.start();




    }

    public static void updateValue(Note note){




        Thread thread = new Thread() {
            @Override
            public void run() {

                Query query = databaseReference.orderByChild("id").equalTo(note.getId());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            for(DataSnapshot dataSnapshot:snapshot.getChildren())
                                dataSnapshot.getRef().setValue(note);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        };
        thread.start();




    }






}
