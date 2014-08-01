package com.sage.database.facade;

import java.util.ArrayList;

import com.sage.database.CommonSimpleHandlerGenic;

public class ObserverbleCollection<T> extends ArrayList<T> {
	@Override
	public boolean add(T object) {
		if (addedHandler != null)
			addedHandler.Run(object);
		return super.add(object);
	}

	@Override
	public boolean remove(Object object) {
		if (removedHandler != null)
			removedHandler.Run(object);
		return super.remove(object);
	}

	public CommonSimpleHandlerGenic<T> addedHandler;

	public CommonSimpleHandlerGenic<T> removedHandler;
}
