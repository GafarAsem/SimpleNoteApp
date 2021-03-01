package com.app.classes;

public interface NoteAction {


    public void removeNote();
    public boolean saveNoteSQL();
    public boolean saveNoteFire();
    public void updateNote(String newTitle,String newNote);



}
