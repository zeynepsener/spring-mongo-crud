/**
 * 
 */
package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.UserDao;
import com.spring.dto.UserDTO;
import com.spring.model.User;

/**
 * @author zeynep
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao dao;

	public List<UserDTO> listAll() {
		logger.debug("Getting user list.");
		List<UserDTO> dtoList = new ArrayList<UserDTO>();
		List<User> userList = dao.listAllUsers();
		for (User user : userList) {
			UserDTO dto = new UserDTO();
			BeanUtils.copyProperties(user, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public void add(UserDTO dto) {
		logger.debug("Adding a new user: " + dto);
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		dao.addUser(user);

	}

	@Override
	public void delete(String id) {
		logger.debug("Deleting a user with id: " + id);
		dao.deleteUserById(id);
	}

}
