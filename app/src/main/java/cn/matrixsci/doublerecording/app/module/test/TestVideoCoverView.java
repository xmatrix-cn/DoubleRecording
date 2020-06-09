package cn.matrixsci.doublerecording.app.module.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class TestVideoCoverView extends View {

    public TestVideoCoverView(Context context, AttributeSet attrs) {

        super(context,attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
         * 方法 说明 drawRect 绘制矩形 drawCircle 绘制圆形 drawOval 绘制椭圆 drawPath 绘制任意多边形
         * drawLine 绘制直线 drawPoin 绘制点
         */
        // 创建画笔
        Paint p = new Paint();
        p.setColor(Color.RED);// 设置红色
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(8.0f);
//        canvas.drawText("画圆：", 10, 20, p);// 画文本
//        canvas.drawCircle(60, 20, 10, p);// 小圆
//        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除。
        int width = getWidth();
        int height = getHeight();
        canvas.drawRect(new Rect(width/2-width/6,height/2-height/6,width/2+width/6,height/2+height/6),p);

    }
}
