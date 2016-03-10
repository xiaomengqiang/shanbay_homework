package com.wangqiang.shanbayhomework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.wangqiang.greendao.DaoSession;
import com.wangqiang.greendao.LessonInfo;
import com.wangqiang.greendao.LessonInfoDao;
import com.wangqiang.shanbayhomework.R;
import com.wangqiang.shanbayhomework.adapter.CatalogAdapter;
import com.wangqiang.shanbayhomework.util.DataBaseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangqiang on 2016/3/10.
 */
public class CatalogActivity extends Activity{
    private ExpandableListView exlv;
    private CatalogAdapter catalogAdapter;
    private List<String> unit;
    private Map<String, List<String>> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        initDate();
        initView();
    }

    private void initView() {
        catalogAdapter = new CatalogAdapter(unit, map, CatalogActivity.this);
        exlv = (ExpandableListView) findViewById(R.id.exlv);
        exlv.setAdapter(catalogAdapter);
        exlv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(CatalogActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                TextView textView = (TextView)v.findViewById(R.id.tvLession);
                String[] str = textView.getText().toString().split(":");
                bundle.putString("lesson", str[0]);
                intent.putExtras(bundle);
                startActivity(intent);
                return false;
            }
        });
    }

    private void initDate() {
        DaoSession mDaoSession = DataBaseManager.getInstance().getDaoSession();
        LessonInfoDao lessonInfoDao = mDaoSession.getLessonInfoDao();
        List<LessonInfo> all = lessonInfoDao.loadAll();
        unit = new ArrayList<String>();
        map = new HashMap<String, List<String>>();
        for(LessonInfo lessonInfo:all){
            if (!unit.contains(lessonInfo.getUnitNo())){
                unit.add(lessonInfo.getUnitNo());
            }
        }
        for (String unitNo:unit){
            List<LessonInfo> lessonInUnit = lessonInfoDao.queryBuilder().where(LessonInfoDao.Properties.UnitNo.eq(unitNo)).list();
            List<String> lesson = new ArrayList<String>();
            for (int i = 0;i<lessonInUnit.size();i++){
                String[] str = lessonInUnit.get(i).getTitle().split("\\n");
                lesson.add(lessonInUnit.get(i).getLessonNo()+":"+str[0]);
            }
            map.put(unitNo, lesson);
       }
//        List<LessonInfo> lessonInUnitTwo = lessonInfoDao.queryBuilder().where(LessonInfoDao.Properties.UnitNo.eq("Unit 2")).list();
//        unit = new ArrayList<String>();
//        unit.add("Unit 1");
//        unit.add("Unit 2");
//        List<String> unitOne = new ArrayList<String>();
//        for (int i = 0;i<lessonInUnitOne.size();i++){
//            String[] str = lessonInUnitOne.get(i).getTitle().split("\\n");
//            unitOne.add(lessonInUnitOne.get(i).getLessonNo()+":"+str[0]);
//        }
//        List<String> unitTwo = new ArrayList<String>();
//        for (int i = 0;i<lessonInUnitTwo.size();i++){
//            String[] str = lessonInUnitTwo.get(i).getTitle().split("\\n");
//            unitTwo.add(lessonInUnitTwo.get(i).getLessonNo()+":"+str[0]);
//        }
//        map.put("Unit 1", unitOne);
//        map.put("Unit 2", unitTwo);
    }

}
