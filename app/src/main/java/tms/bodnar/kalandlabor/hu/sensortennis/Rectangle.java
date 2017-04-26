package tms.bodnar.kalandlabor.hu.sensortennis;

/**
 * Created by user on 2017.01.18..
 */

public class Rectangle {

    int yTop;
    int xLeft;
    int yBottom;
    int xRight;

    public Rectangle() {
    }

    public Rectangle(int xLeft,int yTop, int xRight, int yBottom ) {
        this.yTop = yTop;
        this.xLeft = xLeft;
        this.yBottom = yBottom;
        this.xRight = xRight;
    }

    public int getyTop() {
        return yTop;
    }

    public void setyTop(int yTop) {
        this.yTop = yTop;
    }

    public int getxLeft() {
        return xLeft;
    }

    public void setxLeft(int xLeft) {
        this.xLeft = xLeft;
    }

    public int getyBottom() {
        return yBottom;
    }

    public void setyBottom(int yBottom) {
        this.yBottom = yBottom;
    }

    public int getxRight() {
        return xRight;
    }

    public void setxRight(int xRight) {
        this.xRight = xRight;
    }
}
