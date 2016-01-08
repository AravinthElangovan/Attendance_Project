package com.example.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Student {
	public static final String KEY_REGID = "_reg";
	public static final String KEY_NAME = "studentname";
	public static final String KEY_COURSE ="course";
	
	private static final String DATABASE_NAME = "Student_DB";
	public static final String DATABASE_TABLE = "tblStudent";
	private static final int DATABASE_VERSION = 1;
	private static final String KEY_ATT = "attend";
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_REGID + " TEXT NOT NULL, " +
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_COURSE+" TEXT NOT NULL, " + KEY_ATT+" TEXT NOT NULL);"		
					);		
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}
	public Student(Context c){
		ourContext =c;
	}
	public Student open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	public void close(){
		
	}
	public long createEntry(String name, String course,String attend,String regnoo) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_COURSE, course);
		cv.put(KEY_ATT, attend);
		cv.put(KEY_REGID, regnoo);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
		
	}
	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_REGID, KEY_NAME, KEY_COURSE,KEY_ATT};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, KEY_REGID);
		String result = "";
	
		int iRow = c.getColumnIndex(KEY_REGID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iCourse = c.getColumnIndex(KEY_COURSE);
		int att=c.getColumnIndex(KEY_ATT);
		
		
		for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			result = result +c.getString(iRow)+ "\t\t\t\t" + c.getString(iName) + "\t\t\t\t\t" + c.getString(iCourse)+"\t\t\t\t\t" + c.getString(att)+"\n";
		}
		return result;
	}
	public String getName(long l) {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_REGID, KEY_NAME, KEY_COURSE,KEY_ATT};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REGID + "=" + l, null, null, null, null);
		if(c != null){
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}
	public String getCourse(long l) {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_REGID, KEY_NAME, KEY_COURSE,KEY_ATT};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REGID + "=" + l, null, null, null, null);
		if(c != null){
			c.moveToFirst();
			String course = c.getString(2);
			return course;
		}
		return null;
	}
	public void updateEntry(long lRow, String mName, String mCourse,String mAtt) {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, mName);
		cvUpdate.put(KEY_COURSE, mCourse);
		cvUpdate.put(KEY_ATT, mAtt);
		
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_REGID + "=" + lRow, null);
		
		
	}
	public String getAttendance(long l) {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_REGID, KEY_NAME, KEY_COURSE,KEY_ATT};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REGID + "=" + l, null, null, null, null);
		if(c != null){
			c.moveToFirst();
			String att = c.getString(3);
			return att;
		}
		return null;
	}
	public String getreg(long l) {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_REGID, KEY_NAME, KEY_COURSE,KEY_ATT};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REGID + "=" + l, null, null, null, null);
		if(c != null){
			c.moveToFirst();
			String course = c.getString(0);
			return course;
		}
		return null;
	}
	public void deleteEntry(long lRow1) {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_REGID + "=" + lRow1, null);
	}
}

