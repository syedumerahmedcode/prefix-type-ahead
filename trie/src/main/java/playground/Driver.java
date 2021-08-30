package playground;

import java.util.List;

import data.PrefixTrie;

public class Driver {
	public static void main(String[] args) {
		PrefixTrie prefixTrie=new PrefixTrie();
		prefixTrie.insertPrefix("ryan");
		prefixTrie.insertPrefix("rye");
		prefixTrie.insertPrefix("ryaane");
		prefixTrie.insertPrefix("run");
		
		List<String> results=prefixTrie.findMatchingPhrases("r");
		System.out.println(results);
	}
}
