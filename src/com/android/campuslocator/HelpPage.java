package com.android.campuslocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.android.campuslocator.R;

public class HelpPage extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    setContentView(R.layout.help_activity);
	}

	public void openlists(View view) {
	    Intent i = new Intent(this , SubActivity.class);
	    startActivity(i);
	} 
	
}