import java.util.*;

public class BookingService {
    public boolean bookTicket(Show selectedShow, List<String> requestedSeatIds, PaymentMethod chosenPaymentMethod) {
        if (selectedShow.lockSeats(requestedSeatIds)) {
            double payableAmount  = calculateTotal(selectedShow, requestedSeatIds);
            if (chosenPaymentMethod.processPayment(payableAmount)) {
                return true;
            } else {
              selectedShow.unlockSeats(requestedSeatIds);
                return false;
            }
        }
        return false;
    }

    private double calculateTotal(Show selectedShow, List<String> requestedSeatIds) {
        return selectedShow.getSeats().stream()
            .filter(seat -> requestedSeatIds.contains(seat.getSeatId()))
                .mapToDouble(seat -> seat.getPrice())
                .sum();
    }
}