package com.sage.database;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class BaseTable<T extends BaseItem>
{
	public ArrayList<T> removedItems = new ArrayList<T>();

	public ObserverbleCollection<T> Items = new ObserverbleCollection<T>();

	private Class<T> itemClass;

	private Context context;

	public BaseTable(Class<T> itemClass, Context context)
	{
		this.itemClass = itemClass;
		this.context = context;

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

	public void Load() throws SQLException
	{
		DataHelper dataHelper = OpenHelperManager.getHelper(context, DataHelper.class);
		Dao<T, Integer> contactDao = dataHelper.getDao(itemClass);
		if (Items.size() == 0)
			Items.addAll(contactDao.queryForAll());
		else
			throw new RuntimeException("重复加载");// 已加载，不能重复加。这个地方的处理没有深入思考，异常也需要细化。
	}

	public void Save() throws SQLException
	{
		DataHelper dataHelper = OpenHelperManager.getHelper(context, DataHelper.class);
		Dao<T, Integer> contactDao = dataHelper.getDao(itemClass);
		for (T item : Items)
		{
			if (item.isNew)
				contactDao.create(item);
			else if (item.isChanged) contactDao.update(item);
		}

		for (T item : removedItems)
		{
			contactDao.delete(item);
		}
	}

}
