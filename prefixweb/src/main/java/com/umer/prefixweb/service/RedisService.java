package com.umer.prefixweb.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	private static final int LOWEST_SCORE = -1;
	private static final int HIGHEST_SCORE = 0;
	private static final int SCORE = 1;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void insertIntoCache(String prefix) {
		redisTemplate.opsForZSet().add(prefix, prefix, SCORE);
	}

	public Set<String> checkCachePrefix(String prefix) {
		Set<String> results = redisTemplate.opsForZSet().reverseRange(prefix, HIGHEST_SCORE, LOWEST_SCORE);
		return results;
	}

}
