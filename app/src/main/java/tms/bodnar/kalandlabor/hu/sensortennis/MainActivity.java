package tms.bodnar.kalandlabor.hu.sensortennis;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private Sensor sensor;
    private SensorManager mgr;
    private SensorTennisView sensorTennisView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorTennisView = new SensorTennisView((this));
        sensorTennisView.setBackgroundColor(Color.BLACK);
        setContentView(sensorTennisView);

    /*    mgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
*/
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

  /*  @Override
    protected void onPause() {
        super.onPause();
        mgr.unregisterListener(bouncingBallView);
    }

    @Override
    protected void onResume() {
        super.onResume();
      mgr.registerListener(bouncingBallView, sensor, SensorManager.SENSOR_DELAY_GAME);
  }*/
}
