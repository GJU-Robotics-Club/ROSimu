package abhatem.com.rosimu;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.ros.android.RosActivity;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;
import org.ros.node.topic.Publisher;
import org.ros.node.ConnectedNode;

public class MainActivity extends RosActivity implements SensorEventListener{

    private SensorManager m_sensorManager;
    private Sensor m_rotationSensor;
    public MainActivity() {
        super("imupub", "imupub");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        m_rotationSensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    }

    protected void onResume() {
        super.onResume();
        m_sensorManager.registerListener(this, m_rotationSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void init(NodeMainExecutor nme) {
        RotationPublisher rp = new RotationPublisher();
        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname());
        nodeConfiguration.setMasterUri(getMasterUri());
        nme.execute(rp, nodeConfiguration);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        SensorValues.instance().handleSensorEvent(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
