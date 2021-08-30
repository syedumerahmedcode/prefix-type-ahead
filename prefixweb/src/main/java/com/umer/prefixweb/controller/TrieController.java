package com.umer.prefixweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umer.prefixweb.service.TrieService;

@RestController
@RequestMapping("/api/v1/trie")
public class TrieController {
	
	@Autowired
	private TrieService trieService;
	
	@GetMapping("/insert/{prefix}")
	public String addPrefix(@PathVariable("prefix") String prefix) {
		trieService.insertPrefix(prefix);
		return prefix + " was inserted into the trie successfully";
	}
	
	@GetMapping("/health")
	public String healthCheck() {
		return "I am alive";
	}

}
