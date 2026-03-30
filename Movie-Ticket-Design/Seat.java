public class Seat {
    private String seatId;
    private SeatType seatType;
    private double price;

    public Seat(String seatId, SeatType seatType, double price) {
        this.seatId = seatId;
        this.seatType = seatType;
        this.price = price;
    }

    public String getSeatId() { return seatId; }
    public double getPrice() { return price; }
}