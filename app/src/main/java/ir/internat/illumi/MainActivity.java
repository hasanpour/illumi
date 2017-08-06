package ir.internat.illumi;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewLux;
    private ColorArcProgressBar progressBarLux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarLux = (ColorArcProgressBar) findViewById(R.id.progressBarLux);

        // Load control
        textViewLux = (TextView) findViewById(R.id.textViewLux);

        // implement sensor manager
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        // check sensor available in devise. if available then get reading
        if (lightSensor == null) {
            Toast.makeText(getApplicationContext(), "No Sensor",
                    Toast.LENGTH_SHORT).show();
            // Toast.makeText(AndroidLightSensorActivity.this,
            // "No Light Sensor! quit-", Toast.LENGTH_LONG).show();
        } else {
            float max = lightSensor.getMaximumRange();
            progressBarLux.setMaxValues(max);
            textViewLux.setText("Max Reading: " + String.valueOf(max));

            sensorManager.registerListener(lightSensorEventListener,
                    lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    // implement sensor event listener
    SensorEventListener lightSensorEventListener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }

        // get sensor update and reading
        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                float currentReading = event.values[0];
                progressBarLux.setCurrentValues(currentReading);
                //textViewLux.setText("Current Reading: "
                 //       + String.valueOf(currentReading) + " Lux");
            }
        }
    };
}
