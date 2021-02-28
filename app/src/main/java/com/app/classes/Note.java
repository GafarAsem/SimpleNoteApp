package com.app.classes;

import java.util.UUID;

public class Note implements NoteAction {

    protected String id,title,note;

    public Note(String title, String note) {
        this.id= UUID.randomUUID().toString();
        this.title = title;
        this.note = note;
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
    public void saveNoteSQL() {

    }

    @Override
    public void saveNoteFire() {

    }

    @Override
    public void updateNote(String newTitle, String newNote) {

    }
}
