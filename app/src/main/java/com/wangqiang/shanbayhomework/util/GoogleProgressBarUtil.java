package com.wangqiang.shanbayhomework.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

import com.jpardogo.android.googleprogressbar.library.ChromeFloatingCirclesDrawable;
import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable;
import com.jpardogo.android.googleprogressbar.library.GoogleMusicDicesDrawable;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;
import com.wangqiang.shanbayhomework.R;

/**
 * Created by wangqiang on 2016/3/10.
 */
public class GoogleProgressBarUtil {
    public static final int FOLDING_CIRCLES = 0;
    public static final int MUSIC_DICES = 1;
    public static final int NEXUS_CROSS_ROTATION = 2;
    public static final int CHROME_FLOATING_CIRCLES = 3;
    public static Drawable getProgressDrawable(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int value = CHROME_FLOATING_CIRCLES;
        Drawable progressDrawable = null;
        switch (value) {
            case FOLDING_CIRCLES:
                progressDrawable = new FoldingCirclesDrawable.Builder(context)
                        .colors(getProgressDrawableColors(context))
                        .build();
                break;

            case MUSIC_DICES:
                progressDrawable = new GoogleMusicDicesDrawable.Builder().build();
                break;

            case NEXUS_CROSS_ROTATION:
                progressDrawable = new NexusRotationCrossDrawable.Builder(context)
                        .colors(getProgressDrawableColors(context))
                        .build();
                break;

            case CHROME_FLOATING_CIRCLES:
                progressDrawable = new ChromeFloatingCirclesDrawable.Builder(context)
                        .colors(getProgressDrawableColors(context))
                        .build();
                break;
        }
        return progressDrawable;
    }
    public static int[] getProgressDrawableColors(Context context) {
            int[] colors = new int[4];
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            colors[0] = prefs.getInt(context.getString(R.string.firstcolor_pref_key),context.getResources().getColor(R.color.red));
            colors[1] = prefs.getInt(context.getString(R.string.secondcolor_pref_key),context.getResources().getColor(R.color.blue));
            colors[2] = prefs.getInt(context.getString(R.string.thirdcolor_pref_key),context.getResources().getColor(R.color.yellow));
            colors[3] = prefs.getInt(context.getString(R.string.fourthcolor_pref_key), context.getResources().getColor(R.color.green));
            return colors;
        }
}
