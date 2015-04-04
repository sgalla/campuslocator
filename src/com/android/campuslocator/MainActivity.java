package com.android.campuslocator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.campuslocator.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity{

	private SupportMapFragment mapFragment;
	private GoogleMap map;
	public Location loc;
	Marker location;
	String latitude;
	String longitude;
	double lat, longi;
	Intent intent = getIntent();

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
		case R.id.menu_button:   //********//
			if (!isNetworkAvailable())
			{
				NoNetworkConnectionDialogFragment dialog = new NoNetworkConnectionDialogFragment();
				dialog.show(getFragmentManager(), "dialog");
				return true;
			}
			else
			{
				Intent i = new Intent(this, SubActivity.class);
				startActivityForResult(i, 100);
				return true;
			}
		case R.id.help:        //********//
			Intent i2 = new Intent(this, HelpPage.class);
			startActivity(i2);
			return true;	
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == 0 && data != null)
		{
			latitude = data.getStringExtra("latitude");
			longitude = data.getStringExtra("longitude");
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
		map = mapFragment.getMap();
		map.getUiSettings().setZoomControlsEnabled(true);  //********//
		map.getUiSettings().setZoomGesturesEnabled(true);  //********//
		map.getUiSettings().setRotateGesturesEnabled(true); //********//
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);   //********//
		
		while (map.equals(null))
			map = mapFragment.getMap();
		    
		LocationManager locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		LatLng ece = new LatLng(35.083180, -106.624656);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(ece, 18));
		map.setInfoWindowAdapter(new CustomInfoWindowAdapter());
		Marker marker = map.addMarker(new MarkerOptions().position(ece).title("Electrical and Computer Engineering").snippet("EECE"));
		marker.showInfoWindow();
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, new LocationListener() {

			@Override
			public void onLocationChanged(Location arg0) {
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(
						new LatLng(arg0.getLatitude(), arg0
								.getLongitude()), 15));
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}
				
			});
		
		
		map.setMyLocationEnabled(true);
		if (mapFragment != null) {
			map = mapFragment.getMap();
			
			if (map != null) {
			}
			else {			
				Toast.makeText(this, "Map failed! Please restart your device"
						+ "or see our Help page", Toast.LENGTH_LONG).show();
			}
		} 
		else {
			Toast.makeText(this, "Map failed! Please restart your device"
						+ "or see our Help page", Toast.LENGTH_LONG).show();
		}	
	}

	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	public class NoNetworkConnectionDialogFragment extends DialogFragment {
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setMessage(R.string.no_connection)
	               .setPositiveButton((Integer) R.string.cancel, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       dialog.cancel();
	                   }
	               })
	               .setNegativeButton((Integer) R.string.try_again, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       dialog.dismiss();
	                       View view = findViewById(R.id.menu_button);
                    	   view.performClick(); 
	                   }
	               });
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	}
	
	public class CustomInfoWindowAdapter implements InfoWindowAdapter
	{
	    public CustomInfoWindowAdapter()
	    {
	    }

		@Override
		public View getInfoContents(Marker arg0) {
			
			View v  = getLayoutInflater().inflate(R.layout.custom_info_window, null);

	        ImageView markerIcon = (ImageView) v.findViewById(R.id.marker_icon);

	        TextView markerLabel = (TextView)v.findViewById(R.id.marker_label);

	        markerIcon.setImageResource(R.drawable.centennial);

	        markerLabel.setText("Electrical and Computer Engineering");

	        return v;
		}

		@Override
		public View getInfoWindow(Marker arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
