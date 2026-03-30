public class WeightSensor {
    private final int maxWeight;
    private int currentWeight;

    public WeightSensor(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean isOverWeight() {
        return currentWeight > maxWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }
}
