import java.util.*;

public class Main {
    public static void main(String[] args) {
        CinemaManagementService cms = new CinemaManagementService();
        BookingService bookingService = new BookingService();

        List<Seat> seats = Arrays.asList(
            new Seat("A1", SeatType.GOLD, 200.0),
            new Seat("A2", SeatType.GOLD, 200.0)
        );

        Show show = new Show("S1", "Inception", "PVR_01", seats);
        cms.addShow(show);

        List<String> selectedSeats = Arrays.asList("A1", "A2");
        PaymentMethod upi = new UPIPayment();

        boolean success = bookingService.bookTicket(show, selectedSeats, upi);
        System.out.println("Transaction status: " + success);
    }
}