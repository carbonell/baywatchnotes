package com.example.baywatchnotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.entities.Note;

public class NewNoteActivity extends Activity {
	
	private TextView txtSubject;
	private TextView txtNote;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getReferences();
		setContentView(R.layout.new_note);
		setEvents();
		
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
				// TODO Auto-generated method stub
				Intent returnIntent = new Intent();
				Note note = getNoteFromView();
				returnIntent.putExtra("subject", note.getSubject());
				returnIntent.putExtra("note", note.getNote());
				setResult(RESULT_OK,returnIntent);     
				finish();
			}
		});
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
		TextView txtSubject = (TextView) findViewById(R.id.txtSubject);
		TextView txtNote = (TextView) findViewById(R.id.txtNote);
		Note note = new Note(txtSubject.getText().toString(), txtNote.getText().toString());
		return note;
	}	
}
