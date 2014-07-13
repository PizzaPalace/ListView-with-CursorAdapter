package com.example.cursoradaptertest;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
    
	ListView listview; 
	DatabaseTools database; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		database = new DatabaseTools(getApplicationContext(),null,null,1);
		
		//ArrayList<String> result = database.getAllData();
		Cursor cursor = database.getAllData();
		 
		listview = (ListView) findViewById(R.id.list_view);
	    SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.layout_file,cursor,new String[]{"candy"},new int[]{R.id.label},SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listview.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void saveButton(View view){
		
		EditText editText = (EditText) findViewById(R.id.edit_text);
		String data = editText.getText().toString();
		
		database.insertData(data);
		Log.v("database",database.getAllData().toString());
		
	    SimpleCursorAdapter adapter = (SimpleCursorAdapter) listview.getAdapter();
	    adapter.changeCursor(database.getAllData());
	
	}
	

}
