package com.edao.codes.datastructure.tree.btree;

/**
 * @author liushuai
 *
 */
public class BTNode {
	int keyNum; // keyword number keyNum < m
	BTNode parent; // point to parent node
	
	BTNode[] ptr; // sub-tree reference vector, length = keyNum + 1
	KeyType[] keys; // keyword reference vector, length = keyNum 
}

class KeyType {
	String key;
}
