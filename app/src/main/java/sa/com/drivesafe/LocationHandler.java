package sa.com.drivesafe;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by kaytee on 5/21/15.
 */
public class LocationHandler {
    LocationManager mLocationManager;
    LocationListener mLocationListener;

    public LocationHandler() {
    }

    public void setup(Object locationManager) {
        setupLocationManager(locationManager);
        setupLocationListener();
    }
    private void setupLocationManager(Object locationManager) {
        mLocationManager = (LocationManager) locationManager;
    }
    private void setupLocationListener() {
        new mLocationListener() {
            double prevLat = 0, prevLong = 0, prevTime = 0, timer = 0;
            public void onLocationChanged(Location loc) {
                double param1 = loc.getLatitude() - prevLat;
                double param2 = loc.getLongitude() - prevLong;
                timer = System.currentTimeMillis() - prevTime;
                loc.setSpeed((float) ((Math.sqrt((param1 * param1) + (param2 * param2)) / timer)));
                //loc.setSpeed(15); (use to emulate movement for testing)
                prevLat = loc.getLatitude();
                prevLong = loc.getLongitude();
                prevTime = System.currentTimeMillis();
                //TODO: insert of speed is greater than selected speed from the UI,
                //TODO: and an SMS is received, send an SMS back.
                //TODO: if(loc.getSpeed() > thresholdSpeed)
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}
            public void onProviderEnabled(String provider) {}
            public void onProviderDisabled(String provider) {}
        };
    }
}
