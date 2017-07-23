package com.lucask.bullseyes.infrastruture.resources;

public interface ResourceMapper<E, T> {

	public T process(E source);
}
