package fr.notes.views.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.androidannotations.annotations.EView;

@EView
public class BaseRenderedTextView extends TextView {

    private OnTextViewRenderedListener mOnTextViewRenderedListener;

    public interface OnTextViewRenderedListener {
        void onTextViewRendered(TextView textView);
    }

    public BaseRenderedTextView(Context context) {
        super(context);
    }

    public BaseRenderedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRenderedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnTextViewRenderedListener(OnTextViewRenderedListener mOnTextViewRenderedListener) {
        this.mOnTextViewRenderedListener = mOnTextViewRenderedListener;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (mOnTextViewRenderedListener != null) {
            mOnTextViewRenderedListener.onTextViewRendered(this);
        }
    }
}
