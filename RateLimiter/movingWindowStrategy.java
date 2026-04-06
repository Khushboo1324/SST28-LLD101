package RateLimiter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class movingWindowStrategy implements ILimitStrategy {
    private final int windowSizeSeconds = 4;
    private final int maxRequestsPerWindow = 3;

    private final Map<String, Queue<Integer>> requestTimestampsByClientId = new HashMap<>();

    @Override
    public boolean allowRequest(Client client) {
        String clientId = client.getClientId();
        int requestTimestampSeconds = client.getRequestTimestampSeconds();

        requestTimestampsByClientId.putIfAbsent(clientId, new LinkedList<>());
        Queue<Integer> recentRequestTimestamps = requestTimestampsByClientId.get(clientId);

        // Remove timestamps that are outside the moving window.
        while (!recentRequestTimestamps.isEmpty()
                && recentRequestTimestamps.peek() <= requestTimestampSeconds - windowSizeSeconds) {
            recentRequestTimestamps.poll();
        }

        if (recentRequestTimestamps.size() < maxRequestsPerWindow) {
            recentRequestTimestamps.add(requestTimestampSeconds);
            System.out.println("Request accepted at t=" + requestTimestampSeconds);
            return true;
        }

        System.out.println("Rate limit exceeded at t=" + requestTimestampSeconds);
        return false;
    }
}
