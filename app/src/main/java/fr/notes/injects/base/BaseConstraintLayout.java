package fr.notes.injects.base;

import android.content.Context;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import fr.notes.injects.bus.AppBus;

@AndroidEntryPoint
public abstract class BaseConstraintLayout extends ConstraintLayout {

    @Inject
    protected AppBus bus;

    protected Context appContext;
    protected Context uiContext;

    public BaseConstraintLayout(Context context) {
        super(context);
        init(context);
    }

    public BaseConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.appContext = context.getApplicationContext();
        this.uiContext = context;
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
