package com.example.testdistancecalculator;

public class Canculations {
    private static double EARTH_RADIUS = 6371;
    public static double toRadian(double degrees)
    {
        return (degrees * Math.PI) / 180.0d;
    }
    public static double distanceСalculationCrowfight(double lat1, double lng1, double lat2, double lng2)
    {
        double dLat = toRadian(lat2 - lat1);
        double dLng = toRadian(lng2 - lng1);

        double num = Math.pow(Math.sin(dLat/2), 2)  +
                Math.cos(toRadian(lat1)) * Math.cos(toRadian(lat2)) *
                        Math.pow(Math.sin(dLng/2), 2);

        double c = 2 * Math.atan2(Math.sqrt(num), Math.sqrt(1-num));
        return EARTH_RADIUS * c; // в км
    }
}
