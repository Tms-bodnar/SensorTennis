package tms.bodnar.kalandlabor.hu.sensortennis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017.01.12..
 */

public class SensorTennisView extends View{

//    private int ballX = 120;
//    private int ballY = 120;
//    private int ballR = 25;

//    private int rect1Left =1;
//    private int rect1Top = 1;
//    private int rect1Right = 21;
//    private int rect1Bottom = 201;

    /*private int rect2Left = 1;
    private int rect2Top = 1;
    private int rect2Right = 1;
    private int rect2Bottom = 201;
*/

    private Rectangle ball;
    private Rectangle racket1;
    private Rectangle racket2;
    private Rectangle touchArea1;
    private Rectangle touchArea2;
    private int ballSpeedX = 8;
    private int ballSpeedY = 8;

    private double rect1SpeedY = 1;
    private double rect2SpeedY = 1;

    private float ballAccelx = 2;
    private float ballAccelY = 2;

    private float rectAccelY = 1;

    private int xMin = 0;
    private int yMin = 0;

    private double res=0;
    private int fail=0;
    private int xMax;
    private  int yMax;

    private int sizeX;
    private int sizeY;
    private double xDivY ;

    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;

//    private Drawable ball;
    private Map<Integer, PointF> circles;


    public SensorTennisView(Context context) {
        super(context);
        paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        paint2 = new Paint();
        paint2.setColor(Color.GRAY);
        paint3 = new Paint();
        paint3.setColor(Color.LTGRAY);
        paint3.setTextSize(80);
        Typeface tf = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        paint3.setTypeface(tf);
        paint3.setTextAlign(Paint.Align.CENTER);
        paint4 = new Paint();
        paint4.setColor(Color.DKGRAY);


//        ball = context.getResources().getDrawable(R.drawable.metalball);

        circles = new HashMap<>();

        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        sizeX = size.x;
        sizeY = size.y;


    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Rect rBall = new Rect(ball.getxLeft(),ball.getyTop(), ball.getxRight(),ball.getyBottom());
//        ball.setBounds(ballX-ballR, ballY-ballR, ballX+ballR, ballY+ballR);
//        ball.draw(canvas);
        canvas.drawRect(rBall, paint1);
        //canvas.drawCircle(ballX,ballY,ballR,paint1);
//        for (PointF p : circles.values()) {
//            canvas.drawCircle(p.x,p.y,ballR,paint1);
//        }
        Rect rRacket1 = new Rect(racket1.getxLeft(),racket1.getyTop(),racket1.getxRight(),racket1.getyBottom());
        canvas.drawRect(rRacket1,paint2);
        Rect rRacket2 = new Rect(racket2.getxLeft(),racket2.getyTop(), racket2.getxRight(),racket2.getyBottom());
        canvas.drawRect(rRacket2, paint2);
        Rect rToucharea1 = new Rect(touchArea1.getxLeft(),touchArea1.getyTop(),touchArea1.getxRight(),touchArea1.getyBottom());
        canvas.drawRect(rToucharea1,paint4);
        Rect rTouchArea2 = new Rect(touchArea2.getxLeft(),touchArea2.getyTop(),touchArea2.getxRight(),touchArea2.getyBottom());
        canvas.drawRect(rTouchArea2, paint4);
        /*rect2Left = sizeX -21;
        rect2Right = sizeX -1;*/
       // canvas.drawRect(rect2Left,rect2Top,rect2Right,rect2Bottom,paint2);
        canvas.drawText(String.valueOf(res), sizeX/2,yMax-10, paint3);



        try{
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        update();
        invalidate();
    }

    private void update() {
//        ballAccelx = 2;
//        ballAccelY = 2;


//       for (PointF p : circles.values()) {
//            ballAccelx += p.x -ballX;
//            ballAccelY += p.x -ballY;
//        }

//        if(circles.size()>0){
//            ballAccelx /= circles.size();
//            ballAccelY /= circles.size();
//
//
//            ballAccelx /=40;
//            ballAccelY /= 40;
//        }
        ballSpeedX += ballAccelx;
        ballSpeedY += ballAccelY;

        ball.setyTop(ball.getyTop()+ballSpeedX);
        ball.setyBottom(ball.getyBottom() + ballSpeedX);
        ball.setxLeft(ball.getxLeft() + ballSpeedY);
        ball.setxRight(ball.getxRight() + ballSpeedY);



        if(ball.getxRight() >= xMax){
            ballSpeedX = - ballSpeedX;
            ball.setxRight(xMax - 1);
            ball.setxLeft(xMax - (ball.getxRight() - ball.getxLeft()));

        }else if (ball.getxLeft()<=xMin + racket1.getxRight()
                && ball.getyTop()> racket1.getyTop()
                && ball.getyBottom()< racket1.getyBottom()){
            ballSpeedX = - ballSpeedX;
            ball.setxLeft(xMin + 1);
            ball.setxRight(xMin + (ball.getxRight() - ball.getxLeft()));
            res += 1;
        }else if (ball.getxLeft() < xMin ){
            fail +=1;
            ball.setxLeft(sizeX/2);
            ball.setxRight(sizeY/2 +100);
        }
        if (ball.getyBottom() >= yMax){
            ballSpeedY *= -1;
            ball.setyBottom(yMax - 1);
            ball.setyTop(yMax - (ball.getyBottom() - ball.getyTop()));

        }else if (ball.getyTop() <= yMin){
            ballSpeedY *= -1;
            ball.setyTop(yMin - 1);
            ball.setyBottom(yMin - (ball.getyBottom() - ball.getyTop()));

        }

        if(circles.size()>0){
            PointF p = circles.get(1);
          //  rectAccelY = p.x - (rect1Top-rect1Bottom);
          //  rectAccelY /=40;
            racket1.setyTop((int)p.y-50);
            racket1.setyTop((int)p.y+50);
        }else{
            rectAccelY = 0;
        }

      //  rect1SpeedY = rectAccelY*10;
        //rect2SpeedY = rectAccelY*10;

        //rect1Top +=rect1SpeedY;
        //rect1Bottom +=rect1SpeedY;

   /*     if(rect1Bottom > yMax-1){
            rect1SpeedY *= -1;
            rect1Bottom = yMax -1;
            rect1Top =yMax -201;
        }else if (rect1Top < yMin-1){
            rect1SpeedY *= -1;
            rect1Top = yMin +1;
            rect1Bottom = yMin +201;

        }*/

       /* rect2Top += rect2SpeedY;
        rect2Bottom += rect2SpeedY;
*/
       /* if(rect2Bottom >= yMax){
            rect2SpeedY *=-1;
            rect2Bottom = yMax -1;
            rect2Top = yMax -201;
        }else if ( rect2Top <= yMin){
            rect2SpeedY *=-1;
            rect2Bottom = yMin +201;
            rect2Top = yMin + 1;
        }*/

    }


    public boolean onTouchEvent(MotionEvent event){

        int action = event.getAction();
        int pointerIndex = event.getActionIndex();


        PointF pt;
        pt = new PointF(event.getX(), event.getY());

        switch (action){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                circles.put(1,pt);
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                circles.remove(1);
                return true;
            case MotionEvent.ACTION_MOVE:

                    pt = new PointF(event.getX(), event.getY());
                    circles.put(1, pt);

                return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        xMax = w-1;
        yMax = h-1;
    }

    /*@Override
    public void onSensorChanged(SensorEvent event) {
        rectAccelY = event.values[0];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }*/
}
