package com.example.util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.baywatchnotes.R;
import com.example.entities.Note;

public class NoteAdapter extends ArrayAdapter<Note> {

	private List<Note> _notes;
	private Context _context;
	public NoteAdapter(List<Note> notes, Context ctx) {
		super(ctx, R.layout.note_layout, notes);
		_notes = notes;
		_context = ctx;
	}
	
	public int getCount() {
		return _notes.size();
	}

	public Note getItem(int position) {
		return _notes.get(position);
	}

	public long getItemId(int position) {
		return _notes.get(position).hashCode();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		// get a holder object (from the view, or a new one). this comment is actually mine, alvinin.
		NoteHolder holder = new NoteHolder();

		// First let's verify the convertView is not null -- that one is not!
		if (convertView == null) {
			// This a new view we inflate the new layout -- neither is this other one.
			LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.note_layout, null);
			// Now we can fill the layout with the right values
			TextView txtSubject = (TextView) v.findViewById(R.id.txtSubject);
			TextView txtNote = (TextView) v.findViewById(R.id.txtNote);


			holder.Subject = txtSubject;
			holder.Note = txtNote;

			v.setTag(holder);
		}
		else 
			holder = (NoteHolder) v.getTag();

		Note n = _notes.get(position);
		holder.Subject.setText(n.getSubject());
		holder.Note.setText(getNoteForDisplay(n.getNote()));
		

		return v;
	}

	private String getNoteForDisplay(CharSequence note){
		if(note.length() > 8){
			String fragment = note.subSequence(0,7).toString() + "...";
			return fragment;
		}
		return note.toString();
	}
	
	// holder pattern: la forma mas rapida de bregar listviews de acuerdo con google. 
	// when I say google I mean the company, not the search engine ;)
	private static class NoteHolder{
		public TextView Subject;
		public TextView Note;
	}
}
