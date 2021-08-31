package com.umer.prefixweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import data.PrefixTrie;

/**
 * Communicate back and forth with the current running instance of the Trie
 * representng the current range.
 * 
 * @author umer
 *
 * 
 */
@Service
public class TrieService {

	private PrefixTrie prefixTrie;

	@Autowired
	private RedisService redisService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public TrieService() {
		this.prefixTrie = new PrefixTrie();
	}

	public List<String> getMatchingPhrases(String prefix) {
		Set<String> results = checkCachePrefix(prefix);
		if (results.size() > 0) {
			return new ArrayList<String>(results);
		} else {
			return prefixTrie.findMatchingPhrases(prefix);
		}

	}

	private Set<String> checkCachePrefix(String prefix) {
		Set<String> results = redisTemplate.opsForZSet().reverseRangeByScore(prefix, 0, -1);
		return results;
	}

	public void insertPrefix(String prefix) {
		prefixTrie.insertPrefix(prefix);
		/**
		 * Once a prefix is inserted into the Trie for the first time, w we want to
		 * inject it into the cache as well.
		 */
		insertIntoCache(prefix);

	}

	private void insertIntoCache(String prefix) {
		redisTemplate.opsForZSet().add(prefix, prefix, 1);
	}

}
