package me.biubiubiu.justifytext.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ccheng on 3/18/14.
 */
public class JustifyTextView extends TextView {

    private int mLineY;
    private int mViewWidth;
    private int start;
    private int end;
    Canvas  mc;

    public JustifyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void setInt(int s,int e){
        start = s;
        end = e;
    }

    @Override
    public void onDraw(Canvas canvas) {
        mc = canvas;
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        paint.drawableState = getDrawableState();
        mViewWidth = getMeasuredWidth();
//        String text = (String) getText();
        //text
        SpannableString spanText =  new SpannableString(getText());
        mLineY = 0;
        mLineY += getTextSize();
        Layout layout = getLayout();
        for (int i = 0; i < layout.getLineCount(); i++) {
            int lineStart = layout.getLineStart(i);
            int lineEnd = layout.getLineEnd(i);
//            String line = text.substring(lineStart, lineEnd);
            //text
            CharSequence spanLine = (CharSequence) spanText.subSequence(lineStart, lineEnd);

//            float width = StaticLayout.getDesiredWidth(text, lineStart, lineEnd, getPaint());
            //text
            float spanWidth = StaticLayout.getDesiredWidth(spanText, lineStart, lineEnd, getPaint());

//            if (needScale(line)) {
//                drawScaledText(canvas, lineStart, line, width);
//            } else {
//                canvas.drawText(line, 0, mLineY, paint);
//            }
            //text
            if (needScale(spanLine)) {
                drawScaledText(canvas, lineStart, spanLine, spanWidth);
            } else {
                canvas.drawText(spanLine, lineStart, lineEnd, 0, mLineY, paint);

                Layout layout11 = getLayout();
                int line = layout11.getLineForVertical(end);
                int characterOffset = layout11.getOffsetForHorizontal(line, start);
                int characterOffset11 = layout11.getOffsetForHorizontal(line, 0);
                String c = "h";
                float cw11 = StaticLayout.getDesiredWidth(c, getPaint());
                float qqq = (characterOffset-characterOffset11)*cw11;
//                canvas.drawText("cannot", lineStart, lineEnd, 0, mLineY, paint);
                canvas.drawText("cannot", cw11, (line+1)*getLineHeight(), getPaint());

            }
            mLineY += getLineHeight();
        }
    }

    private void drawScaledText(Canvas canvas, int lineStart, CharSequence line, float lineWidth) {
        float x = 0;
        if (isFirstLineOfParagraph(lineStart, line)) {
            String blanks = "  ";
            canvas.drawText(blanks, x, mLineY, getPaint());
            float bw = StaticLayout.getDesiredWidth(blanks, getPaint());
            x += bw;

            line = line.subSequence(3,line.length()-1);
        }

        float d = (mViewWidth - lineWidth) / line.length() - 1;
        for (int i = 0; i < line.length(); i++) {
            String c = String.valueOf(line.charAt(i));
            float cw = StaticLayout.getDesiredWidth(c, getPaint());
            canvas.drawText(c, x, mLineY, getPaint());
            x += cw + d;
        }
    }

    private void drawScaledText(Canvas canvas, int lineStart, String line, float lineWidth) {
        float x = 0;
        if (isFirstLineOfParagraph(lineStart, line)) {
            String blanks = "  ";
            canvas.drawText(blanks, x, mLineY, getPaint());
            float bw = StaticLayout.getDesiredWidth(blanks, getPaint());
            x += bw;

            line = line.substring(3);
        }

        float d = (mViewWidth - lineWidth) / line.length() - 1;
        for (int i = 0; i < line.length(); i++) {
            String c = String.valueOf(line.charAt(i));
            float cw = StaticLayout.getDesiredWidth(c, getPaint());
            canvas.drawText(c, x, mLineY, getPaint());
            x += cw + d;
        }
    }

    private boolean isFirstLineOfParagraph(int lineStart, String line) {
        return line.length() > 3 && line.charAt(0) == ' ' && line.charAt(1) == ' ';
    }

    private boolean isFirstLineOfParagraph(int lineStart, CharSequence line) {
        return line.length() > 3 && line.charAt(0) == ' ' && line.charAt(1) == ' ';
    }

    private boolean needScale(String line) {
        if (line.length() == 0) {
            return false;
        } else {
            return line.charAt(line.length() - 1) != '\n';
        }
    }

    private boolean needScale(CharSequence line) {
        if (line.length() == 0) {
            return false;
        } else {
            return line.charAt(line.length() - 1) != '\n';
        }
    }

}
