package abhatem.com.rosimu;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

import geometry_msgs.*;

import static android.R.attr.gravity;

/**
 * Created by abo0ody on 18/08/17.
 */

class SensorValues implements SensorValuesInterface{

    private static SensorValues m_instance = new SensorValues();

    private Quaternion quaternion ;



    private float R[] = new float[9];
    private float I[] = new float[9];
    private SensorValues() {
        this.quaternion = new Quaternion();
        quaternion.setX(0);
        quaternion.setY(0);
        quaternion.setZ(0);
        quaternion.setW(1);
    }




    public static SensorValues instance() {
        return m_instance;
    }

    @Override
    public void handleSensorEvent(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            this.quaternion.setX(event.values[0]);
            this.quaternion.setY(event.values[1]);
            this.quaternion.setZ(event.values[2]);
            this.quaternion.setW(event.values[3]);
        }
    }

    public Quaternion getQuaternion() {
        return this.quaternion;
    }




}
