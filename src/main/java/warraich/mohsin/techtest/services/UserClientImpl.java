package warraich.mohsin.techtest.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import warraich.mohsin.techtest.model.User;

@Service
public class UserClientImpl implements UserClient {
	
    @Value("${user.api.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Collection<User> getAllUsers() {

        ResponseEntity<User[]> response = restTemplate.getForEntity(baseUrl + "/users", User[].class);

        Collection<User> users = parseResponse(response);

        return users;
    }

    @Override
    public Collection<User> getUsersFromCity(String city) {

        ResponseEntity<User[]> response = restTemplate.getForEntity(baseUrl + "/city/" + city + "/users", User[].class);

        Collection<User> users = parseResponse(response);

        return users;
    }

    @Override
    public Optional<User> getUserById(int id) {

    	ResponseEntity<User> response = restTemplate.getForEntity(baseUrl + "/user/" + id, User.class);

        return Optional.ofNullable(response.getBody());
    }

    private Collection<User> parseResponse(ResponseEntity<User[]> response) {
        Collection<User> users = new ArrayList<>();

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            users = Arrays.asList(response.getBody());
        }
        return users;
    }

}
