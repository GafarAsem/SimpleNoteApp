    package com.app.adapters;


import android.content.Context;

import androidx.annotation.NonNull;

import com.app.MainActivity;
import com.app.classes.Note;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


    public class HelpFireBase {

    static  DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Note");

    static ArrayList<Note> Notes;

    public static void addValue(Note note){




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


    public static void setListNote(){
        Notes=new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                       Notes.add(dataSnapshot.getValue(Note.class));

                    }

                    ArrayList<String> note1=getList(Notes);
                    ArrayList<String> note2=getList(MainActivity.Notes);
                    ArrayList<String> notesID= mirageNotes(note1,note2);
                    for(String noteID:notesID){
                        if(!note1.contains(noteID))
                        {
                            Note note=MainActivity.Notes.get(note2.indexOf(noteID));
                            note.saveNoteFire();
                        }else
                        if(!note2.contains(noteID))
                        {

                            Note note=Notes.get(note1.indexOf(noteID));
                            note.saveNoteSQL();

                        }

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public static ArrayList<String> mirageNotes(ArrayList<String> note1, ArrayList<String> note2) {
        ArrayList<String> notesID=new ArrayList<>();


        notesID.addAll(note1);
        notesID.addAll(note2);

        Set<String> notes=new HashSet<>(notesID);
        notesID.clear();
        notesID.addAll(notes);

        return notesID;
    }

    public static ArrayList<String> getList(ArrayList<Note> Notes){
        ArrayList<String> strings=new ArrayList<>();
        for(Note note:Notes){
            strings.add(note.getId());
        }
        return strings;
    }

}
