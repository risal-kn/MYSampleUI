package com.example.risalkn.mysampleui.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import com.example.risalkn.mysampleui.R;

/**
 * Created by risal.kn on 10/6/2016.
 */
public class MeterWidget extends View {
    //circle maintednd proprties
    private float circleVale;
    private float radious;
    private float thikness;

    //Text properties
    private String text_Vale;
    private  int text_Size;
    //paints for diffrent draw
    private Paint onMarkPaint;
    private float text_X;
    private float text_Y;

   //differnt color local variable
    private int out_Circle_Filed_Color = Color.argb(255, 0xff, 0xA5, 0x00);
    private int in_Circle_Filed_Color = Color.argb(255,0x3e,0x3e,0x3e);

    public MeterWidget(Context context) {
        super(context);
    }

    public MeterWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.MeterWidget,
                0, 0);
        try {
            circleVale = a.getFloat(R.styleable.MeterWidget_circle_vale, 0);
            radious = a.getFloat(R.styleable.MeterWidget_radius, 0);
            thikness = a.getFloat(R.styleable.MeterWidget_thikness, 0);
            out_Circle_Filed_Color=a.getColor(R.styleable.MeterWidget_out_circle_fill_color,out_Circle_Filed_Color);
            in_Circle_Filed_Color=a.getColor(R.styleable.MeterWidget_in_circle_fill_color,in_Circle_Filed_Color);
            text_Size=a.getInteger(R.styleable.MeterWidget_setDigreeTextSize,0);
            text_Vale=a.getText(R.styleable.MeterWidget_setDigreeText).toString();
            text_X=a.getFloat(R.styleable.MeterWidget_setDigreeTextX,text_X);
            text_Y=a.getFloat(R.styleable.MeterWidget_setDigreeTextY,text_Y);
        }
        finally
        {
            a.recycle();
        }
        initDrawingTools();
    }

    private void initDrawingTools() {
        onMarkPaint = new Paint();
        onMarkPaint.setStyle(Paint.Style.STROKE);
        onMarkPaint.setColor(Color.GREEN);
        onMarkPaint.setStrokeWidth(5f);
        onMarkPaint.setShadowLayer(0f, 0f, 0f, out_Circle_Filed_Color);
        onMarkPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            drawCircleWithInside(canvas);
            OutLine(canvas);
    }

    private void drawCircleWithInside(Canvas canvas){


        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(out_Circle_Filed_Color);
        paint.setStrokeWidth(thikness);
        canvas.drawCircle(getHeight()/2,getWidth()/2, radious, paint);
        Log.d("height",""+getHeight());
        //indie circle
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(in_Circle_Filed_Color);
        paint.setStrokeWidth(thikness);
        canvas.drawCircle(getHeight()/2,getWidth()/2, radious-10, paint);
        //text add
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(text_Size);
        canvas.drawText(text_Vale,(float) (getHeight()/text_X),(float)(getWidth()/text_Y), paint);

    }

    private void OutLine(Canvas canvas){
        // onPath.reset();
         RectF ovalvalues = new RectF();
        Path path=new Path();
        ovalvalues.set(getLeft()+120,getTop()+120,getRight()-120,getBottom()-120);
        for(int j=-180;j<=180;j++)
        {
            path.addArc(ovalvalues,j, 1f);
            j=j+2;
        }
        Paint paintdies = new Paint();
        paintdies.setStyle(Paint.Style.STROKE);
        paintdies.setColor(Color.RED);
        paintdies.setStrokeWidth(100f);
//        paintdies.setst
        paintdies.setAntiAlias(true);
        canvas.drawPath(path, paintdies);
    }
}
