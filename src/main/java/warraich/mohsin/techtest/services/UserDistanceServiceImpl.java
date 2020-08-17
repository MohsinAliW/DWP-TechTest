package warraich.mohsin.techtest.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import warraich.mohsin.techtest.model.Coordinates;
import warraich.mohsin.techtest.model.Returned;
import warraich.mohsin.techtest.model.User;

@Service
public class UserDistanceServiceImpl implements UserDistanceService {
	
    @Autowired
    private UserClient userClient;

    @Autowired
    private ComputeDistanceService computeDistanceService;

    @Override
    public Returned getUsers(String locationName, Double distance, Double locationLatitude, Double locationLongitude) {

        Returned results = new Returned();
        Set<User> users = new HashSet<>();

        // If a location name has been provided look up users from that city
        if (!(locationName == null) && !locationName.isEmpty()) {
            users.addAll(userClient.getUsersFromCity(locationName));
        } 


        Coordinates coords = new Coordinates(locationLatitude, locationLongitude);
        users.addAll(getUsersWithinCoords(distance, coords));
            
                results.setUsers(users);

        return results;
    }

    private List<User> getUsersWithinCoords(double distance, Coordinates location) {

        List<User> usersWithinRange = new ArrayList<>();

        // Get all users
        Collection<User> allUsers = userClient.getAllUsers();

        for (User user : allUsers) {

            // Calculate the distance between the user and the coordinates provided
            Coordinates userCoords = new Coordinates(user.getLatitude(), user.getLongitude());
            double distanceFromCoords = computeDistanceService.calculateDistance(userCoords, location);

            // If the user is within range add it to the list
            if (distanceFromCoords <= distance) {
                user.setDistanceFromCoords(distanceFromCoords);
                usersWithinRange.add(user);
            }
        }

        return usersWithinRange;
    }

}
