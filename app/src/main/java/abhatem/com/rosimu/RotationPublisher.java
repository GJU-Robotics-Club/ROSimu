package abhatem.com.rosimu;

import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;


/**
 * Created by abo0ody on 18/08/17.
 */

public class RotationPublisher extends AbstractNodeMain {

    private Publisher<geometry_msgs.Quaternion> publisher;

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of("rosimu/rotation_publisher");
    }



    @Override
    public void onStart(final ConnectedNode cn) {
        publisher = cn.newPublisher("rotation_publisher", geometry_msgs.Quaternion._TYPE);

        cn.executeCancellableLoop(new CancellableLoop() {
            @Override
            protected void loop() throws InterruptedException {
                geometry_msgs.Quaternion rot = publisher.newMessage();
                rot.setX(SensorValues.instance().getQuaternion().getX());
                rot.setY(SensorValues.instance().getQuaternion().getY());
                rot.setZ(SensorValues.instance().getQuaternion().getZ());
                rot.setW(SensorValues.instance().getQuaternion().getW());
                publisher.publish(rot);
                Thread.sleep(100);
            }
        });
    }


}
