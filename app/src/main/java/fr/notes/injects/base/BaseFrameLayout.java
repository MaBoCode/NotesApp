package fr.notes.injects.base;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import org.androidannotations.annotations.EViewGroup;

import javax.inject.Inject;

import fr.notes.injects.bus.AppBus;

@EViewGroup
public abstract class BaseFrameLayout extends FrameLayout {

    @Inject
    protected AppBus bus;

    protected Context appContext;
    protected Context uiContext;

    public BaseFrameLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public abstract void inject();

    private void init(Context context) {
        this.appContext = context.getApplicationContext();
        this.uiContext = context;
        if (!isInEditMode()) {
            inject();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            bus.register(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (!isInEditMode()) {
            bus.unregister(this);
        }
        super.onDetachedFromWindow();
    }
}
