package com.umer.prefixweb.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void insertIntoCache(String prefix) {
		redisTemplate.opsForZSet().add(prefix, prefix, 1);
	}
	
	public Set<String> checkCachePrefix(String prefix) {
		Set<String> results = redisTemplate.opsForZSet().reverseRange(prefix, 0, -1);
		return results;
	}

}
