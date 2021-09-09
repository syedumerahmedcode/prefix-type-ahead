# Prefix type-ahead

## Table of content
- [Introduction](#introduction)
- [Explanation TrieNode](#explanation-trienode)
- [Explanation DFS](#explanation-dfs)
- [Explanation System Design](#explanation-system-design)
- [Explanation Redis Commands](#explanation-redis-commands)
- [Technologies Used](#technologies-used)
- [Swagger](#swagger)
- [Commands](#commands)
- [Contact Information](#contact-information)

## Introduction

This project demonstrates auto completion capability (also known as the look ahead feature) which one sees when one types something in google search bar. To accomplish this Trie data structure is used. The project consists of two modules: `trie` which contains the trie structure for prefix and `prefixweb` which is used to connect the backend with the outside world via REST APIs.

## Explanation TrieNode

![TrieNodeOnWhiteBoard](https://github.com/syedumerahmedcode/prefix/blob/master/src/main/resources/syetemdesign/TrieNodeOnWhiteBoard.jpeg)

The TrieNode consists of the following components:

> Map<Character, TrieNode> children

A recursive list which contains the character and the possible children, which are themselves TrieNodes.Default value is a new Hashmap.

> EOW

True if we reached the end of the word. Default value is false.

> phrase

What is the complete phrase in the node chain. Default value is an empty string.

> weight

Contains the weight of how often a node is hit in the lookup. Default value is 0.

It is important to note that a node can have `EOW=true` but `isLeaf=false`. Check if the node is a leaf or not is done via a predicate. Insertion and searching within the Tree is done via recursion(Depth First Search or DFS).  

## Explanation DFS

**Q) What is DFS?**

Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. The algorithm starts at the root node (selecting some arbitrary node as the root node in the case of a graph) and explores as far as possible along each branch before backtracking. DFS is typically used to traverse an entire graph, and takes time O ( | V | + | E | ) where |V| is the number of vertices and |E| the number of edges.

In order to understand the DFS(Depth First Search), we will look at code of method `public List<String> findMatchingPhrases(String prefix)` and analyze it in more detail.

```java
public List<String> findMatchingPhrases(String prefix) {
		TrieNode subTreeRoot = findSubTree(prefix);
		if (subTreeRoot != null) {
			// Recursive DFS
			return findMatchingPhrases(subTreeRoot, new ArrayList<String>());
		} else {
			return Collections.EMPTY_LIST;
		}
	}
```
The above method uses modified depth first search to find all the terminal nodes that represent prefix matched phrases. `@param prefix` is the input prefix whereas the method returns a list of all possible phrases that contain the prefix.

Inside this method, we first get the subtree for the given prefix. If a subtree exists, we call *findMatchingPhrases()* with newly found subTree and a new array list. If the subtree is null, we return an empty collection from the method. Due to the reason who the call stack builds up during recursion, all matching phrases are returned. Both *findSubTree()* and *findMatchingPhrases()* are explained below:

**!!! In progress !!!**

## Explanation System Design

![SystemOverviewOnWhietBoard](https://github.com/syedumerahmedcode/prefix/blob/master/src/main/resources/syetemdesign/SystemOverviewOnWhietBoard.jpeg)

The process flow of the architecture is as follows:

> Clients 

The clients calls prefix which are forwarded to the Application Load Balancer.

> Application Load Balancer(ALB) 

In this scenario, it consists of two parts: `Active` and `Passive`. It is responsible for forwarding requests to nodes.

> Nodes

In this scenario, we have 3 nodes which `N1`, `N2` and `N3`. They look up the information first in the Cache.

> Cache

This is a Redis Cache with a Key value pair of `<String,String>`. It has finite memory and it will use LRU eviction policy. In my implementation, it is a in memory cache or ephemeral. This means that as soon as application stops, the cache also vanishes. The main reason of using cache is that it has O(1) complexity when looking up saved results whereas in Trie, the complexity is O(v+e) i.e. vertices and edges.

> Cache miss

In case of a cache miss, the system should contact Zookeeper which will determine, based on Start range, which Ip Address should be lookup for creating TrieNodes.

- Zookeeper part is currently not implemented.

> Prefix (Java Spring)

These are responsible for a range of characters and each of them uses PrefixTrie Data Structure(referenced above) to create the Trie Tree.

> MongoDB

The trie structures created via spring boot application are saved in MongoDB.

- MongoDB part is currently not implemented. The Trie structure is present only till the application runs. As soon as the application terminates, the data is lost.

> Data Aggregator

The idea is to fetch the data from MongoDB every 5 minutes, clean the cache and re-populate it with aggregated data. This is needed so that the cache does not become stale.

- Since the cache is in memory right now and no data is saved in MongoDB, Data Aggregator is currently not implemented.

## Explanation Redis Commands

The command used to create a docker instance of redis: 

> docker run --name trie-redis -p 6379:6379 -d redis

where `--name` is the name of the docker container, `-p` specifies the port mapping `external:internal` and `-d` tells to run it in detached mode.


The following are some commands which are serve as an introduction on how to use Redis from command line. Once the concepts are understood, it is easy to program it in Java.

> redis-cli ping

Simple check command. Returns PONG.

> redis-cli

Connects to the docker container and takes the use to the command prompt.

> set testKey testValue

Sets `testKey` with corresponding `testValue`.

> keys '*'

Returns all keys.

> get testKey

Gets all values for `testKey`.

> zadd sortedSetKey 100 sortedSetValue

Adds to a Sorted set, the key: `sortedSetKey`, the score `100` and the value `sortedSetValue`.

> type sortedSetKey

Tells the user that this is a sorted set.

> zrange sortedSetKey 0 -1

Get all values for the key `sortedSetKey`.

> zrange sortedSetKey 0 100 WITHSCORES

Get all values for `sortedSetKey` with scores between 0 and 100.

> zrevrange sortedSetKey 0 -1 WITHSCORES

Get all values for `sortedSetKey` with scores between 0 and 100 but in reverse.

> del sortedSetKey

Deletes values for key `sortedSetKey`.




 



## Technologies Used

- TBD

## Swagger
Following are the available end points: 

- TBD

## Commands
To compile and test, please execute
> mvn clean install

To run the application, please navigate to the `prefixweb` folder and then please execute
> mvn spring-boot:run



## Contact Information

How to reach me? At [github specific gmail account](syed.umer.ahmed.code@gmail.com). Additionally, you can reach me via [Linkedin](https://www.linkedin.com/in/syed-umer-ahmed-a346a746/) or at [Xing](https://www.xing.com/profile/SyedUmer_Ahmed/cv)





