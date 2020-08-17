package warraich.mohsin.techtest.services;

import org.springframework.stereotype.Service;

import warraich.mohsin.techtest.model.Coordinates;

@Service
public class ComputeDistanceServiceImpl implements ComputeDistanceService {
	

    private static final double EARTH_RADIUS = 3958.8;

    @Override
    public double calculateDistance(Coordinates far, Coordinates wide) {

        if ((far.getLatitude() == wide.getLatitude()) && (far.getLongitude() == wide.getLongitude())) {
            return 0;
        } else {

            double farLon = Math.toRadians(far.getLongitude());
            double farLat = Math.toRadians(far.getLatitude());

            double wideLon = Math.toRadians(wide.getLongitude());
            double wideLat = Math.toRadians(wide.getLatitude());

            double lonDist = wideLon - farLon;
            double latDist = wideLat - farLat;
            double a = Math.pow(Math.sin(latDist / 2), 2)
                    + Math.cos(farLat) * Math.cos(wideLat)
                    * Math.pow(Math.sin(lonDist / 2), 2);

            double c = 2 * Math.asin(Math.sqrt(a));

            // calculate the result
            return (c * EARTH_RADIUS);
        }
    }

}
