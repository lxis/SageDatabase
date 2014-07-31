package com.sage.database;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="Contact") 
public class Contact   {
	
	@DatabaseField(generatedId = true)//自增长的主键
	int id;

	@DatabaseField
	public String Name;
	
	@DatabaseField
	public String Phone;
	
	public Contact()
	{
		
	}
	
	public void setName(String name)
	{
		this.Name = name;
		//Notify();
	}
	
	public Contact(String name,String phone)
	{
		Name = name;
		Phone = phone;
	}	
}
