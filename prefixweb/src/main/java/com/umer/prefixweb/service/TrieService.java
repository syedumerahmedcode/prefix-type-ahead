package com.umer.prefixweb.service;

import org.springframework.stereotype.Service;

import data.PrefixTrie;
import lombok.AllArgsConstructor;

/**
 * Communicate back and forth with the current running instance 
 * of the Trie representng the current range.
 * @author umer

 *
 */
@Service
@AllArgsConstructor
public class TrieService {

	private PrefixTrie prefixTrie;
	
}
