package com.app.classes;

import android.content.Context;

public interface NoteAction {


    public void removeNote();
    public boolean saveNoteSQL();
    public void saveNoteFire(Context context);
    public void updateNote(String newTitle,String newNote);



}
