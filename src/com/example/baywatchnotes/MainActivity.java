package com.example.baywatchnotes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;

import com.example.entities.Note;
import com.example.util.NoteAdapter;

public class MainActivity extends ListActivity {

	public static int NEW_NOTE_CODE = 0;
	public static int EDIT_NOTE_CODE = 1;
	private List<Note> _notes;
	private NoteAdapter _adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListViewFooter();
		registerForContextMenu(getListView());
		addListViewAdapter();
		setClickNewNoteButton();
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater m = getMenuInflater();
		m.inflate(R.menu.context_menu, menu);
	}

	private void addListViewAdapter(){
		List<Note> sampleNotes = getSampleNotes(); 
		_notes = sampleNotes;
		_adapter = new NoteAdapter(sampleNotes, this);
		getListView().setAdapter(_adapter);		
	}
	
	private void addListViewFooter()
	{
		LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View footer = inflater.inflate(R.layout.footer_button, null);
		getListView().addFooterView(footer);		
	}
	
	private void setClickNewNoteButton(){
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
		Note n1 = new Note(1, "test", "please, please, let us stay in the competition");
		Note n2 = new Note(2, "test2", "This goes, to fucking google play, bitches");
		notes.add(n1);
		notes.add(n2);
		return notes;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		  if (requestCode == NEW_NOTE_CODE) {

		     if(resultCode == RESULT_OK){    
		    	 
		    	 Note note = getNoteFromIntent(data);
		         Log.w("App ", "new note: " + note.getSubject());
		    	 _notes.add(note);
				 _adapter.notifyDataSetChanged();
		     }
		  }
		  
		  if(requestCode == EDIT_NOTE_CODE){
			  if(resultCode == RESULT_OK){
		    	Note note = getNoteFromIntent(data);
		    	replaceNote(note);
				_adapter.notifyDataSetChanged();
			  }
		  }
	}

	private Note  findNote(int noteID){
		for(Note note : _notes){
			if(note.getNoteID() == noteID)
				return note;
		}
		return null;
	} 
	
	private void replaceNote(Note editedNote){
		Note n = null;
		for(int i = 0; i < _notes.size(); i++){
			n = _notes.get(i);
			if(editedNote.getNoteID() == n.getNoteID()){
				n.setSubject(editedNote.getSubject());
				n.setNote(editedNote.getNote());
			}
		}
		_adapter.notifyDataSetChanged();
	}
	private Note getNoteFromIntent(Intent i){
        String subject = i.getStringExtra("subject");
        String note = i.getStringExtra("note");
        int noteID = i.getIntExtra("noteID", 0);
        Note newNote = new Note(noteID, subject, note);
		return newNote;
	}
	
	@Override  
	   public boolean onContextItemSelected(MenuItem item) {  
        Log.w("App", "Enter onContextItemSelected ");
		
    	// consigue la informacion del item clicado.
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        // get item position from position attribute
        int position = (int) info.position; 

		switch(item.getItemId()){  

			case R.id.delete_item:  
              // remove from list
				removeItem(position);
				return true;
            case R.id.edit_item:
            	 editItem(position);
            	 return true;
	        }  
	        return super.onContextItemSelected(item);  
	   }
	
		private void removeItem(int position){
            _notes.remove(position);  
            _adapter.notifyDataSetChanged();  
		}
		
		private void editItem(int position){
			Note note = _notes.get(position);
			Intent i = new Intent(MainActivity.this, NewNoteActivity.class);
			i.putExtra("noteID", note.getNoteID());
			i.putExtra("subject", note.getSubject());
			i.putExtra("note", note.getNote());
			startActivityForResult(i, EDIT_NOTE_CODE);
		}
		
		public void onSaveInstanceState(Bundle savedInstanceState){
			super.onSaveInstanceState(savedInstanceState);
			HashMap map = new HashMap();
			map.put("noteList", _notes);
//			savedInstanceState.put;
		}
}


