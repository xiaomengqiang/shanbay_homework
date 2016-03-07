package com.wangqiang.shanbayhomework.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import com.wangqiang.shanbayhomework.R;
import com.wangqiang.shanbayhomework.adapter.CatalogAdapter;

public class LauncherActivity extends Activity {
	private ExpandableListView exlv;
	private CatalogAdapter catalogAdapter;
	private List<String> unit;
	private Map<String, List<String>> map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		initDate();
		initView();
	}

	private void initView() {
		exlv = (ExpandableListView) findViewById(R.id.exlv);
		catalogAdapter = new CatalogAdapter(unit, map, LauncherActivity.this);
		exlv.setAdapter(catalogAdapter);
		exlv.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent intent = new Intent(LauncherActivity.this,MainActivity.class);
//				intent.putExtra("group", groupPosition);
//				intent.putExtra("child", childPosition);
				Bundle bundle = new Bundle();
				bundle.putInt("group", groupPosition);
				bundle.putInt("child", childPosition);
				intent.putExtras(bundle);
				startActivity(intent);
				return false;
			}
		});
	}

	private void initDate() {
		unit = new ArrayList<String>();
		unit.add("Unit One");
		unit.add("Unit Two");
		unit.add("Unit Three");
		unit.add("Unit Four");
		List<String> unitOne = new ArrayList<String>();
		unitOne.add("lession1");
		unitOne.add("lession2");
		unitOne.add("lession3");
		List<String> unitTwo = new ArrayList<String>();
		unitTwo.add("lession1");
		unitTwo.add("lession2");
		unitTwo.add("lession3");
		List<String> unitThree = new ArrayList<String>();
		unitThree.add("lession1");
		unitThree.add("lession2");
		unitThree.add("lession3");
		List<String> unitFour = new ArrayList<String>();
		unitFour.add("lession1");
		unitFour.add("lession2");
		unitFour.add("lession3");
		map = new HashMap<String, List<String>>();
		map.put("Unit One", unitOne);
		map.put("Unit Two", unitTwo);
		map.put("Unit Three", unitThree);
		map.put("Unit Four", unitFour);
	}

}
