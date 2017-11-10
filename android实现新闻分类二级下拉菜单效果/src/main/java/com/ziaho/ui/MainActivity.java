package com.ziaho.ui;

import com.cn.main.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;


public class MainActivity extends Activity {
	ExpandableListView mExpandableListView;
	ExpandableListViewAdapter mExpandableListViewAdapter;
	private static final String TAG = "Main";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
		mExpandableListViewAdapter = new ExpandableListViewAdapter(this);
		mExpandableListView.setAdapter(mExpandableListViewAdapter);
		mExpandableListView.expandGroup(0);
		mExpandableListView
				.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
					@Override
					public boolean onGroupClick(ExpandableListView parent,
							View v, int groupPosition, long id) {
						return false;
					}
				});
		mExpandableListView
				.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
					@Override
					public boolean onChildClick(ExpandableListView parent,
							View v, int groupPosition, int childPosition,
							long id) {
						Log.e(TAG, "groupPosition=" + groupPosition
								+ ",childPosition=" + childPosition);
						return false;
					}
				});

	}

}