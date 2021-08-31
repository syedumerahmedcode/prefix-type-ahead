package com.umer.prefixweb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfiguration {
	
	@Bean
	JedisConnectionFactory getJedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration("localhost", 6379);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	
}
