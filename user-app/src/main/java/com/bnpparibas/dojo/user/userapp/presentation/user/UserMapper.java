package com.bnpparibas.dojo.user.userapp.presentation.user;

import org.springframework.stereotype.Component;

import com.bnpparibas.dojo.user.userapp.domaine.User;

/**
 * @author Badr NASS
 *
 */
@Component
public class UserMapper {

	public UserDTO mapToDto(User user) {
		if (user != null)
			return new UserDTO(user.getFirstName(), user.getLastName());
		else
			throw new RuntimeException("User can not be null");
	}

	public User mapToEntity(UserDTO userDTO) {
			return new User(userDTO.getFirstName(), userDTO.getLastName());
	}

}