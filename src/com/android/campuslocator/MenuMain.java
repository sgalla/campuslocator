package com.android.campuslocator;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.android.campuslocator.R;

public class MenuMain extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_main_activity);
		
		 ActionBar ab = getActionBar(); 
		 ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFFFFF"));    
		           ab.setBackgroundDrawable(colorDrawable);		           
		           ab.setTitle(Html.fromHtml("<font color=\"#990000\">Main Menu<center>"));
		           
         
    }
		
	public void openmap(View view) {
		//Animation animMap = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        //view.startAnimation(animMap);
       	   
        Intent i = new Intent(this , MainActivity.class);
	    startActivity(i);
       	  };
	
	
	public void openabout(View view) {
		//Animation animAbout = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        //view.startAnimation(animAbout);
	    Intent i = new Intent(this , HelpPage.class);
	    startActivity(i);
	} 
	
	public void openevents(View view) {
		//Animation animEvents = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        //view.startAnimation(animEvents);
	    Intent i = new Intent(this , MainActivity.class);
	    startActivity(i);
	} 
	
	public void openschedule(View view) {
		//Animation animSchedule = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        //view.startAnimation(animSchedule);
	    Intent i = new Intent(this , MainActivity.class);
	    startActivity(i);
	} 
	
	public void openMyUNM (View view) {
		//Animation animMyUNM = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        //view.startAnimation(animMyUNM);
        goToUrl ( "https://my.unm.edu/cp/home/displaylogin");
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

}