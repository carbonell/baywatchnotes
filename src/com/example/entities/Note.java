package com.example.entities;

public class Note {
	private int _noteID;
	private String _subject;
	private String _note;
	public int getNoteID() {
		return _noteID;
	}
	
	public Note(){}
	public Note(String subject, String note){
		_subject = subject;
		_note = note;
	}
	
	public Note(int noteID, String subject, String note){
		_noteID = noteID;
		_subject = subject;
		_note = note;
	}
	
	public void setNoteID(int noteID) {
		this._noteID = noteID;
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
