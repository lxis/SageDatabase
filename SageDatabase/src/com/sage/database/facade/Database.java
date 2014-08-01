package com.sage.database.facade;

import java.util.ArrayList;

import example.MyContact;

public class Database {
	public static void init(ArrayList<Class> classes) {
		classes.add(MyContact.class);
	}

	public static ArrayList<Class> Classes = new ArrayList<Class>();
}
