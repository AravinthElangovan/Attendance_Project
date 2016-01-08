package com.example.attendance;


import com.example.attendance.Student;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddFiles extends Activity implements OnClickListener{
TextView e1;
Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
EditText sqlName, sqlCourse, sqlSearch,att,regno;
String matt;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSQLUpdate:
			
			boolean didItWork = true;
			try {
			String name = sqlName.getText().toString();
			String course = sqlCourse.getText().toString();
	String attend=att.getText().toString();	
	String reg=regno.getText().toString();
			Student entry = new Student(AddFiles.this);
			
			entry.open();
			entry.createEntry(name,course,attend,reg);
			
			entry.close();
			}catch(Exception e){
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("SQL");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}finally{
				if(didItWork){
					Dialog d = new Dialog(this);
					d.setTitle("SQL");
					TextView tv = new TextView(this);
					tv.setText("Record Successlly saved.");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
		case R.id.bSQLopenView:
			Intent myIntent = new Intent(AddFiles.this,ShowView.class);
			startActivity(myIntent);
			break;
		case R.id.bSearch:
			try{
				String s = sqlSearch.getText().toString();
				long l = Long.parseLong(s);
				Student stud = new Student(this);
				stud.open();
				String returnedName = stud.getName(l);
				String returnedCourse = stud.getCourse(l);
		String returnedatt=stud.getAttendance(l);
		String returnedreg=stud.getreg(l);
				stud.close();
			
				sqlName.setText(returnedName);
				sqlCourse.setText(returnedCourse);
				att.setText(returnedatt);
				regno.setText(returnedreg);
			}catch(Exception e){
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("SQL");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
		case R.id.bEdit:
			try{
				String mName = sqlName.getText().toString();
				String mCourse = sqlCourse.getText().toString();
				String matt=att.getText().toString();
				String sRow = sqlSearch.getText().toString();
				long lRow = Long.parseLong(sRow);
				Student ex = new Student(this);
				ex.open();
				ex.updateEntry(lRow, mName, mCourse,matt);
			
				ex.close();
			}catch(Exception e){
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("SQL");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}	
			break;
		
		case R.id.bDelete:
			try{
				String sRow1 = sqlSearch.getText().toString();
				long lRow1 = Long.parseLong(sRow1);
				Student ex1 = new Student(this);
				ex1.open();
				ex1.deleteEntry(lRow1);
			
				ex1.close();
			}catch(Exception e){
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("SQL");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
		}
	}
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_files);
		e1=(TextView)findViewById(R.id.textView2);
		e1.setText(getIntent().getStringExtra("username"));
		sqlUpdate =(Button)  findViewById(R.id.bSQLUpdate);
		sqlName =(EditText) findViewById(R.id.etSQLName);
		sqlCourse =(EditText) findViewById(R.id.etSQLCourse);
		att=(EditText)findViewById(R.id.editText1);
		sqlView=(Button) findViewById(R.id.bSQLopenView);
		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
		regno=(EditText)findViewById(R.id.editText2);
		sqlSearch = (EditText) findViewById(R.id.etSearchId);
		sqlModify =(Button) findViewById(R.id.bEdit);
		sqlDelete =(Button) findViewById(R.id.bDelete);
		sqlGetInfo = (Button) findViewById(R.id.bSearch);
		sqlGetInfo.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_files, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			Intent r=new Intent(AddFiles.this,MainActivity.class);
			startActivity(r);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
