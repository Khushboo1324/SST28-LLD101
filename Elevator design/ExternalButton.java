public class ExternalButton implements Button {
    private final int floor;
    private final Direction direction;
    private final ElevatorSystem system;

    public ExternalButton(int floor, Direction direction, ElevatorSystem system) {
        this.floor = floor;
        this.direction = direction;
        this.system = system;
    }

    @Override
    public void press() {
        System.out.println("External button pressed at floor " + floor + " for " + direction);
        system.handleRequest(new Request(floor, direction, Request.RequestType.EXTERNAL));
    }
}
