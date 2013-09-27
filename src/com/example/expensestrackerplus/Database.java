package com.example.expensestrackerplus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {

	String id = "category_id";
	String cat_name = "category_name";
	String cat_type = "category_type";
	String cat_amount = "category_amount";
	String cat_des = "category_description";
	String cat_date = "category_date";
	String cat_time = "category_time";

	static String db_name = "db_ExpensesTracker";
	String tbl_name = "tbl_category";
	String tbl_name1 = "tbl_description";
	static int db_version = 9;

	static String db_create = "create table tbl_category (category_id integer primary key autoincrement, "
			+ "category_name text not null , category_type text not null, category_amount text not null);";

	static String db_create1 = "create table tbl_description (category_id integer, "
			+ "category_name text not null,category_description text not null,category_amount text not null, category_date text not null, category_time text not null);";

	Context context;
	

	static DatabaseHelper dbhelper;
	SQLiteDatabase db;

	public Database(Context ctx) {
		this.context = ctx;
		dbhelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, db_name, null, db_version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			try {
				db.execSQL(db_create);
			} catch (SQLException e) {
				e.printStackTrace();

			}
			try {
				db.execSQL(db_create1);
			} catch (Exception ex) {
				System.out.println("Error" + ex.getMessage());
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
			db.execSQL("DROP TABLE IF EXISTS tbl_category");
			db.execSQL("DROP TABLE IF EXISTS tbl_description");
			onCreate(db);
		}

	}

	public void open() {
		db = dbhelper.getWritableDatabase();
	}

	public void close() {
		dbhelper.close();
	}

	public long insertRecord(String name, String type, String amount) {
		ContentValues value = new ContentValues();
		value.put(cat_name, name);
		value.put(cat_type, type);
		value.put(cat_amount, amount);
		return db.insert(tbl_name, null, value);
	}

	public long insertRecord1(String inc_id, String name, String description,
			String amount, String date, String time) {
		ContentValues value1 = new ContentValues();
		value1.put(id, inc_id);
		value1.put(cat_name, name);
		value1.put(cat_des, description);
		value1.put(cat_amount, amount);
		value1.put(cat_date, date);
		value1.put(cat_time, time);

		return db.insert(tbl_name1, null, value1);
	}

	public Cursor getIncome() {

		return db.query(tbl_name, new String[] { id, cat_name, cat_type,
				cat_amount }, null, null, null, null, null);
	}

	public Cursor getIncomeDescription() {

		return db.query(tbl_name, new String[] { id, cat_name }, null, null,
				null, null, null);
	}

}
