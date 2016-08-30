package hp.demo.databaseexample;

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
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	Button login;
	EditText uName, pass;
	TextView regn;
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		login = (Button)findViewById(R.id.login);
		uName = (EditText)findViewById(R.id.uname);
		pass = (EditText)findViewById(R.id.pass);
		regn = (TextView)findViewById(R.id.regn);
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String user = uName.getText().toString().trim();
				String password = pass.getText().toString().trim();
				String sql = "select * from Login where uname='" + user + "' and pass='" + password + "'";
				Cursor c = db.rawQuery(sql, null);
				if(c.moveToNext()) {
					String temp = c.getString(0) + " " + c.getString(1);
					Intent i = new Intent(LoginActivity.this, HomeActivity.class);
					i.putExtra("name", temp);
					startActivity(i);					
					LoginActivity.this.finish();
				}
				else
					Toast.makeText(LoginActivity.this, "Invalid Values!", Toast.LENGTH_SHORT).show();
			}
		});
		
		regn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
				startActivity(i);
			}
		});
		
		db = openOrCreateDatabase("MyDatabase", 0, null);
		try {
			db.execSQL(
					"create table Login ( "
					+ "fname varchar(30), "
					+ "lname varchar(30), "
					+ "uname varchar(30) primary key, "
					+ "pass varchar(10) "
					+ ");" 
					);
			Toast.makeText(LoginActivity.this, "Table Created!", Toast.LENGTH_SHORT).show();
		} catch(Exception e) {
			Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
