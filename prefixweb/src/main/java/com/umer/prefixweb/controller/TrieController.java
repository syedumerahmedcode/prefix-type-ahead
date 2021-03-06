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
import com.umer.prefixweb.service.RedisService;
import com.umer.prefixweb.service.TrieService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TrieController {

	private static final String WAS_INSERTED_INTO_THE_TRIE_SUCCESSFULLY = " was inserted into the trie successfully.";
	private static final String I_AM_ALIVE = "I am alive";
	public static final String API_VERSION_V1 = "/v1";
	public static final String TRIE_END_POINT_V1 = "/api" + API_VERSION_V1 + "/trie";
	public static final String ADD_PREFIX_END_POINT_V1 = TRIE_END_POINT_V1 + "/insert/{prefix}";
	public static final String HEALTH_END_POINT_V1 = TRIE_END_POINT_V1 + "/health";
	public static final String RETRIEVE_PREFIX_END_POINT_V1 = TRIE_END_POINT_V1 + "/retrieve/{prefix}";

	final private TrieService trieService;

	ObjectMapper mapper = new ObjectMapper();

	@GetMapping(
			path = ADD_PREFIX_END_POINT_V1, 
			produces = MediaType.ALL_VALUE
	)	
	public String addPrefix(@PathVariable("prefix") String prefix) {
		trieService.insertPrefix(prefix);
		return prefix + WAS_INSERTED_INTO_THE_TRIE_SUCCESSFULLY;
	}

	@GetMapping(
			path = HEALTH_END_POINT_V1, 
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public String healthCheck() {
		return I_AM_ALIVE;
	}

	@GetMapping(
			path = RETRIEVE_PREFIX_END_POINT_V1, 
			produces = MediaType.ALL_VALUE
	)
	public String retrievePrefix(@PathVariable("prefix") String prefix) throws JsonProcessingException {
		/**
		 * Lets hit the cache first, then if we get a cache miss, then we want to pull
		 * the information from the tree.
		 */
		List<String> results = trieService.getMatchingPhrases(prefix);
		return mapper.writeValueAsString(results);
	}

}
