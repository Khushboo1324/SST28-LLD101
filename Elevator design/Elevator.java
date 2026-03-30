import java.util.LinkedList;
import java.util.Queue;

public class Elevator {
    private final int id;
    private int currentFloor;
    private Direction direction;
    private final Queue<Request> requestQueue;
    private final Door door;
    private final WeightSensor weightSensor;
    private final ElevatorPanel panel;

    public Elevator(int id, int startFloor, int maxWeight, int totalFloors) {
        this.id = id;
        this.currentFloor = startFloor;
        this.direction = Direction.IDLE;
        this.requestQueue = new LinkedList<>();
        this.door = new Door();
        this.weightSensor = new WeightSensor(maxWeight);
        this.panel = new ElevatorPanel(this, totalFloors);
    }

    public void addRequest(Request pickupRequest) {
        requestQueue.offer(pickupRequest);
        System.out.println("Elevator " + id + " queued pickupRequest for floor " + pickupRequest.getFloor());
    }

    public void step() {
        if (door.isOpen()) {
            closeDoor();
            if (door.isOpen()) {
                return;
            }
        }

        if (requestQueue.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        Request nextRequest = requestQueue.peek();
        if (nextRequest == null) {
            direction = Direction.IDLE;
            return;
        }

        if (currentFloor == nextRequest.getFloor()) {
            System.out.println("Elevator " + id + " reached floor " + currentFloor);
            openDoor();
            requestQueue.poll();
            return;
        }

        move(nextRequest.getFloor());
    }

    public void move(int targetFloor) {
        if (currentFloor < targetFloor) {
            direction = Direction.UP;
            currentFloor++;
            System.out.println("Elevator " + id + " moving up to floor " + currentFloor);
        } else if (currentFloor > targetFloor) {
            direction = Direction.DOWN;
            currentFloor--;
            System.out.println("Elevator " + id + " moving down to floor " + currentFloor);
        }
    }

    public void openDoor() {
        door.open();
    }

    public void closeDoor() {
        if (weightSensor.isOverWeight()) {
            System.out.println("Elevator " + id + " is overweight. Door remains open.");
            door.open();
            return;
        }
        door.close();
    }

    public void setCurrentWeight(int currentWeight) {
        weightSensor.setCurrentWeight(currentWeight);
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorPanel getPanel() {
        return panel;
    }
}
