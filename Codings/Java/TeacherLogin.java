package com.example.attendance;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherLogin extends Activity {
	EditText e1,e2;
	Button b1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teacher_login);
		b1=(Button)findViewById(R.id.button1);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String s1=e1.getText().toString();
				String s2=e2.getText().toString();
				if (s1.equals("HOD")&& s2.equals("7116"))
				{
					Toast.makeText(getApplicationContext(), "Success",Toast.LENGTH_LONG).show();	
					Intent k=new Intent(TeacherLogin.this,AddFiles.class);
					k.putExtra("username",s1);
					startActivity(k);
					
				} 
				else
				{
					Toast.makeText(getApplicationContext(), "Incorrect Username or Password",Toast.LENGTH_LONG).show();

				}

			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.teacher_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent h=new Intent(TeacherLogin.this,MainActivity.class);
			startActivity(h);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
