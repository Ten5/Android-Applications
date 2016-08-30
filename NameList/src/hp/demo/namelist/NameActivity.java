package hp.demo.namelist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NameActivity extends ActionBarActivity {

	EditText name;
	Button add, show;
	SQLiteDatabase db;
	String items[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_name);
		name = (EditText)findViewById(R.id.editText1);
		add = (Button)findViewById(R.id.button1);
		show = (Button)findViewById(R.id.button2);
		
		db = openOrCreateDatabase("NameDatabase", 0, null);
		try {
			db.execSQL(
					"create table Names ("
					+ "name varchar(30) primary key"
					+ ");"
					);
			Toast.makeText(NameActivity.this, "Table Created!", Toast.LENGTH_SHORT).show();
		} catch(Exception e) {
			Toast.makeText(NameActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				String user = name.getText().toString().trim();
				String sql = "insert into Names values('" + user + "');";
				db.execSQL(sql);
				Toast.makeText(NameActivity.this, "Name Entered Successfully!", Toast.LENGTH_SHORT).show();
			}
		});
		
		show.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder list = new AlertDialog.Builder(NameActivity.this);
				String sql = "select * from Names";
				int i = 0;
				Cursor c = db.rawQuery(sql, null);
				items = new String[c.getCount()];
				while(i < c.getCount()) {
					c.moveToNext();					
					items[i++] = c.getString(0);
					Toast.makeText(NameActivity.this, c.getString(0), Toast.LENGTH_SHORT).show();
					//NameActivity.this.finish();
				}
				if(c.getCount()>0) {
					list.setTitle("List of Names:");
					list.setItems(items, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(NameActivity.this, items[which], Toast.LENGTH_SHORT).show();
						}
					});
					//list.show();
					Intent intent = new Intent(NameActivity.this, NameList.class);
					intent.putExtra("list", items);
					startActivity(intent);
				}
				else
					Toast.makeText(NameActivity.this, "No names!", Toast.LENGTH_SHORT).show();
			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.name, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
