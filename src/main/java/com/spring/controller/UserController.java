package com.spring.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dto.UserDTO;
import com.spring.service.UserService;

@Controller
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService service;

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public @ResponseBody List<UserDTO> getAllUser() {
		logger.debug("User list is rendering.");
		return service.listAll();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody void saveUser(@RequestBody UserDTO userDTO) {
		logger.debug("Adding a new user with data: " + userDTO);
		service.add(userDTO);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void deleteUser(@RequestBody String id) {
		logger.debug("Deleting a user with id: " + id);
		service.delete(id);
	}
}
