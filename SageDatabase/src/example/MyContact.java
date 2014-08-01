package example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sage.database.facade.BaseItem;

@DatabaseTable
public class MyContact extends BaseItem
{
	@DatabaseField(generatedId = true)
	public int id;
	
	@DatabaseField
	public String Name;

	public void setName(String name)
	{
		this.Name = name;
		notifyChanged();
	}

	public MyContact()
	{}
}
