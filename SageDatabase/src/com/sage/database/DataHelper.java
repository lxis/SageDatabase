package com.sage.database;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sage.database.facade.Database;

import example.MyContact;

public class DataHelper extends OrmLiteSqliteOpenHelper {
	private static final String DATABASE_NAME = "helloAndroid.db";

	private static final int DATABASE_VERSION = 1;

	public DataHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		classes = Database.Classes;
		if (classes.size() == 0)
			Database.init(classes);
	}

	ArrayList<Class> classes = new ArrayList<Class>();

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource connectionSource) {
		try {
			for (Class classType : classes)
				TableUtils.createTableIfNotExists(connectionSource, classType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int arg2, int arg3) {
		try {
			for (Class classType : classes)
				TableUtils.dropTable(connectionSource, classType, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		super.close();
	}

}
