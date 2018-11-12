/**
 * 
 */
package com.spring.service;

import java.util.List;

import com.spring.model.User;

/**
 * @author zeynep
 *
 */
public interface UserService {

	public List<User> listAll();

	public void add(User user);

	public void delete(String id);

}
