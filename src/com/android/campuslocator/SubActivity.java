package com.android.campuslocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.android.campuslocator.R;

public class SubActivity extends Activity{
	private TXTAdapter adapter;
	private ListView listView;
	private EditText inputSearch;

	public boolean showPopup(MenuItem item) {
	    PopupMenu popup = new PopupMenu(this, findViewById(R.id.dropdown));
	    MenuInflater inflater = popup.getMenuInflater();
	    inflater.inflate(R.menu.items, popup.getMenu());
	    popup.show();
		return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.sub_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
		case R.id.dropdown:			
			return true;
		case R.id.help:
			Intent i = new Intent(this, HelpPage.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    setContentView(R.layout.sub_activity);
	    
	    RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
	    radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
	    {
	    	public void onCheckedChanged(RadioGroup group, int checkedId)
	    	{
	    		switch(checkedId)
	    		{
	    		case R.id.radioAlphabetically:
	    			if (!adapter.equals(null))
	    				adapter.sortAlphabetically();
	    			break;
	    		case R.id.radioNumerically:
	    			if (!adapter.equals(null))
	    				adapter.sortNumerically();
	    			break;
	    		}
	    	}
	    });
	   
	 // get id of the listview we are using (only one)
	    listView = (ListView)findViewById(R.id.listView1);
	    
	    //get search
	    inputSearch = (EditText) findViewById(R.id.inputSearch);
	    
	    inputSearch.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				adapter.search(s);
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    // -1 value is dummy, constructor of TXTAdapter required it
	    adapter = new TXTAdapter(this, -1);
	    listView.setAdapter(adapter);
	    
	    // on item click what do we want to do?? 
	    /* in theory we want to send the lat and long coordinates to the 
	     * map methods to display the location on the map.
	     */
	    listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				 
				// TODO Auto-generated method stub
				// set up an intent to pass data when item from row is clicked
				
				Intent intent = new Intent(SubActivity.this, MainActivity.class);
				
				//intent.putExtra("latitude", AssetsReader.getLatitude());
				//intent.putExtra("longitude", AssetsReader.getLongitude());
				//setResult(RESULT_OK, intent);
				startActivityForResult(intent, 0);

			}
			
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    });  
	}

}