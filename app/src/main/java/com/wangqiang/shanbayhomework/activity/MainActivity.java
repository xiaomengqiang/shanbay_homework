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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wangqiang.shanbayhomework.R;
import com.wangqiang.shanbayhomework.bean.PositionBean;
import com.wangqiang.shanbayhomework.util.PromptDialogUtil;
import com.wangqiang.shanbayhomework.util.TextJustification;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class MainActivity extends Activity {
    private TextView tvContent;
    private Button btnHighLight, btnChooseLevel;
    private PromptDialogUtil promptDialogUtil;
    private Map<String, List<PositionBean>> map;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
        string = "    We can read of things that happened 5,000 years ago in the Near East, where people first learned to write. But there are some parts of the word where even now people cannot write. The only way that they can preserve their history is to recount it as sagas -- legends handed down from one generation of another. These legends are useful because they can tell us something about migrations of people who lived long ago, but none could write down what they did. Anthropologists wondered where the remote ancestors of the Polynesian peoples now living in the Pacific Islands came from. The sagas of these people explain that some of them came from Indonesia about 2,000 years ago.\n" +
                "    But the first people who were like ourselves lived so long ago that even their sagas, if they had any, are forgotten. So archaeologists have neither history nor legends to help them to find out where the first 'modern men' came from.\n" +
                "    Fortunately, however, ancient men made tools of stone, especially flint, because this is easier to shape than other kinds. They may also have used wood and skins, but these have rotted away. Stone does not decay, and so the tools of long ago have remained when even the bones of the men who made them have disappeared without trace.\n";

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        int width = dm.widthPixels;
        tvContent.setText(string);
        tvContent.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        TextJustification.justify(tvContent, tvContent.getMeasuredWidth());
                        String str = tvContent.getText().toString();
                        search(str);
                        tvContent.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });
    }

    private void initDate() {
        Bundle bundle = getIntent().getExtras();
        Toast.makeText(MainActivity.this, bundle.getInt("group") + "and" + bundle.getInt("child"), Toast.LENGTH_SHORT).show();
        map = new HashMap<String, List<PositionBean>>();
        map.put("recount", new ArrayList<PositionBean>());
    }

    private void search(String string) {
        Pattern p = Pattern.compile("recount");
        Matcher m = p.matcher(string);
        while (m.find()) {
            PositionBean pb = new PositionBean(m.start(), m.end());
            map.get("recount").add(pb);
        }
    }

    private void initView() {
        promptDialogUtil = new PromptDialogUtil(MainActivity.this);
        tvContent = (TextView) findViewById(R.id.tvContent);
        tvContent.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP){
                    Layout layout = ((TextView) v).getLayout();
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    if (layout != null) {
                        int line = layout.getLineForVertical(y);
                        int characterOffset = layout.getOffsetForHorizontal(line, x);
                        int temp = characterOffset;
                        SpannableString ss = new SpannableString(tvContent.getText());
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
                            Log.e("index", "" + characterOffset + "+" + str.charAt(characterOffset));
                            return true;//先不考虑一个单词
                        }
                        ss.setSpan(new ForegroundColorSpan(Color.RED), characterOffset, temp,
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        tvContent.setText(ss);
                        Log.e("index", "" + characterOffset + "+" + str.substring(characterOffset + 1, temp));
                    }
                }
                return true;
            }
        });
        btnHighLight = (Button) findViewById(R.id.btnHighLight);
        btnHighLight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                SpannableString s = new SpannableString(tvContent.getText());
                ArrayList<PositionBean> array = (ArrayList<PositionBean>) map
                        .get("recount");
                for (int i = 0; i < array.size(); i++) {
                    s.setSpan(new ForegroundColorSpan(Color.RED), array.get(i)
                                    .getStart(), array.get(i).getEnd(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                tvContent.setText(s);
            }
        });
        btnChooseLevel = (Button) findViewById(R.id.btnChooseLevel);
        btnChooseLevel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                promptDialogUtil.showDialog();
            }
        });
    }

}
