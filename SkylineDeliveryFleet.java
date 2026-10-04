public class SkylineDeliveryFleet {
    public static String getLocationIfTrackable(Object object) {
        if (object instanceof Trackable) {
            Trackable trackable = (Trackable) object;
            return trackable.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone deliveryDrone = new DeliveryDrone("DR-1");
        ScoutDrone scoutDrone = new ScoutDrone("SC-1");
        GroundRobot groundRobot = new GroundRobot("GR-1");

        System.out.println(getLocationIfTrackable(deliveryDrone));
        System.out.println(getLocationIfTrackable(scoutDrone));
        System.out.println(getLocationIfTrackable(groundRobot));
    }
}

abstract class Drone {
    private final String id;

    protected Drone(String id) {
        this.id = id;
    }

    protected String getId() {
        return id;
    }

    public abstract String fly();
}

interface Trackable {
    String getLocation();
}

class DeliveryDrone extends Drone implements Trackable {
    public DeliveryDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return "Delivery drone " + getId() + " flying";
    }

    @Override
    public String getLocation() {
        return getId() + " at Sector 4";
    }
}

class ScoutDrone extends Drone {
    public ScoutDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return "Scout drone " + getId() + " flying";
    }
}

class GroundRobot implements Trackable {
    private final String id;

    public GroundRobot(String id) {
        this.id = id;
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}
