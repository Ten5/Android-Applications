package hp.demo.namelist;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class NameList extends ListActivity {
	
	String arr[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		arr = i.getStringArrayExtra("list");
		ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
		setListAdapter(array);
	}
}
