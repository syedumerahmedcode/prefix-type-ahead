package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class PrefixTrie {
	private static final int INITIAL_WEIGHT = 0;
	private static final String EMPTY_PHRASE = "";

	public static class TrieNode {
		
		
		/**
		 * A recursive list which contains the character and the possible children.
		 * Default value is a new Hashmap
		 */
		protected Map<Character, TrieNode> children;
		/**
		 * True if we reached the end of the word. Default value is false.
		 */
		protected boolean EOW;
		/**
		 * What is the complete phrase in the node chain. Default value is an empty
		 * string.
		 */
		protected String phrase;
		/*
		 * Contains the weight of how often a node is hit in the lookup. Default value
		 * is 0.
		 */
		protected int weight;

		public TrieNode() {
			children = new HashMap<Character, PrefixTrie.TrieNode>();
			EOW = false;
			phrase = EMPTY_PHRASE;
			weight = INITIAL_WEIGHT;
		}

		public Map<Character, TrieNode> getChildren() {
			return children;
		}
	}

	private TrieNode root;
	private final Predicate<TrieNode> isLeaf = (currNode) -> currNode.children.size() == 0;

	public PrefixTrie() {
		root = new TrieNode();
	}

	/**
	 * Inserts a String prefix into the trie.
	 * 
	 * @param phrase Takes a phrase like "umer" and then recursively inject into the
	 *               tree node by node.
	 */
	public void insertPrefix(String phrase) {
		insertPhrase(phrase, root, INITIAL_WEIGHT);
	}

	private void insertPhrase(String phrase, TrieNode currentNode, int index) {
		// base condition
		if (phrase.length() == index) {
			currentNode.EOW = true;
			currentNode.phrase = phrase;
			currentNode.weight = 1;
			return;
		}
		char currentCharacter = phrase.charAt(index);
		TrieNode newNode = currentNode.children.get(currentCharacter);

		if (newNode == null) {
			newNode = new TrieNode();
			currentNode.children.put(currentCharacter, newNode);
		}

		/*recursive call*/
		insertPhrase(phrase, newNode, index + 1);
	}

	/**
	 * Modified depth first search to find all the terminal nodes that represent
	 * prefix matched phrases.
	 * 
	 * @param prefix The input perfix.
	 * @return A list of all possible phrases that contain the prefix.
	 */
	public List<String> findMatchingPhrases(String prefix) {
		TrieNode subTreeRoot = findSubTree(prefix);
		if (subTreeRoot != null) {
			// Recursive DFS
			return findMatchingPhrases(subTreeRoot, new ArrayList<String>());
		} else {
			return Collections.EMPTY_LIST;
		}

	}

	private List<String> findMatchingPhrases(TrieNode node, List<String> result) {
		// one base condition
		if (node.EOW == true) {
			result.add(node.phrase);
			// another base condition
			if (isLeaf.test(node)) {
				node.weight++;
				return result;
			}
		}
		/*recursive call*/
		node.children.keySet().stream().forEach(child -> findMatchingPhrases(node.children.get(child), result));
		return result;
	}

	/**
	 * Find the node that begins at the end of the input prefix. Ultimately allows
	 * us to search for all words that match the given prefix.
	 * 
	 * Depth first search strategy.
	 * 
	 * @param prefix The input prefix.
	 * @return The Subtree
	 */
	private TrieNode findSubTree(String prefix) {
		return findSubtree(root, prefix, INITIAL_WEIGHT);
	}

	private TrieNode findSubtree(TrieNode currentNode, String prefix, int index) {
		// base condition
		if (isPrefixEmpty(prefix) || indexEqualsSizeOfThePrefix(prefix, index)) {
			return currentNode;
		}
		TrieNode tempNode = getChildWhichMatchesThePhraseAtInitialIndex(currentNode, prefix, index);
		if (tempNode == null) {
			return null;
		}
		/*recursive call*/
		return findSubtree(tempNode, prefix, index + 1);

	}

	private TrieNode getChildWhichMatchesThePhraseAtInitialIndex(TrieNode currentNode, String prefix, int index) {
		return currentNode.children.get(prefix.charAt(index));
	}

	private boolean indexEqualsSizeOfThePrefix(String prefix, int index) {
		return index == prefix.length();
	}

	private boolean isPrefixEmpty(String prefix) {
		return prefix.length() == 0;
	}
}
