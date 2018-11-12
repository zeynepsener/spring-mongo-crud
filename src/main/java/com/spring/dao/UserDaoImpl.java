
package com.spring.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.spring.model.User;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	MongoTemplate mongoTempl;

	private static final String COLLECTION_NAME = "user";

	public void addUser(User user) {
		if (user.getId() == null) {
			user.setId(UUID.randomUUID().toString());
		}
		mongoTempl.save(user, COLLECTION_NAME);
	}

	public List<User> listAllUsers() {
		return mongoTempl.findAll(User.class, COLLECTION_NAME);
	}

	@Override
	public void deleteUserById(String id) {
		mongoTempl.findAndRemove(new Query(Criteria.where("id").is(id)), User.class, COLLECTION_NAME);
	}

}
