import java.util.*;

public class BookingService {
    public boolean bookTicket(Show show, List<String> seatIds, PaymentMethod paymentMethod) {
        if (show.lockSeats(seatIds)) {
            double totalAmount = calculateTotal(show, seatIds);
            if (paymentMethod.processPayment(totalAmount)) {
                return true;
            } else {
                show.unlockSeats(seatIds);
                return false;
            }
        }
        return false;
    }

    private double calculateTotal(Show show, List<String> seatIds) {
        return show.getSeats().stream()
                .filter(s -> seatIds.contains(s.getSeatId()))
                .mapToDouble(s -> s.getPrice())
                .sum();
    }
}