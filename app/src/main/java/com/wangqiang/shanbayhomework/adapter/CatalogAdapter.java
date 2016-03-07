package com.wangqiang.shanbayhomework.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.wangqiang.shanbayhomework.R;

public class CatalogAdapter extends BaseExpandableListAdapter{

	private List<String> unit;
	private Map<String, List<String>> map;
	private Context context;
	public CatalogAdapter(List<String> unit,Map<String, List<String>> map,Context context){
		this.unit = unit;
		this.map = map;
		this.context = context;
	}
	
	@Override
	public int getGroupCount() {
		return unit.size();
	}
	//获取当前父item下的子item的个数
	@Override
	public int getChildrenCount(int groupPosition) {
		return map.get(unit.get(groupPosition)).size();
	}
	//获取当前父item的数据
	@Override
	public Object getGroup(int groupPosition) {
		return unit.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return map.get(unit.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	
	//得到子item的ID
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		 if (convertView == null) {
             LayoutInflater inflater = (LayoutInflater) context
                     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             convertView = inflater.inflate(R.layout.unit_layout, null);
         }
         TextView tv = (TextView) convertView
                 .findViewById(R.id.tvUnit);
         tv.setText(unit.get(groupPosition));
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
        String info = map.get(unit.get(groupPosition)).get(childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lession_layout, null);
        }
        TextView tv = (TextView) convertView
                .findViewById(R.id.tvLession);
        tv.setText(info);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
