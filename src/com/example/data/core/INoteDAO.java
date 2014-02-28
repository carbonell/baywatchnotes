package com.example.data.core;

import java.util.List;

import com.example.entities.Note;

public interface INoteDAO {
	Note addNote(Note newNote); 
	void editNote(Note editedNote);
	void deleteNote(int noteID);
	List<Note> getAll();
	Note getNoteById(int noteID);
}
