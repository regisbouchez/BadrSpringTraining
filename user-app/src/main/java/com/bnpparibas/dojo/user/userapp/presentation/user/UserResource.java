package com.bnpparibas.dojo.user.userapp.presentation.user;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bnpparibas.dojo.user.userapp.dao.UserRepository;
import com.bnpparibas.dojo.user.userapp.domaine.User;
import com.bnpparibas.dojo.user.userapp.presentation.common.NotFoundException;

@RestController
@RequestMapping("/users")
public class UserResource {

	private UserRepository userRepository;
	private UserMapper userMapper;

	private UserResource(UserRepository userRepository, UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@GetMapping("/{id}")
	public UserDTO getUser(@PathVariable final String id) {
		final User user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User with the following id, not found: " + id));
		return userMapper.mapToDto(user);
	}

	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<URI> addUser(final @RequestBody UserDTO userDTO) {
		final User user = userMapper.mapToEntity(userDTO);
		userRepository.save(user);
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO updateUser(@PathVariable final String id, @RequestBody UserDTO userDTO) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User with the following id, not found: " + id));
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		userRepository.save(user);
		return userMapper.mapToDto(user);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable final String id) {
		userRepository.deleteById(id);
	}

}
