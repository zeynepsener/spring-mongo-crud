
package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Configuration
@ComponentScan({ "com.spring" })
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "springdb";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(new ServerAddress("localhost", 27017));
	}

}
