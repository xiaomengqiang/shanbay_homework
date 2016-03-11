package com.wangqiang.shanbayhomework.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.wangqiang.greendao.DaoSession;
import com.wangqiang.greendao.LessonInfo;
import com.wangqiang.greendao.LessonInfoDao;
import com.wangqiang.greendao.WordInfo;
import com.wangqiang.greendao.WordInfoDao;
import com.wangqiang.shanbayhomework.R;
import com.wangqiang.shanbayhomework.bean.PositionBean;
import com.wangqiang.shanbayhomework.util.DataBaseManager;
import com.wangqiang.shanbayhomework.util.PromptDialogUtil;
import com.wangqiang.shanbayhomework.util.TextJustification;
import com.wangqiang.shanbayhomework.view.TagImageSpan;

public class MainActivity extends Activity implements OnClickListener{
    private TextView tvContent,tvTitle,tvHead;
    private Button btnHighLight, btnChooseLevel;
    private ImageButton btnBack;
    private PromptDialogUtil promptDialogUtil;
    private Map<String, List<PositionBean>> map;
    private String string,justedStr;
    private LessonInfo currentLessonInfo;
    private LessonInfoDao lessonInfoDao;
    private WordInfoDao wordInfoDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
    }

    private void initDate() {
        DaoSession mDaoSession = DataBaseManager.getInstance().getDaoSession();
        lessonInfoDao = mDaoSession.getLessonInfoDao();
        wordInfoDao = mDaoSession.getWordInfoDao();
        Bundle bundle = getIntent().getExtras();
        tvTitle.setText(bundle.getString("lesson"));
        currentLessonInfo = lessonInfoDao.queryBuilder().where(LessonInfoDao.Properties.LessonNo.eq(bundle.getString("lesson"))).list().get(0);
        String[] titleStr = currentLessonInfo.getTitle().split("\\s+");
        tvHead.setText(currentLessonInfo.getTitle());
        List<WordInfo> wordsList = wordInfoDao.queryBuilder().where(WordInfoDao.Properties.LessonNo.eq(bundle.getString("lesson"))).list();
        map = new HashMap<String, List<PositionBean>>();
        for (WordInfo wordInfo:wordsList){
            map.put(wordInfo.getContent(), new ArrayList<PositionBean>());
        }
        string = currentLessonInfo.getContent();
        tvContent.setText(string);
    }

    private void search(String string) {
        for (String key:map.keySet()){
            Pattern p = Pattern.compile(key);
            Matcher m = p.matcher(string);
            while (m.find()) {
                PositionBean pb = new PositionBean(m.start(), m.end());
                map.get(key).add(pb);
            }
        }
    }

    private void initView() {
        promptDialogUtil = new PromptDialogUtil(MainActivity.this);
        tvContent = (TextView) findViewById(R.id.tvContent);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvHead = (TextView) findViewById(R.id.tvHead);
        tvHead.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP){
                    tvContent.setText(justedStr);
                    if (HighClickWord((TextView) v, event,tvHead.getText().toString())) return true;
                }
                return true;
            }
        });
        tvContent.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP){
                    String headStr = tvHead.getText().toString();
                    tvHead.setText(headStr);
                    if (HighClickWord((TextView) v, event,justedStr)) return true;
                }
                return true;
            }
        });
        tvContent.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        TextJustification.justify(tvContent, tvContent.getMeasuredWidth());
                        justedStr = tvContent.getText().toString();
                        search(justedStr);
                        tvContent.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnHighLight = (Button) findViewById(R.id.btnHighLight);
        btnHighLight.setOnClickListener(this);
        btnChooseLevel = (Button) findViewById(R.id.btnChooseLevel);
        btnChooseLevel.setOnClickListener(this);
    }

    private boolean HighClickWord(TextView v, MotionEvent event,String contentStr){
        Layout layout = v.getLayout();
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (layout != null) {
            try {
                int line = layout.getLineForVertical(y);
                int characterOffset = layout.getOffsetForHorizontal(line, x);
                int temp = characterOffset;
                SpannableString ss = new SpannableString(contentStr);
                String str = ss.toString();
                while (Character.isLetter(str.charAt(characterOffset))) {
                    characterOffset--;
                    if (characterOffset == -1) {
                        break;
                    }
                }
                while (Character.isLetter(str.charAt(temp))) {
                    temp++;
                }
                if (characterOffset == temp) {
                    Log.e("index", characterOffset + "+" + str.charAt(characterOffset));
                    return true;
                }
                ss.setSpan(new TagImageSpan(0, 0),characterOffset+1, temp,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                v.setText(ss);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnHighLight:
                SpannableString s = new SpannableString(justedStr);
                for (String key:map.keySet()){
                    WordInfo wordInfo = wordInfoDao.queryBuilder().where(WordInfoDao.Properties.Content.eq(key)).list().get(0);
                    if (Integer.parseInt(wordInfo.getLevel())<=promptDialogUtil.getLevel()){
                        ArrayList<PositionBean> array = (ArrayList<PositionBean>) map
                                .get(key);
                        for (int i = 0; i < array.size(); i++) {
                            s.setSpan(new TagImageSpan(0,0), array.get(i)
                                            .getStart(), array.get(i).getEnd(),
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                    }
                }
                tvContent.setText(s);
                break;
            case R.id.btnChooseLevel:
                promptDialogUtil.showDialog();
                break;
        }
    }
}
