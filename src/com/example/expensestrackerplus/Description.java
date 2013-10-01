package com.example.expensestrackerplus;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Description extends Activity {

	String id, income, des, amount, date, time;
	TextView txtincome;
	EditText et_des, et_amount, et_date, et_time;
	Button btn_submit, btn_view;
	static final int timeDialog = 0;
	static final int dateDialog = 1;
	int hour, minute, yr, month, day;;
	Database db = new Database(Description.this);;
	long result;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.descriptionform);

		txtincome = (TextView) findViewById(R.id.textView2);
		et_des = (EditText) findViewById(R.id.editText1);
		et_amount = (EditText) findViewById(R.id.editText2);
		et_date = (EditText) findViewById(R.id.editText3);
		et_time = (EditText) findViewById(R.id.editText4);
		btn_submit = (Button) findViewById(R.id.button2);
		btn_view = (Button) findViewById(R.id.view);

		Calendar today = Calendar.getInstance();
		yr = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH);
		day = today.get(Calendar.DAY_OF_MONTH);

		Calendar c = Calendar.getInstance();
		int mHour = c.get(Calendar.HOUR);
		int mMinute = c.get(Calendar.MINUTE);

		et_time.setText(mHour + ":" + mMinute);
		et_date.setText(yr + "-" + month + "-" + day);

		Bundle b = new Bundle();
		b = getIntent().getExtras();
		id = b.getString("Id");
		income = b.getString("Income");

		txtincome.setText(income);
		Toast.makeText(this, id, 1000).show();

		et_time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showDialog(timeDialog);

			}

		});

		et_date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(dateDialog);

			}

		});

		btn_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String description = et_des.getText().toString();
				String amount = et_amount.getText().toString();
				String date = et_date.getText().toString();
				String time = et_time.getText().toString();

				db.open();
				result = db.insertRecord1(id, income, description, amount,
						date, time);
				db.close();
				if (result == -1) {
					System.out.println("fail");
				} else {
					System.out.println("pass");
				}

			}

		});

		btn_view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				db.open();
				Toast.makeText(Description.this, income, 1000).show();
				Cursor c = db.getIncomeDescription(income);

				if (c.moveToFirst()) {
					do {

						amount = c.getString(0);
					} while (c.moveToNext());
				}
				db.close();
				if (amount != null) {
					Intent data = new Intent();
					data.setData(Uri.parse(amount));
					setResult(RESULT_OK, data);
					finish();
				}
			}

		});

	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case timeDialog:

			return new TimePickerDialog(this, mTimeSetListener, hour, minute,
					false);
		case dateDialog:
			return new DatePickerDialog(this, mDateSetListener, yr, month, day);
		}
		return null;

	}

	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourofDay, int minuteofHour) {
			// TODO Auto-generated method stub
			hour = hourofDay;
			minute = minuteofHour;

			SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
			Date date = new Date(0, hour, minute);
			String strDate = timeFormat.format(date);
			et_time.setText(strDate);

		}
	};
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthofYear,
				int dayofYear) {
			// TODO Auto-generated method stub
			yr = year;
			month = monthofYear;
			day = dayofYear;
			et_date.setText(yr + ":" + month + ":" + day);
		}
	};

}