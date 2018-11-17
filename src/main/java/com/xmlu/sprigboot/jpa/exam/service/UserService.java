package com.xmlu.sprigboot.jpa.exam.service;

import com.xmlu.sprigboot.jpa.exam.dao.UserRepository;
import com.xmlu.sprigboot.jpa.exam.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
	public User getUserById(@Param("id") Integer id){
	    return userRepository.getUserById(id);
    }
}
