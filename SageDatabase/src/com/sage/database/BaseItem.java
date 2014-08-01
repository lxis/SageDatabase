package com.sage.database;

public class BaseItem
{
	public boolean isNew = false;
	public boolean isChanged = false;
	public void Notify()
	{
		isChanged = true;
	}	
}
