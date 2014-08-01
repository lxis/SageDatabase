package example;

import java.sql.SQLException;

import com.sage.database.*;
import com.sage.database.facade.Table;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try
		{			
			MyDatabase();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void MyDatabase() throws SQLException
	{		
		Table<MyContact> myContacts =  new Table<MyContact>(MyContact.class, this);
		
		//插入
		MyContact contact = new MyContact();
		contact.setName("my name");
		myContacts.Items.add(contact);
		
		//更新
		myContacts.Items.get(0).setName("sage");		
		
		//删除
		myContacts.Items.remove(0);
		
		//查询
		myContacts.Load();
		
		//全部执行(增删改)
		myContacts.Save();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
