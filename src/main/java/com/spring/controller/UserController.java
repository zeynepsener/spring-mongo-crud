package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.model.User;
import com.spring.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService service;

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUser() {
		return service.listAll();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody void saveUser(@RequestBody User user) {
		System.out.println(user);
		service.add(user);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody void deleteUser(@RequestBody String id) {
		System.out.println(id);
		service.delete(id);
	}
}
