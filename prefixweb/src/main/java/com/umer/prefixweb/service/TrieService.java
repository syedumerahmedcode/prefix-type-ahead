package com.umer.prefixweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.PrefixTrie;

/**
 * Communicate back and forth with the current running instance of the Trie
 * representing the current range.
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

	public TrieService() {
		this.prefixTrie = new PrefixTrie();
	}

	public List<String> getMatchingPhrases(String prefix) {
		Set<String> results = redisService.checkCachePrefix(prefix);
		final boolean checkIfCacheHasResults = results.size() > 0;
		if (checkIfCacheHasResults) {
			return new ArrayList<String>(results);
		} else {
			return prefixTrie.findMatchingPhrases(prefix);
		}
	}

	public void insertPrefix(String prefix) {
		prefixTrie.insertPrefix(prefix);
		/**
		 * Once a prefix is inserted into the Trie for the first time, we want to
		 * inject it into the cache as well.
		 */
		redisService.insertIntoCache(prefix);
	}

}
