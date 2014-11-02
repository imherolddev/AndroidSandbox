package com.imherolddev.androidsandbox.demo_activities.device_motion;

/**
 * Created by imherolddev on 8/23/2014.
 */
public class Circle {

    private int x;
    private int y;
    private int radius;
    private int xVelocity;
    private int yVelocity;

    Circle (int x, int y, int radius, int xVelocity, int yVelocity) {

        this.setX(x);
        this.setY(y);
        this.setRadius(radius);
        this.setxVelocity(xVelocity);
        this.setyVelocity(yVelocity);

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

}
