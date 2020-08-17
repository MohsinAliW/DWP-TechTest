package warraich.mohsin.techtest.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import warraich.mohsin.techtest.model.Coordinates;
import warraich.mohsin.techtest.model.Location;
import warraich.mohsin.techtest.model.Returned;
import warraich.mohsin.techtest.model.User;
import warraich.mohsin.techtest.services.ComputeDistanceService;
import warraich.mohsin.techtest.services.UserClient;
import warraich.mohsin.techtest.services.UserDistanceServiceImpl;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDistanceTest {
	
	 @Mock
	    UserClient userClient;

	    @Mock
	    ComputeDistanceService computeDistanceService;

	    @InjectMocks
	    UserDistanceServiceImpl userDistanceService;

	    private Coordinates close, far, london;
	    private User londonUser, closeUser, farUser;
	    private Collection<User> londonUsers;
	    private Collection<User> allUsers;

	    @BeforeEach
	    void setUp() throws Exception {

	        MockitoAnnotations.initMocks(this);

	        close = new Coordinates(5.0, 5.0);
	        far = new Coordinates(20.0, 20.0);

	        london = Location.LONDON.getCoordinates();

	        londonUser = new User();
	        londonUser.setId(1);
	        londonUser.setLatitude(far.getLatitude());
	        londonUser.setLongitude(far.getLongitude());

	        closeUser = new User();
	        closeUser.setId(2);
	        closeUser.setLatitude(close.getLatitude());
	        closeUser.setLongitude(close.getLongitude());

	        farUser = new User();
	        farUser.setId(3);
	        farUser.setLatitude(far.getLatitude());
	        farUser.setLongitude(far.getLongitude());

	        londonUsers = new ArrayList<>();
	        londonUsers.add(londonUser);

	        allUsers = new ArrayList<>();
	        allUsers.add(londonUser);
	        allUsers.add(closeUser);
	        allUsers.add(farUser);

	        Mockito.when(userClient.getUsersFromCity(Location.LONDON.getName())).thenReturn(londonUsers);
	        Mockito.when(userClient.getAllUsers()).thenReturn(allUsers);
	        Mockito.when(computeDistanceService.calculateDistance(close, london)).thenReturn(25.0);
	        Mockito.when(computeDistanceService.calculateDistance(far, london)).thenReturn(100.0);
	    }

	    @AfterEach
	    void tearDown() {
	    }

	    @Test
	    void test_getUsersLocation_allParameters() {

	        Returned withinDistanceOfLocation = userDistanceService.getUsers(Location.LONDON.getName(), 50.0, london.getLatitude(), london.getLongitude());
	        assertThat(withinDistanceOfLocation.getUsers()).hasSize(2);
	        assertThat(withinDistanceOfLocation.getUsers()).contains(londonUser, closeUser);
	    }

	    @Test
	    void test_getUserLocation_coordsOnly() {
	        Returned withinDistanceOfLocation = userDistanceService.getUsers(null, 50.0, london.getLatitude(), london.getLongitude());
	        assertThat(withinDistanceOfLocation.getUsers()).hasSize(1);
	        assertThat(withinDistanceOfLocation.getUsers()).contains(closeUser);
	    }

	    @Test
	    void test_getUsersLocation_distanceLessThan0() {
	        Returned withinDistanceOfLocation = userDistanceService.getUsers(null, -50.0, london.getLatitude(), london.getLongitude());
	        assertThat(withinDistanceOfLocation.getUsers()).hasSize(0);
	    }

	    @Test
	    void test_getUsersLocation_nameSearch_InvalidCoords() {
	        Returned withinDistanceOfLocation = userDistanceService.getUsers(Location.LONDON.getName(), -50.0, -91.0, -181.0);
	        assertThat(withinDistanceOfLocation.getUsers()).hasSize(1);
	        assertThat(withinDistanceOfLocation.getUsers()).contains(londonUser);
	    }

}
