
package com.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.model.User;

@Repository
public interface UserDao {

	public List<User> listAllUsers();

	public void addUser(User user);

	public void deleteUserById(String id);

}
