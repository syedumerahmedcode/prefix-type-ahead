package com.umer.prefixweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umer.prefixweb.service.TrieService;

import lombok.AllArgsConstructor;

@RestController
//@RequestMapping("/api/v1/trie")
//@RequestMapping("/v1")
@AllArgsConstructor
public class TrieController {
	
	public static final String TRIE_END_POINT_V1 = "/api/v1/trie";
	public static final String ADD_PREFIX_END_POINT_V1 = TRIE_END_POINT_V1 + "/insert/{prefix}";
	public static final String HEALTH_END_POINT_V1 = TRIE_END_POINT_V1 + "/health";
	
	final private TrieService trieService;
	
	ObjectMapper mapper=new ObjectMapper();
	
	@GetMapping(
			path = ADD_PREFIX_END_POINT_V1, 
			produces = MediaType.ALL_VALUE
			)
	public String addPrefix(@PathVariable("prefix") String prefix) {
		trieService.insertPrefix(prefix);
		return prefix + " was inserted into the trie successfully.";
	}
	
	@GetMapping(
			path = HEALTH_END_POINT_V1, 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public String healthCheck() {
		return "I am alive";
	}
	
	@GetMapping("/retrieve/{prefix}")
	public String retrievePrefix(@PathVariable("prefix") String prefix) throws JsonProcessingException {
		List<String> results=trieService.getMatchingPhrases(prefix);
		return mapper.writeValueAsString(results);
	}
	

}
