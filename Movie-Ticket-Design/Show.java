import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Show {
    private String showId;
    private String movieName;
    private String theaterId;
    private Map<String, Boolean> seatAvailability; 
    private List<Seat> seats;

    public Show(String showId, String movieName, String theaterId, List<Seat> seats) {
        this.showId = showId;
        this.movieName = movieName;
        this.theaterId = theaterId;
        this.seats = seats;
        this.seatAvailability = new ConcurrentHashMap<>();
        for (Seat seat : seats) {
            seatAvailability.put(seat.getSeatId(), true);
        }
    }

    public synchronized boolean lockSeats(List<String> seatIds) {
        for (String id : seatIds) {
            if (!seatAvailability.getOrDefault(id, false)) return false;
        }
        for (String id : seatIds) {
            seatAvailability.put(id, false);
        }
        return true;
    }

    public synchronized void unlockSeats(List<String> seatIds) {
        for (String id : seatIds) {
            seatAvailability.put(id, true);
        }
    }

    public List<Seat> getSeats() { return seats; }
    public String getMovieName() { return movieName; }
    public Map<String, Boolean> getSeatAvailability() { return seatAvailability; }
}