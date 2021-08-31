# Prefix type-ahead

## Table of content
- [Introduction](#introduction)
- [Explanation TrieNode](#explanation-trienode)
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

It is important to note that a node can have `EOW=true` but `isLeaf=false`. Check if the node is a leaf or not is done via a predicate. Insertiona and searching within the Tree is done via recursion.  

## Technologies Used

- TBD

## Swagger
Following are the available endpoints

- TBD

## Commands
To compile and test, please execute
> mvn clean install

To run the application, please navigate to the prefixweb folder and then please execute
> mvn spring-boot:run



## Contact Information

How to reach me? At [github specific gmail account](syed.umer.ahmed.code@gmail.com). Additionally, you can reach me via [Linkedin](https://www.linkedin.com/in/syed-umer-ahmed-a346a746/) or at [Xing](https://www.xing.com/profile/SyedUmer_Ahmed/cv)





