package warraich.mohsin.techtest.services;

import java.util.Collection;
import java.util.Optional;

import warraich.mohsin.techtest.model.User;

public interface UserClient {
	
	 Collection<User> getAllUsers();

	 Collection<User> getUsersFromCity(String city);

	 Optional<User> getUserById(int id);

}
