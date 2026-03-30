import java.util.List;

public interface ElevatorStrategy {
    Elevator choose(List<Elevator> elevatorBank, Request pickupRequest);
}
