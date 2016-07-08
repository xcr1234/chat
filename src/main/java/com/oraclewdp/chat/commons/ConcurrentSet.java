package com.oraclewdp.chat.commons;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentSet<E> extends AbstractSet<E> implements Set<E> {
	
	private ConcurrentHashMap<E, Object> map;
	
	private static Object object = new Object();
	
	public ConcurrentSet(){
		map = new ConcurrentHashMap<E,Object>();
	}
	public ConcurrentSet(int i){
		map = new ConcurrentHashMap<E,Object>(i);
	}
	
	@Override
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean add(E e) {
		return map.put(e, object)!=null;
	}
	@Override
	public boolean remove(Object o) {
		return map.remove(o)!=null;
	}
	@Override
	public boolean contains(Object o) {
		return map.containsKey(o);
	}
	
	@Override
	public void clear() {
		map.clear();
	}

}
