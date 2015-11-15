package com.praca_inz;

import android.location.Location;

/**
 * Created by KamilH on 2015-10-22.
 */
public class CalculateTrack{
    private double distance = 0, avgSpeed = 0;

    // konstruktor w którym obliczam dystans od początku pomiaru, czyli cały dystans + dystans pomiędzy ostatnimi dwoma pomiarami
    // obliczam też prędkość, czyli cały dystans / cały czas
    public CalculateTrack(Location lastLocation, Location actualLocation, double time, double allDistance){
        this.distance = allDistance + calculateDistance(lastLocation, actualLocation);
        this.avgSpeed = calculateSpeed(allDistance, time);
    }

    // funkcja zwracająca odległość między punktami, w metrach
    private double calculateDistance(Location location1, Location location2)
    {
        double lat1 = location1.getLatitude();
        double lon1 = location1.getLongitude();
        double lat2 = location2.getLatitude();
        double lon2 = location2.getLongitude();
        double Radius = 6371;
        double dLat = (lat2-lat1)*Math.PI/180;
        double dLon = (lon2-lon1)*Math.PI/180;
        lat1 = lat1*Math.PI/180;
        lat2 = lat2*Math.PI/180;

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = Radius * c * 1000;

        //Log.v("DISTANCE", "result[0]: " + String.valueOf(d));
        return distance;
    }

    // obliczanie prędkości w m/s i konwersja do km/h
    private double calculateSpeed(double distance, double time){
        double speed = distance / time;
        //Log.v("DISTANCE", "m/s: " + speed + " " + "km/h" + speed * UnitConversions.MS_TO_KMH);
        return speed * UnitConversions.MS_TO_KMH;
    }

    public double getDistance() {
        return distance;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }
}
