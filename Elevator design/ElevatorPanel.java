import java.util.ArrayList;
import java.util.List;

public class ElevatorPanel {
    private final Elevator elevator;
    private final List<InternalButton> internalButtons;
    private final Button openButton;
    private final Button closeButton;
    private final Button alarmButton;

    public ElevatorPanel(Elevator elevator, int totalFloors) {
        this.elevator = elevator;
        this.internalButtons = new ArrayList<>();

        for (int floor = 1; floor <= totalFloors; floor++) {
            internalButtons.add(new InternalButton(floor, this));
        }

        this.openButton = new Button() {
            @Override
            public void press() {
                System.out.println("Open button pressed in Elevator " + elevator.getId());
                elevator.openDoor();
            }
        };

        this.closeButton = new Button() {
            @Override
            public void press() {
                System.out.println("Close button pressed in Elevator " + elevator.getId());
                elevator.closeDoor();
            }
        };

        this.alarmButton = new Button() {
            @Override
            public void press() {
                System.out.println("Alarm button pressed in Elevator " + elevator.getId());
            }
        };
    }

    public void requestFloor(int floor) {
        elevator.addRequest(new Request(floor, elevator.getDirection(), Request.RequestType.INTERNAL));
    }

    public void pressInternalButton(int floor) {
        if (floor < 1 || floor > internalButtons.size()) {
            System.out.println("Invalid floor selected: " + floor);
            return;
        }
        internalButtons.get(floor - 1).press();
    }

    public void pressOpenButton() {
        openButton.press();
    }

    public void pressCloseButton() {
        closeButton.press();
    }

    public void pressAlarmButton() {
        alarmButton.press();
    }
}
