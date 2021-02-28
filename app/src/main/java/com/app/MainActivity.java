package com.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.classes.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ImageView iconView;
    TextView  textView;
    androidx.appcompat.widget.SearchView searchView;
    RecyclerView recyclerView;
    FloatingActionButton fab;


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

        fab.setOnClickListener(v -> {

            startDialogAddNote();

        });





    }

    private void startDialogAddNote() {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);

        View dialogView = LayoutInflater.from(this).inflate(R.layout.note_dialog, viewGroup, false);
        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);

        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                EditText titleText=findViewById(R.id.dialog_Title);
                EditText noteText=findViewById(R.id.dialog_Note);

                Note note=new Note(titleText.getText().toString(),noteText.getText().toString());
                note.saveNoteSQL();

                return true;
            }
        });

        alertDialog.show();

    }
}