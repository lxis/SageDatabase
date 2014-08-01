package com.sage.database;

import android.content.Context;

public class MyContacts extends BaseTable<MyContact>
{
	public MyContacts(Context context)
	{
		super(MyContact.class,context);		
	}
}
