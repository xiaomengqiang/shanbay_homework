package com.wangqiang.shanbayhomework.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.widget.ProgressBar;
import com.wangqiang.shanbayhomework.R;
import com.wangqiang.shanbayhomework.util.CreateLessonInfos;
import com.wangqiang.shanbayhomework.util.CreateWordInfos;
import com.wangqiang.shanbayhomework.util.GoogleProgressBarUtil;

public class LauncherActivity extends Activity {
	private ProgressBar pb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		pb  = (ProgressBar)findViewById(R.id.googlePb);
		final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LauncherActivity.this);
		if (sharedPreferences.getBoolean("firstTime",true)){
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					CreateLessonInfos.createLessonInfos();
					CreateWordInfos.createWordInfos();
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putBoolean("firstTime",false);
					editor.commit();
					mHandler.sendEmptyMessage(0);
				}
			}).start();
		}else {
			mHandler.sendEmptyMessage(0);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		Rect bounds = pb.getIndeterminateDrawable().getBounds();
		pb.setIndeterminateDrawable(GoogleProgressBarUtil.getProgressDrawable(LauncherActivity.this));
		pb.getIndeterminateDrawable().setBounds(bounds);
	}

	public Handler mHandler=new Handler()
	{
		public void handleMessage(Message msg)
		{   startActivity(new Intent(LauncherActivity.this,CatalogActivity.class));
			finish();
			super.handleMessage(msg);
		}
	};


}
