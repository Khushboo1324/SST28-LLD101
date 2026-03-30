public class InternalButton implements Button {
    private final int targetFloor;
    private final ElevatorPanel panel;

    public InternalButton(int targetFloor, ElevatorPanel panel) {
        this.targetFloor = targetFloor;
        this.panel = panel;
    }

    @Override
    public void press() {
        System.out.println("Internal button pressed for floor " + targetFloor);
        panel.requestFloor(targetFloor);
    }
}
