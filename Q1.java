package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final double LATITUDE_OF_ALKAABAH = 21.422506;
        final double LONGITUDE_OF_ALKAABAH = 39.826203;
        int numberOfCities = 0;
        double latitude = 0;
        double longitude = 0;
        double magneticDeclination = 0;
	    Scanner kb = new Scanner(System.in);
	    while (true) {
            System.out.println("Enter number of cities to process [1 to 10]: ");
            numberOfCities = kb.nextInt();
            if (numberOfCities <= 10 && numberOfCities >= 0)
                break;
            else System.out.println("Error: Invalid number of cities");
        }
	    for (int i = 1;i <=numberOfCities;i++) {
            boolean unValidLatitude = true;
            boolean unValidLongitude = true;
            boolean unValidMagneticDeclination = true;
                while (unValidMagneticDeclination || unValidLatitude || unValidLongitude) {
                    System.out.printf("Enter latitude, longitude, and magnetic declination for Location#%d", i);
                    latitude = kb.nextDouble();
                    longitude = kb.nextDouble();
                    magneticDeclination = kb.nextDouble();
                    if (latitude < -90 || latitude > 90) {
                        unValidLatitude = true;
                        System.out.println("Error: Invalid latitude");}
                    else unValidLatitude = false;
                    if (longitude < -180 || longitude > 180) {
                        System.out.println("Error: Invalid longitude");
                        unValidLongitude = true;}
                    else unValidLongitude = false;
                    if (magneticDeclination < -180 || magneticDeclination > 180) {
                        System.out.println("Error: Invalid magneticDeclination");
                        unValidMagneticDeclination = true;}
                    else unValidMagneticDeclination = false;
                }
            double x = Math.sin(Math.toRadians(LONGITUDE_OF_ALKAABAH - longitude));
            double y = (Math.cos(Math.toRadians(latitude)) * Math.tan(Math.toRadians(LATITUDE_OF_ALKAABAH))
                    - Math.sin(Math.toRadians(latitude)) * Math.cos(Math.toRadians(LONGITUDE_OF_ALKAABAH - longitude)));
            double radians = Math.atan(x/y);
            double angleInDegrees = Math.toDegrees(radians);
            if (x == 0.00 && y > 0.00 && magneticDeclination > 0)
            angleInDegrees = (angleInDegrees + 360);
            else if (x == 0 && y < 0)
            angleInDegrees +=  180;
            else if (x > 0 && y < 0)
            angleInDegrees += 180;
            else if (x < 0 && y < 0)
            angleInDegrees += 180;
            else if (x < 0 && y > 0)
            angleInDegrees += 360;
            double magneticBearing = (angleInDegrees  - magneticDeclination);
            System.out.printf("Location#%d: latitude %.6f , longitude %.6f , Qibla direction clockwise from Magnetic North %.1f \n",i , latitude, longitude, magneticBearing);
            }


        }





    }
