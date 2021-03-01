package com.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.classes.DataBase;
import com.app.classes.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ImageView iconView;
    TextView  textView;
    androidx.appcompat.widget.SearchView searchView;
    RecyclerView recyclerView;
    FloatingActionButton fab;

    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    DataBase db;

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




    }

    private void startDialogAddNote() {


        builder = new AlertDialog.Builder(MainActivity.this);

        builder.setView(dialogView);
        builder.setCancelable(true);
        builder.setNegativeButtonIcon(new ColorDrawable(Color.TRANSPARENT) );
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

                saveNewNote();

            }
        });
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

    }

    public void saveNewNote() {

        EditText titleText= dialogView.findViewById(R.id.dialog_Title);
        EditText noteTitle= dialogView.findViewById(R.id.dialog_Note);

        Note note=new Note(titleText.getText().toString(),noteTitle.getText().toString());

        if(note.saveNoteFire())
        {
            note.setSaveFire(true);
        }
        if(note.saveNoteSQL()){
            viewGroup = findViewById(android.R.id.content);
            dialogView = LayoutInflater.from(this).inflate(R.layout.note_dialog, viewGroup, false);
        }


    }

    public void onClickAdd(View v){

       alertDialog.cancel();

    }
}