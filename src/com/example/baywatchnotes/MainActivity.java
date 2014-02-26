package com.example.baywatchnotes;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.entities.Note;
import com.example.util.NoteAdapter;

public class MainActivity extends ListActivity {

	public static int NEW_NOTE_CODE = 0;
	private List<Note> _notes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListViewFooter();
		addListViewAdapter();
		setClickEvent();
	}

	private void addListViewAdapter(){
		List<Note> sampleNotes = getSampleNotes(); 
		_notes = sampleNotes;
		NoteAdapter adapter = new NoteAdapter(sampleNotes, this);
		getListView().setAdapter(adapter);		
	}
	
	private void addListViewFooter()
	{
		LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View footer = inflater.inflate(R.layout.footer_button, null);
		getListView().addFooterView(footer);		
	}
	
	private void setClickEvent(){
		Button b = (Button)findViewById(R.id.add_button);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, NewNoteActivity.class);
				startActivityForResult(i, NEW_NOTE_CODE);	
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public List<Note> getSampleNotes(){
		List<Note> notes = new ArrayList<Note>();
		Note n1 = new Note("test", "please, please, let us stay in the competition");
		Note n2 = new Note("test2", "This goes, to fucking google play, bitches");
		notes.add(n1);
		notes.add(n2);
		return notes;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		  if (requestCode == NEW_NOTE_CODE) {

		     if(resultCode == RESULT_OK){      
		         String subject = data.getStringExtra("subject");
		         String note = data.getStringExtra("note");
		         Note newNote = new Note(subject, note);
		         _notes.add(newNote);
		     }
		  }
		}

}
