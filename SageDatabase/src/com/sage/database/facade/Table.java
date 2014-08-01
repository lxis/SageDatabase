package com.sage.database.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import android.R.integer;
import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.sage.database.CommonSimpleHandlerGenic;
import com.sage.database.DataHelper;

import example.MyContact;

public class Table<T extends BaseItem>
{
	private ArrayList<T> removedItems = new ArrayList<T>();

	public ObserverbleCollection<T> Items = new ObserverbleCollection<T>();

	private Dao<T, Integer> dao;
	
	public Table(Class<T> itemClass, Context context) throws SQLException
	{
		DataHelper dataHelper = OpenHelperManager.getHelper(context, DataHelper.class);		
		dao = dataHelper.getDao(itemClass);
		Items.addedHandler = new CommonSimpleHandlerGenic<T>()
		{
			@Override
			public <T> void Run(T item)
			{
				((BaseItem) item).isNew = true;
			}
		};
		Items.removedHandler = new CommonSimpleHandlerGenic<T>()
		{
			@Override
			public <U> void Run(U item)
			{
				removedItems.add(((T) item));
			}
		};
	}

	public void load() throws SQLException
	{
		if (Items.size() == 0)
			Items.addAll(dao.queryForAll());
		else
			throw new RuntimeException("重复加载");// 已加载，不能重复加。这个地方的处理没有深入思考，异常也需要细化。
	}
	
//	public void Query(String queryText)
//	{
//	
//	}

	public void save() throws SQLException
	{
		for (T item : Items)
		{
			if (item.isNew)
				dao.create(item);
			else if (item.isChanged) dao.update(item);
		}

		for (T item : removedItems)
		{
			dao.delete(item);
		}
	}

}
