package com.example.a1658824587qqcom.collectfrequencytest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Button bt1,bt2;
    private Sensor msensor;
    private SensorManager sm;
    private String TAG="main";
    private float[] mag_value=new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=(Button) findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        msensor=sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //sm.registerListener(this,msensor,CollectTime.COLLECT_NORMAL);
        //按钮注册监听事件
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"10HZ is running......");
                run_collect_10();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop_run();
                Log.d(TAG,"10HZ has stopped...");
                run_collect_5();
                Log.d(TAG,"5HZ is running...");
            }
        });
    }
    public void run_collect_10(){
        sm.registerListener(this,msensor,CollectTime.COLLECT_NORMAL);
    }
    public void run_collect_5(){
        sm.registerListener(this,msensor,CollectTime.COLLECT_SLOW);
    }
    public void stop_run(){
        sm.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        mag_value=sensorEvent.values.clone();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss.SSS");
        String str=sdf.format(new Date());
        String msg=str.concat(" "+mag_value[0]);
        Log.d(TAG,"x component is "+msg);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
