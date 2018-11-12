/**
 * 
 */
package com.spring.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spring.dao.UserDao;
import com.spring.dto.UserDTO;
import com.spring.model.User;

/**
 * @author zeynep
 *
 */
public class UserServiceImplTest {

	public static final String ID = UUID.randomUUID().toString();

	@Mock
	UserDao repoMock;

	@InjectMocks
	@Resource
	private UserServiceImpl service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void listAll() {

		List<User> userList = new ArrayList<User>();
		when(repoMock.listAllUsers()).thenReturn(userList);

		List<UserDTO> real = service.listAll();

		verify(repoMock, times(1)).listAllUsers();
		verifyNoMoreInteractions(repoMock);
		assertThat(real, is(userList));
	}

	@Test
	public void add() {

		UserDTO dto = new UserDTO();
		dto.setId(null);
		dto.setName("AddName");
		dto.setSurname("AddSurname");
		dto.setPhoneNumber("AddPhoneNumber");

		service.add(dto);

		ArgumentCaptor<User> userArgument = ArgumentCaptor.forClass(User.class);
		verify(repoMock, times(1)).addUser(userArgument.capture());
		verifyNoMoreInteractions(repoMock);

		User user = userArgument.getValue();

		assertNull(user.getId());
		assertThat(user.getName(), is(dto.getName()));
		assertThat(user.getSurname(), is(dto.getSurname()));
		assertThat(user.getPhoneNumber(), is(dto.getPhoneNumber()));
	}

	@Test
	public void delete() {

		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("AddName");
		user.setSurname("AddSurname");
		user.setPhoneNumber("AddPhoneNumber");

		service.delete(ID);

		verify(repoMock, times(1)).deleteUserById(ID);
		verifyNoMoreInteractions(repoMock);

	}
}
