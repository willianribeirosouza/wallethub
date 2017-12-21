package com.ef.loader;

import org.dozer.Mapper;

public abstract class AbstractLoader {
	
	protected abstract Mapper mapper();
	
	protected <T> T map(Object source, Class<T> destinationClass){
		return this.map(source, destinationClass, this.mapper());
	}
	
	protected <T> T map(Object source, Class<T> destinationClass, Mapper mapper){
		return source != null ? mapper.map(source, destinationClass) : null;
	}

}
