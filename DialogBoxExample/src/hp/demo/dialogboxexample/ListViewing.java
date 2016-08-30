package hp.demo.dialogboxexample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewing extends ListActivity {
	
	String arr[] = {"No.7!","Pondra","Seniors","Pen Handler","Midnight Scope","X-Ray Vision","Bhaat Boka Skill","Home Fantasy", "Neighbourhood Love"};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> ob = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
		setListAdapter(ob);
	}
	
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id); 
		Toast.makeText(getApplicationContext(), l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
	}

}
