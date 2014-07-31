package com.sage.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable 
public class MyContact
{
	@DatabaseField(generatedId = true)
	int id;

	@DatabaseField
	public String Name;
	
	@DatabaseField
	public String Phone;
	
	public MyContact(){}		
}
