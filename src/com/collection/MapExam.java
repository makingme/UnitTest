package com.collection;

import java.util.HashMap;
import java.util.Map;

public class MapExam {

	public MapExam() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		HashMap<String,String> aa =new HashMap<String,String>();

	}

}
final class MyEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }
}