package com.sage.database;

import com.j256.ormlite.field.DatabaseField;

public class BaseItem
{
	public boolean isNew = false;
	public boolean isChanged = false;
	public void Notify()
	{
		isChanged = true;
	}
	
	@DatabaseField(generatedId = true)
	public int id;
}
