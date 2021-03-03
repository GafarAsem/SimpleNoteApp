package com.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.adapters.AdapterNotes;
import com.app.adapters.HelpFireBase;
import com.app.classes.DataBase;
import com.app.classes.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements AdapterNotes.onClickNotes {

    ImageView iconView;
    TextView  textView;
    androidx.appcompat.widget.SearchView searchView;
    RecyclerView recyclerView;
    FloatingActionButton fab;

    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    DataBase db;

    ArrayList<Note> Notes;
    AdapterNotes AN;
    ViewGroup viewGroup ;
    View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        iconView=findViewById(R.id.main_imagView);
        textView=findViewById(R.id.main_textView);
        searchView=findViewById(R.id.main_searchView);
        recyclerView=findViewById(R.id.main_recyclerView);
        fab=findViewById(R.id.main_floatingActionButton);

        viewGroup = findViewById(android.R.id.content);
        dialogView = LayoutInflater.from(this).inflate(R.layout.note_dialog, viewGroup, false);

        fab.setOnClickListener(v -> {
            startDialogAddNote();
        });

        db=DataBase.getInstance(this);
        Notes=db.getAllNotes();
        Collections.reverse(Notes);
        AN=new AdapterNotes(Notes,this);
        setLayout();




    }

    private void setLayout() {
        recyclerView=findViewById(R.id.main_recyclerView);
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        );
        recyclerView.smoothScrollToPosition(0);
        try{
            recyclerView.setAdapter(AN);
        }catch (Exception e){
            int i=0;
        }



    }

    private void startDialogAddNote() {


        builder = new AlertDialog.Builder(MainActivity.this);

        builder.setView(dialogView);
        builder.setCancelable(true);
        builder.setNegativeButtonIcon(new ColorDrawable(Color.TRANSPARENT) );
        builder.setNegativeButton("cancel", (dialog, which) -> {
        });
        builder.setOnCancelListener(dialog -> saveNewNote());
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

    }
    private void startDialogAddNote(Note note) {


        builder = new AlertDialog.Builder(MainActivity.this);

        builder.setView(dialogView);
        builder.setCancelable(true);
        builder.setNegativeButtonIcon(new ColorDrawable(Color.TRANSPARENT) );
        builder.setNegativeButton("cancel", (dialog, which) -> {
        });
        builder.setOnCancelListener(dialog -> saveNewNote(note));
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        EditText titleText= dialogView.findViewById(R.id.dialog_Title);
        EditText noteTitle= dialogView.findViewById(R.id.dialog_Note);

        titleText.setText(note.getTitle());
        noteTitle.setText(note.getNote());

    }

    public void saveNewNote() {

        EditText titleText= dialogView.findViewById(R.id.dialog_Title);
        EditText noteTitle= dialogView.findViewById(R.id.dialog_Note);



        Note note=new Note(titleText.getText().toString(),noteTitle.getText().toString());
        Notes.add(0,note);
        AN.notifyDataSetChanged();
        setLayout();
        note.saveNoteFire(this);
        note.saveNoteSQL();

        viewGroup = findViewById(android.R.id.content);
        dialogView = LayoutInflater.from(this).inflate(R.layout.note_dialog, viewGroup, false);


    }
    public void saveNewNote(Note Note){
        EditText titleText= dialogView.findViewById(R.id.dialog_Title);
        EditText noteTitle= dialogView.findViewById(R.id.dialog_Note);
        Note.setTitle(titleText.getText().toString());
        Note.setNote(noteTitle.getText().toString());


        AN.notifyDataSetChanged();
        setLayout();
        HelpFireBase.updateValue(Note);
        db.updateNote(Note.getId(),Note.getTitle(),Note.getNote());

        viewGroup = findViewById(android.R.id.content);
        dialogView = LayoutInflater.from(this).inflate(R.layout.note_dialog, viewGroup, false);

    }

    public void onClickAdd(View v){

       alertDialog.cancel();

    }

    @Override
    public void onClickNote(int position) {

        Note note=Notes.get(position);
        startDialogAddNote( note);





    }
}