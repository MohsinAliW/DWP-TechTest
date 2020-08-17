package warraich.mohsin.techtest.services;

import warraich.mohsin.techtest.model.Returned;

public interface UserDistanceService {
	
	Returned getUsers(String locationName,
            Double distance,
            Double locationLatitude,
            Double locationLongitude);

}
