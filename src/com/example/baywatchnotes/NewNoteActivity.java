package com.example.baywatchnotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.entities.Note;

public class NewNoteActivity extends Activity {
	
	private TextView txtSubject;
	private TextView txtNote;
	private int noteID;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_note);
		getReferences();
		setEvents();
		Intent i = getIntent();
		if(i.getIntExtra("noteID", 0) != 0)
			setNoteInView(i);
		
		if(savedInstanceState != null)
			restoreActivityState(savedInstanceState);
	}
	
	private void restoreActivityState(Bundle savedState){
		txtSubject.setText(savedState.getString("subject"));
		txtNote.setText(savedState.getString("note"));
	}
	
	private void setNoteInView(Intent i){
		txtSubject.setText(i.getStringExtra("subject"));
		txtNote.setText(i.getStringExtra("note"));
		noteID = i.getIntExtra("noteID", 0);
	}
	private void getReferences(){
		txtSubject = (TextView) findViewById(R.id.txtSubject);
		txtNote = (TextView) findViewById(R.id.txtNote);
	}
	
	private void setEvents(){
		setSubmitEvent();
		setCancelEvent();
	}
	
	private void setSubmitEvent(){
		Button b = (Button) findViewById(R.id.submit);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent returnIntent = new Intent();
				Note note = getNoteFromView();
				
				if(!noteIsValid(note)){
					showInvalidMessage("Your note should have a subject and the actual note!");
				
				}else{
					returnIntent.putExtra("subject", note.getSubject());
					
					returnIntent.putExtra("note", note.getNote());
					if(noteID != 0)
						returnIntent.putExtra("noteID", noteID);
					setResult(RESULT_OK,returnIntent);     
					finish();					
				}
				
			}
		});
	}
	
	private boolean noteIsValid(Note note){
		Log.w("App", note.getNote() + " " + note.getSubject() + "!" + note.getNote().length() + " " + note.getSubject().length());
		if(note.getNote() == null || 
				note.getNote().trim().equals("") ||
				note.getSubject() == null || 
				note.getSubject().trim().equals(""))
			return false;
		return true;
	}
	
	private void showInvalidMessage(String message){
		new AlertDialog.Builder(this)
	    .setTitle("Can't proceed with operation")
	    .setMessage(message)
	    .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        }
	     }).show();
		
	}
	
	private void setCancelEvent(){
		Button b  = (Button) findViewById(R.id.cancel);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	private Note getNoteFromView(){
		Note note = new Note(txtSubject.getText().toString(), txtNote.getText().toString());
		return note;
	}	
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putString("subject", txtSubject.getText().toString());
		savedInstanceState.putString("note", txtNote.getText().toString());
		savedInstanceState.putInt("noteID", noteID);
	}
}
