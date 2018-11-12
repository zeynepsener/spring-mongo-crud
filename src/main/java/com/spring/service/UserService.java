/**
 * 
 */
package com.spring.service;

import java.util.List;

import com.spring.dto.UserDTO;

/**
 * @author zeynep
 *
 */
public interface UserService {

	public List<UserDTO> listAll();

	public void add(UserDTO dto);

	public void delete(String id);

}
