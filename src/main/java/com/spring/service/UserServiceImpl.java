/**
 * 
 */
package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.UserDao;
import com.spring.model.User;

/**
 * @author zeynep
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;

	public List<User> listAll() {
		return dao.listAllUsers();
	}

	public void add(User user) {
		dao.addUser(user);

	}

	@Override
	public void delete(String id) {
		dao.deleteUserById(id);
	}

}
