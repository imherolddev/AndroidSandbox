package com.imherolddev.androidsandbox.demo_activities.device_motion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.imherolddev.androidsandbox.R;
import com.imherolddev.androidsandbox.demo_activities.device_motion.Circle;

/**
 * Created by imherolddev on 8/23/2014.
 */
public class CustomCanvas extends View {

    private int width, height;
    private Circle circle;
    private Paint paint = new Paint();

    private Handler handler;
    private Runnable runnable;

    private final int FRAME_RATE = 16;

    public CustomCanvas(Context context,AttributeSet attrs) {

        super(context, attrs);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                moveCircle(circle);
            }
        };

    }

    @Override
    protected void onDraw(Canvas canvas) {

        handler.postDelayed(runnable, this.FRAME_RATE);
        paint.setColor(getResources().getColor(R.color.black));
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
        invalidate();

    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);

        this.width = w;
        this.height = h;

        circle = new Circle(w/2, h/2, w/50, 0, 0);

    }

    private synchronized void moveCircle(Circle circle) {

        if (circle.getX() > this.getXBoundary(circle.getRadius())) {

            circle.setxVelocity(-1);

        } else if (circle.getX() < circle.getRadius()) {

            circle.setxVelocity(1);

        }

        if (circle.getY() > this.getYBoundary(circle.getRadius())) {

            circle.setyVelocity(-1);

        } else if (circle.getY() < circle.getRadius()) {

            circle.setyVelocity(1);

        }

        circle.setX(circle.getX() + circle.getxVelocity());
        circle.setY(circle.getY() + circle.getyVelocity());

    }

    public synchronized void setCircleVelocities(int xVelocity, int yVelocity) {

        circle.setxVelocity(xVelocity);
        circle.setyVelocity(yVelocity);

    }

    private int getXBoundary(int radius) {
        return width - radius;
    }

    private int getYBoundary(int radius) {
        return height - radius;
    }

}