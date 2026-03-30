import java.util.*;
import java.util.stream.Collectors;

public class CinemaManagementService {
    private List<Show> shows = new ArrayList<>();

    public void addShow(Show show) {
        shows.add(show);
    }

    public List<String> listMoviesInCity(String city) {
        return shows.stream().map(Show::getMovieName).distinct().collect(Collectors.toList());
    }

    public List<Show> getShowsForMovie(String movieName) {
        return shows.stream()
                .filter(s -> s.getMovieName().equalsIgnoreCase(movieName))
                .collect(Collectors.toList());
    }
}