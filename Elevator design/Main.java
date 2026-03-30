import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int totalFloors = 10;

        Elevator e1 = new Elevator(1, 1, 800, totalFloors);
        Elevator e2 = new Elevator(2, 6, 800, totalFloors);

        List<Elevator> elevators = new ArrayList<>();
        elevators.add(e1);
        elevators.add(e2);

        ElevatorSystem system = new ElevatorSystem(elevators, new NearestStrategy());

        List<Floor> floors = new ArrayList<>();
        for (int i = 1; i <= totalFloors; i++) {
            floors.add(new Floor(i, system));
        }

        floors.get(1).getExternalButtons().get(0).press();
        floors.get(7).getExternalButtons().get(1).press();

        e1.getPanel().pressInternalButton(5);

        e1.setCurrentWeight(900);
        e1.getPanel().pressOpenButton();
        e1.getPanel().pressCloseButton();
        e1.setCurrentWeight(700);

        int tick = 1;
        while (true) {
            System.out.println("------ STEP " + tick + " ------");
            system.step();
            tick++;
        }
    }
}
