package com.example.data.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class NotesDbContext extends SQLiteOpenHelper  {

	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "NotesDB";
	
	public NotesDbContext(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public NotesDbContext(Context context){
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String create_query = "CREATE TABLE Notes " +
		"(NoteID INTEGER PRIMARY KEY AUTOINCREMENT, " + 
		"Subject TEXT, " + 
		" Note TEXT)";
		db.execSQL(create_query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Notes"); 
        this.onCreate(db);
	}
	
	public SQLiteDatabase getWDbContext(){
		 return this.getWritableDatabase();
	}

	public SQLiteDatabase getRDbContext(){
		return this.getReadableDatabase();
	}
}
