package com.example.kotlinproject.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.kotlinproject.utils.Utils;
import com.example.kotlinproject.utils.UtilsUI;

@SuppressLint("AppCompatCustomView")
public class ViewRound extends ImageView {

    public float radius = UtilsUI.Companion.get().dp(20.0F);

    public ViewRound(Context context) {
        super(context);
    }

    public ViewRound(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewRound(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        @SuppressLint("DrawAllocation") Path clipPath = new Path();
        @SuppressLint("DrawAllocation") RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}
