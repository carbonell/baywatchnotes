package com.example.entities;

public class Note {
	private int noteID;
	private String _subject;
	private String _note;
	public int getNoteID() {
		return noteID;
	}
	
	public Note(){}
	public Note(String subject, String note){
		_subject = subject;
		_note = note;
	}
	public void setNoteID(int noteID) {
		this.noteID = noteID;
	}
	public String getSubject() {
		return _subject;
	}
	public void setSubject(String subject) {
		this._subject = subject;
	}
	public String getNote() {
		return _note;
	}
	public void setNote(String note) {
		this._note = note;
	}

}
