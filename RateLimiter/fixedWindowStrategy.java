package RateLimiter;

import java.util.LinkedList;
import java.util.Queue;

public class fixedWindowStrategy implements ILimitStrategy {
    // timeframe: 4 seconds; max requests allowed per window: 3
    private int windowStartTimeSeconds = 0;
    private int windowEndTimeSeconds = 3;
    private final int maxRequestsPerWindow = 3;

    private final Queue<Client> requestsInCurrentWindow = new LinkedList<>();

    @Override
    public boolean allowRequest(Client client) {
        int requestTimestampSeconds = client.getRequestTimestampSeconds();

        boolean isWithinWindow = requestTimestampSeconds >= windowStartTimeSeconds
                && requestTimestampSeconds <= windowEndTimeSeconds;

        if (isWithinWindow && requestsInCurrentWindow.size() < maxRequestsPerWindow) {
            requestsInCurrentWindow.add(client);
            System.out.println("API request accepted at t=" + requestTimestampSeconds);
            return true;
        }

        if (isWithinWindow) {
            System.out.println("Rate limit exceeded at t=" + requestTimestampSeconds);
            return false;
        }

        if (requestTimestampSeconds > windowEndTimeSeconds) {
            // move to the next window
            windowStartTimeSeconds = requestTimestampSeconds;
            windowEndTimeSeconds = windowStartTimeSeconds + 3;
            requestsInCurrentWindow.clear();

            requestsInCurrentWindow.add(client);
            System.out.println("API request accepted in new window at t=" + requestTimestampSeconds);
            return true;
        }

        return false;
    }
}
