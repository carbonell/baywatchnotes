package com.example.baywatchnotes;

import android.app.Activity;
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
		int idToLog = i.getIntExtra("noteID", 0); 
		
		Log.w("App", Integer.valueOf(idToLog).toString());
		Log.w("App", i.getStringExtra("subject"));
		Log.w("App", i.getStringExtra("note"));
		
		if(i.getIntExtra("noteID", 0) != 0)
			setNoteInView(i);
	}
	
	private void setNoteInView(Intent i){
		txtSubject.setText(i.getStringExtra("subject"));
		txtNote.setText(i.getStringExtra("note"));
		noteID = i.getIntExtra("noteID", 0);
//		if(txtSubject == null)
//		{
//			Log.w(";)", ";)");
//		}
//		
//		if(txtNote == null)
//		{
//			Log.w(";)", ";)");
//		}
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
				if(noteID != 0)
				returnIntent.putExtra("noteID", noteID);
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
		Note note = new Note(txtSubject.getText().toString(), txtNote.getText().toString());
		return note;
	}	
}
