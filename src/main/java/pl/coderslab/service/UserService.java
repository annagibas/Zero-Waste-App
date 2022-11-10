package pl.coderslab.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.coderslab.domain.User;
import pl.coderslab.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
