package com.example.expensestrackerplus;

import java.util.ArrayList;

import com.commonsware.cwac.merge.MergeAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Income extends Activity {

	Database db = new Database(this);
	long result;
	String incomeList, str_input, incomeAmount, id;
	ArrayList<String> list_Inc = new ArrayList<String>();
	ArrayList<String> list_Id = new ArrayList<String>();
	ArrayList<String> list_Amp = new ArrayList<String>();
	ListView lv_income;
	ImageButton btnCategory;
	int test;
	String amount;
	String[] arr;
	MergeAdapter m;
	CustomIncome ci;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.incomelayout);

		lv_income = (ListView) findViewById(R.id.listView1);
		btnCategory = (ImageButton) findViewById(R.id.imageButton1);
		amount = Double.toString(0.00);

		// insertIncome();
		retrieve();

		btnCategory.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				LayoutInflater li = LayoutInflater.from(Income.this);
				View v = li.inflate(R.layout.incomedialogbox, null);

				AlertDialog.Builder ad = new AlertDialog.Builder(Income.this);
				ad.setView(v);

				final EditText etinputbox = (EditText) v
						.findViewById(R.id.editText1);

				ad.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// get user input and set it to result
										// edit text

										str_input = etinputbox.getText()
												.toString();
										if (str_input.equals("")) {
											System.out.println("ignore");
										} else {
											insertIncome(str_input, "income",
													amount);

										}
									}

								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}

								});

				// create alert dialog
				AlertDialog alertDialog = ad.create();

				// show it

				alertDialog.show();

			}

		});

	}

	public long insertIncome(String name, String type, String amount) {
		db.open();
		db.insertRecord(name, type, amount);
		db.close();

		if (result == -1) {
			System.out.println("failure");
		} else {
			System.out.println("success");
		}

		/*pushchanges();*/

		return result;
	}

/*	public void pushchanges() {
		db.open();

		Cursor c = db.getIncome();

		if (c.moveToFirst()) {
			do {

				id = c.getString(0);
				incomeList = c.getString(1);
				incomeAmount = c.getString(3);
				list_Id.add(id);
				list_Inc.add(incomeList);
				list_Amp.add(incomeAmount);

			} while (c.moveToNext());
		}

		db.close();
		ci.makechanges(list_Id, list_Amp);

	}*/

	public void retrieve() {
		db.open();

		Cursor c = db.getIncome();

		if (c.moveToFirst()) {
			do {

				id = c.getString(0);
				incomeList = c.getString(1);
				incomeAmount = c.getString(3);
				list_Id.add(id);
				list_Inc.add(incomeList);
				list_Amp.add(incomeAmount);

			} while (c.moveToNext());
		}

		db.close();
		ci = new CustomIncome(Income.this, list_Inc, list_Amp);
		lv_income.setAdapter(ci);
		lv_income.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				/*
				 * Toast.makeText(Income.this, list_Id.get(position), 1000)
				 * .show();
				 * 
				 * Toast.makeText(Income.this, list_Inc.get(position), 1000)
				 * .show();
				 */

				Intent i = new Intent(Income.this, Description.class);

				Bundle b = new Bundle();
				b.putString("Id", list_Id.get(position));
				b.putString("Income", list_Inc.get(position));
				i.putExtras(b);

				startActivity(i);

			}

		});

	}

}
