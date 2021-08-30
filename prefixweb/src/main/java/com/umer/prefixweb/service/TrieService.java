package com.umer.prefixweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import data.PrefixTrie;

/**
 * Communicate back and forth with the current running instance 
 * of the Trie representng the current range.
 * @author umer

 *
 */
@Service
public class TrieService {

	private PrefixTrie prefixTrie;
	
	public TrieService() {
		this.prefixTrie=new PrefixTrie();
	}
	
	public List<String> getMatchingPhrases(String prefix){
		return prefixTrie.findMatchingPhrases(prefix);
	}
	
	public void insertPrefix(String prefix) {
		prefixTrie.insertPrefix(prefix);
	}
	
}
