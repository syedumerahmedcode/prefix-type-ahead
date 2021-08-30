package com.umer.prefixweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trie")
public class TrieController {
	
	@GetMapping("/insert/{prefix}")
	public String addPrefix(@PathVariable("prefix") String prefix) {
		throw new UnsupportedOperationException("Method not implemented");
	}

}
