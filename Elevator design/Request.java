public class Request {
    private final int floor;
    private final Direction direction;
    private final RequestType type;

    public enum RequestType {
        INTERNAL,
        EXTERNAL
    }

    public Request(int floor, Direction direction, RequestType type) {
        this.floor = floor;
        this.direction = direction;
        this.type = type;
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestType getType() {
        return type;
    }
}
