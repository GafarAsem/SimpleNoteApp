package com.app.classes;

import android.content.Context;

import com.app.adapters.HelpFireBase;

import java.util.UUID;

public class Note implements NoteAction {

    protected String id,title,note;
    protected boolean saveFire;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(String note) {
        this.note = note;
    }

    static DataBase db=DataBase.getInstance(null);

    public Note(String title, String note) {
        this.id= UUID.randomUUID().toString();
        this.title = title;
        this.note = note;
        saveFire=false;
    }

    public Note(String id, String title, String note) {
        this.id = id;
        this.title = title;
        this.note = note;
    }

    public boolean isSaveFire() {
        return saveFire;
    }

    public void setSaveFire(boolean saveFire) {
        this.saveFire = saveFire;
    }

    public static Note getNote(String id) {
        return new Note("","");
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    @Override
    public void removeNote() {

    }

    @Override
    public boolean saveNoteSQL() {

        boolean save=db.insertNote(id,title,note, String.valueOf(saveFire));
        return save;

    }

    @Override
    public void saveNoteFire(Context context) {
        HelpFireBase.addValue(this,context);
    }

    @Override
    public void updateNote(String newTitle, String newNote) {

    }



















}
