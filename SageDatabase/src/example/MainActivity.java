package example;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.sage.database.Contact;
import com.sage.database.*;
import com.sage.database.R;

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
			//Database();
			MyDatabase();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void MyDatabase() throws SQLException
	{
		DataHelper dataHelper = OpenHelperManager.getHelper(this, DataHelper.class);

		MyInsert(dataHelper);
		MySelect(dataHelper);
	}

	private void MySelect(DataHelper dataHelper) throws SQLException
	{
		MyContacts myContacts =  new MyContacts(this);
		myContacts.Load();			
		int i = 0;
		int ii = i;
	}

	private void MyInsert(DataHelper dataHelper) throws SQLException
	{		
		MyContacts myContacts =  new MyContacts(this);
		
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

	private void Database() throws SQLException
	{
		DataHelper dataHelper = OpenHelperManager.getHelper(this, DataHelper.class);
		Insert(dataHelper);
		Select(dataHelper);
	}

	private void Insert(DataHelper dataHelper) throws SQLException
	{
		Contact contact = new Contact();
		contact.Name = "my name";
		contact.Phone = "186";

		Dao<Contact, Integer> contactDao = dataHelper.getDao(Contact.class);
		contactDao.create(contact);
	}

	private void Select(DataHelper dataHelper) throws SQLException
	{
		Dao<Contact, Integer> contactDao = dataHelper.getDao(Contact.class);
		List<Contact> contactLists = contactDao.queryForAll();
		int i = 0;
		int ii = i;
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
