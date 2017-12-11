package com.example.anik.database;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Speed extends Fragment implements SensorEventListener, StepListener , NavigationView.OnNavigationItemSelectedListener{


    private TextView textView;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private Button logout,BtnStart,BtnStop;
    private Session session;
    private TextView TvSteps;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_speed, container, false);


        // Get an instance of the SensorManager
        sensorManager = (SensorManager)getActivity(). getSystemService(Context.SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        TvSteps = (TextView)rootView.findViewById(R.id.tv_steps) ;
        BtnStart = (Button)rootView. findViewById(R.id.btn_start);
        BtnStop = (Button)rootView. findViewById(R.id.btn_stop);





        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                numSteps = 0;
                sensorManager.registerListener(Speed.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });



        BtnStop.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                sensorManager.unregisterListener(Speed.this);

            }
        });



        return rootView;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void step(long timeNs) {

        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }
}
