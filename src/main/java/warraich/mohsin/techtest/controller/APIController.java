package warraich.mohsin.techtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import warraich.mohsin.techtest.model.Coordinates;
import warraich.mohsin.techtest.model.Location;
import warraich.mohsin.techtest.model.Returned;
import warraich.mohsin.techtest.services.UserDistanceService;

@RestController
public class APIController {

    @Autowired
    private UserDistanceService userDistanceService;

    @GetMapping(value = "/users")
    public Returned getUsersWithin50MilesOfLondon() {

        Coordinates london = Location.LONDON.getCoordinates();
        return userDistanceService.getUsers(Location.LONDON.getName(), 50.0, london.getLatitude(), london.getLongitude());
    }

}
