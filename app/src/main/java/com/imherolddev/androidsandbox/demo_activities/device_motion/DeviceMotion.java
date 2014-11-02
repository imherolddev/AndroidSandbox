package com.imherolddev.androidsandbox.demo_activities.device_motion;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.imherolddev.androidsandbox.R;

public class DeviceMotion extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private long lastUpdate;

    private TextView azimuth;
    private TextView pitch;
    private TextView roll;
    private CustomCanvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_motion);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        azimuth = (TextView) findViewById(R.id.azimuth);
        pitch = (TextView) findViewById(R.id.pitch);
        roll = (TextView) findViewById(R.id.roll);
        canvas = (CustomCanvas) findViewById(R.id.canvas);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.device_motion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onPause() {

        super.onPause();

        sensorManager.unregisterListener(this);

    }

    @Override
    public void onResume() {

        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    /**
     * Called when sensor values have changed.
     * <p>See {@link android.hardware.SensorManager SensorManager}
     * for details on possible sensor types.
     * <p>See also {@link android.hardware.SensorEvent SensorEvent}.
     * <p/>
     * <p><b>NOTE:</b> The application doesn't own the
     * {@link android.hardware.SensorEvent event}
     * object passed as a parameter and therefore cannot hold on to it.
     * The object may be part of an internal pool and may be reused by
     * the framework.
     *
     * @param event the {@link android.hardware.SensorEvent SensorEvent}.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

        float valueAzimuth, valuePitch, valueRoll;

        long currentTime = System.currentTimeMillis();

        valueAzimuth = event.values[0];
        valuePitch = event.values[1];
        valueRoll = event.values[2];

        azimuth.setText(String.valueOf(valueAzimuth));
        pitch.setText(String.valueOf(valuePitch));
        roll.setText(String.valueOf(valueRoll));

        if ((currentTime - lastUpdate) > 2000) {

            float acceleration =
                    ((valueAzimuth * valueAzimuth) + (valuePitch * valuePitch) + (valueRoll * valueRoll)
                    / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH));

            int SHAKE_THRESHOLD = 800;
            if (acceleration > SHAKE_THRESHOLD) {
                toast(R.string.shake);
            }

        }

        canvas.setCircleVelocities((int) -valueAzimuth, (int) valuePitch);

        lastUpdate = System.currentTimeMillis();

    }

    /**
     * Called when the accuracy of the registered sensor has changed.
     * <p/>
     * <p>See the SENSOR_STATUS_* constants in
     * {@link android.hardware.SensorManager SensorManager} for details.
     *
     * @param sensor the sensor
     * @param accuracy The new accuracy of this sensor, one of
     *                 {@code SensorManager.SENSOR_STATUS_*}
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void toast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_LONG).show();
    }


}
