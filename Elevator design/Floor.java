import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<ExternalButton> externalButtons;

    public Floor(int floorNumber, ElevatorSystem system) {
        this.floorNumber = floorNumber;
        this.externalButtons = new ArrayList<>();
        externalButtons.add(new ExternalButton(floorNumber, Direction.UP, system));
        externalButtons.add(new ExternalButton(floorNumber, Direction.DOWN, system));
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ExternalButton> getExternalButtons() {
        return externalButtons;
    }
}
