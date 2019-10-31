package com.bnpparibas.dojo.user.userapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bnpparibas.dojo.user.userapp.domaine.User;

public interface UserRepository extends JpaRepository<User, String> {



	
}
