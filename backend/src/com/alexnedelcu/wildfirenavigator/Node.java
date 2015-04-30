package com.alexnedelcu.wildfirenavigator;
import java.util.ArrayList;
import java.util.List;





public class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;

        Node (Node<T> parentNode) {
        	this.parent = parentNode;
        	this.children = new ArrayList<Node<T>>();
        }
        
        public Node addChild(T childData) {
        	Node node = new Node<T>(this);
        	node.data = childData;
        	node.children = new ArrayList<Node<T>>();
        	this.children.add(node);
        	
        	return node;
        }
        
        public ArrayList<T> getChildren() {
        	return (ArrayList<T>) this.children;
        }
        
        public T getRootData () {
        	return data;
        }
        
        public void setRootData (T rootData) {
        	data = rootData;
        }
        public Node<T> getParent () {
        	return parent;
        }

        

    }