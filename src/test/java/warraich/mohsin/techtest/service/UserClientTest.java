package warraich.mohsin.techtest.service;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import warraich.mohsin.techtest.model.User;
import warraich.mohsin.techtest.services.UserClientImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class UserClientTest {
	
	 @Value("${user.api.baseurl}")
	    String baseUrl;

	    @Mock
	    RestTemplate mockTemplate;

	    @InjectMocks
	    UserClientImpl client;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    void test_getAllUsers_usersReturned() {

	        User user1 = new User();
	        User user2 = new User();
	        User[] mockUsers = {user1, user2};

	        Mockito.when(mockTemplate.getForEntity(baseUrl + "/users", User[].class)).thenReturn(new ResponseEntity<>(mockUsers, HttpStatus.OK));
	        Collection<User> users = client.getAllUsers();

	        assertThat(users).contains(user1, user2);
	    }

	    @Test
	    void test_getAllUsers_noUsersReturned() {

	        Mockito.when(mockTemplate.getForEntity(baseUrl + "/users", User[].class)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

	        Collection<User> users = client.getAllUsers();

	        assertThat(users).isEmpty();
	    }

	    @Test
	    void test_getAllUsersFromCity_usersReturned() {

	        String city = "London";
	        User user1 = new User();
	        User user2 = new User();
	        User[] mockUsers = {user1, user2};

	        Mockito.when(mockTemplate.getForEntity(baseUrl + "/city/London/users", User[].class)).thenReturn(new ResponseEntity<>(mockUsers, HttpStatus.OK));
	        Collection<User> users = client.getUsersFromCity(city);

	        assertThat(users).contains(user1, user2);
	    }

	    @Test
	    void test_getAllUsersFromCity_noUsersReturned() {

	        String city = "London";

	        Mockito.when(mockTemplate.getForEntity(baseUrl + "/city/London/users", User[].class)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
	        Collection<User> users = client.getUsersFromCity(city);

	        assertThat(users).isEmpty();
	    }


	    @Test
	    void test_getUserById_UserReturned() {

	        int id = 1;
	        User user = new User();

	        Mockito.when(mockTemplate.getForEntity(baseUrl + "/user/" + id, User.class)).thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

	        Optional<User> returnedUser = client.getUserById(id);
	        assertThat(returnedUser).isNotEmpty();
	        assertThat(returnedUser.get()).isEqualTo(user);
	    }

	    @Test
	    void test_getUserById_noUserReturned() {

	        int id = 1;

	        Mockito.when(mockTemplate.getForEntity(baseUrl + "/user/" + id, User.class)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

	        Optional<User> returnedUser = client.getUserById(id);
	        assertThat(returnedUser).isEmpty();
	    }

}
