package com.flamingo.currentlocation;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private LocationManager lm = null;
	
	TextView lon;
	TextView lat;
	TextView providers; 
	LocationListener locationListerner = new LocationListener() {

		@Override
		public void onLocationChanged(Location location) {
			lon.setText(String.valueOf(location.getLongitude()));
			lat.setText(String.valueOf(location.getLatitude()));
			
		}

		@Override
		public void onProviderDisabled(String provider) {
	    	List<String> list = lm.getProviders(true);
	    	StringBuffer s = new StringBuffer();
	    	for(String v: list) {
	    		s.append(v+"\n");
	    	}
	    	providers.setText(s.toString());
			
		}

		@Override
		public void onProviderEnabled(String provider) {
	    	List<String> list = lm.getProviders(true);
	    	StringBuffer s = new StringBuffer();
	    	for(String v: list) {
	    		s.append(v+"\n");
	    	}
	    	providers.setText(s.toString());
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lon  = (TextView)findViewById(R.id.longvalue);
        lat = (TextView)findViewById(R.id.latvalue);
        providers  = (TextView)findViewById(R.id.providervalue);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	Criteria c = new Criteria();
    	c.setAccuracy(Criteria.ACCURACY_FINE);
    	c.setCostAllowed(true);
    	c.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
    	lm.requestLocationUpdates(10000, 1, c, locationListerner, Looper.getMainLooper());
    	List<String> list = lm.getProviders(true);
    	StringBuffer s = new StringBuffer();
    	for(String v: list) {
    		s.append(v+"\n");
    	}
    	providers.setText(s.toString());
    	if(list.size() == 1)
    		Toast.makeText(this, "Enable location updates", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
    	super.onPause();
    	lm.removeUpdates(locationListerner);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
