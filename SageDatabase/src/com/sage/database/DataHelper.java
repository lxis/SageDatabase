package com.sage.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataHelper extends OrmLiteSqliteOpenHelper
{
	private static final String DATABASE_NAME = "helloAndroid.db";

	private static final int DATABASE_VERSION = 1;

	public DataHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource connectionSource)
	{
		try
		{
			TableUtils.createTableIfNotExists(connectionSource, Contact.class);
			TableUtils.createTableIfNotExists(connectionSource, MyContact.class);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int arg2, int arg3)
	{
		try
		{
			TableUtils.dropTable(connectionSource, Contact.class, true);
			TableUtils.dropTable(connectionSource, MyContact.class, true);
			onCreate(db, connectionSource);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void close()
	{
		super.close();
	}



}
