package com.sage.database.facade;

public class BaseItem {
	public boolean isNew = false;
	public boolean isChanged = false;

	public void notifyChanged() {
		isChanged = true;
	}
}
