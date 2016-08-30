package hp.demo.fastdraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FileActivity extends ActionBarActivity {

	EditText editor, fname;
	Button save, open;
	String fileList[];
	AlertDialog.Builder listFile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);
		editor = (EditText)findViewById(R.id.editor);
		fname = (EditText)findViewById(R.id.fname);
		save = (Button)findViewById(R.id.save);
		open = (Button)findViewById(R.id.open);
		listFile = new AlertDialog.Builder(FileActivity.this);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				File mySDCard = Environment.getExternalStorageDirectory(); //new File("/sdcard");
				File myFolder = new File(mySDCard, "FastDraft");
				if(!myFolder.exists())
					myFolder.mkdir();
				saveFile(myFolder);		
			}
		});
		
		open.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File mySDCard = Environment.getExternalStorageDirectory();
				File myFolder = new File(mySDCard, "FastDraft");
				if(myFolder.exists()) {
					fileList = myFolder.list();
					if(!(fileList.length > 0))
						Toast.makeText(FileActivity.this, "No files found!", Toast.LENGTH_SHORT);
					listFile.setItems(fileList, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							File mySDCard = Environment.getExternalStorageDirectory(); //new File("/sdcard");
							String item = fileList[which];
							File myFolder = new File(mySDCard, "FastDraft");
							openFile(myFolder, item);
							dialog.dismiss();
						}
					});
					listFile.show();
				}
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
			FileActivity.this.finish();
		return super.onKeyDown(KeyEvent.KEYCODE_BACK, event);
	}
	
	public void saveFile(File myFolder) {		
		String fileName = fname.getText().toString().trim();
		File myFile = new File(myFolder, fileName+".txt");
		if(myFile.exists())
			Toast.makeText(FileActivity.this, "File exists!\nOverwriting file....", Toast.LENGTH_SHORT).show();
		try {
			FileOutputStream fos = new FileOutputStream(myFile);
			String data = editor.getText().toString().trim();
			byte bytes[] = data.getBytes();
			fos.write(bytes);
			fos.flush();
			fos.close();
			Toast.makeText(FileActivity.this, "File saved to "+myFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
		} catch(IOException e) {
			Toast.makeText(FileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	public void openFile(File myFolder, String item) {		
		File myFile = new File(myFolder, item);
		item = item.trim().replace('.', ' ');
		if(myFile.exists()) {
			Toast.makeText(FileActivity.this, "File Opened", Toast.LENGTH_SHORT).show();
			try {
				FileInputStream fis = new FileInputStream(myFile);
				String data = "";
				int b;
				while((b = fis.read()) > -1)
					data += (char)b;
				editor.setText(data);
				fname.setText(item.split(" ")[0]);
				fis.close();
			} catch(IOException e) {
				Toast.makeText(FileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file, menu);
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
