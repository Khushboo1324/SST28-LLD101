import java.util.List;

public class ElevatorSystem {
    private final List<Elevator> elevatorBank;
    private final ElevatorStrategy strategy;

    public ElevatorSystem(List<Elevator> elevatorBank, ElevatorStrategy strategy) {
        this.elevatorBank = elevatorBank;
        this.strategy = strategy;
    }

    public void handleRequest(Request pickupRequest) {
        Elevator elevator = strategy.choose(elevatorBank, pickupRequest);
        if (elevator == null) {
            System.out.println("No elevator available for floor " + pickupRequest.getFloor());
            return;
        }
        System.out.println("Assigned Elevator " + elevator.getId() + " to floor " + pickupRequest.getFloor());
        elevator.addRequest(pickupRequest);
    }

    public void step() {
        for (Elevator elevator : elevatorBank) {
            elevator.step();
        }
    }
}
