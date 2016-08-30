package hp.demo.databaseexample;

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

public class RegistrationActivity extends ActionBarActivity {

	EditText fname, lname, uname, pass;
	Button regn;
	SQLiteDatabase db;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_activity);
		fname = (EditText)findViewById(R.id.firstname);
		lname = (EditText)findViewById(R.id.lastname);
		uname = (EditText)findViewById(R.id.username);
		pass = (EditText)findViewById(R.id.password);
		regn = (Button)findViewById(R.id.register);
		
		db = openOrCreateDatabase("MyDatabase", 0, null);
		
		regn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = fname.getText().toString().trim();
				String lastName = lname.getText().toString().trim();
				String username = uname.getText().toString().trim();
				String password = pass.getText().toString().trim();
				String sql = "insert into Login values('" + name + "', '" + lastName + "', '" + username + "', '" + password + "');";
				db.execSQL(sql);
				Toast.makeText(RegistrationActivity.this, "Registered Sucessfully!", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
}
