import java.util.List;

public class NearestStrategy implements ElevatorStrategy {
    @Override
    public Elevator choose(List<Elevator> elevatorBank, Request pickupRequest) {
        if (elevatorBank == null || elevatorBank.isEmpty()) {
            return null;
        }

        Elevator bestElevator = elevatorBank.get(0);
        int bestAbsDistance = Math.abs(bestElevator.getCurrentFloor() - pickupRequest.getFloor());

        for (int i = 1; i < elevatorBank.size(); i++) {
            Elevator candidateElevator = elevatorBank.get(i);
            int absDistance = Math.abs(candidateElevator.getCurrentFloor() - pickupRequest.getFloor());
            if (absDistance < bestAbsDistance) {
                bestElevator = candidateElevator;
                bestAbsDistance = absDistance;
            }
        }

        return bestElevator;
    }
}
