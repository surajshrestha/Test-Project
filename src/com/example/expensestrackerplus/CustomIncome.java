package com.example.expensestrackerplus;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomIncome extends BaseAdapter {

	Context context;
	ArrayList<String> incomeList = new ArrayList<String>();
	ArrayList<String> incomeAmount = new ArrayList<String>();
	LayoutInflater inflater;

	public CustomIncome(Context ctx, ArrayList<String> income_List,
			ArrayList<String> income_Amount) {
		this.context = ctx;
		this.incomeList = income_List;
		this.incomeAmount = income_Amount;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
/*	public void makechanges(ArrayList<String> income_List,
			ArrayList<String> income_Amount){

		this.incomeList = income_List;
		this.incomeAmount = income_Amount;
		notifyDataSetChanged();
		
		
	}*/

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return incomeList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int postion, View view, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		ViewHolder holder = new ViewHolder();

		if (view == null) {

			view = inflater.inflate(R.layout.customincomelist, null);
			holder = new ViewHolder();

			holder.txt1 = (TextView) view.findViewById(R.id.textView1);
			holder.txt2 = (TextView) view.findViewById(R.id.textView2);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		String incomeItem = incomeList.get(postion);
		if (incomeItem != null) {
			if (holder.txt1 != null) {
				// set the item name on the TextView
				holder.txt1.setText(incomeItem);
			}

		}
		String amountItem = incomeAmount.get(postion);
		if (amountItem != null) {
			if (holder.txt2 != null) {
				// set the item name on the TextView
				holder.txt2.setText(amountItem);
			}

		}
		return view;
	}

	public static class ViewHolder {
		TextView txt1;
		TextView txt2;
	}

}
