package RateLimiter;

public class Client {
    private final int requestTimestampSeconds;
    private final String clientId;

    public Client(int requestTimestampSeconds, String clientId){
        this.requestTimestampSeconds = requestTimestampSeconds;
        this.clientId = clientId;
    }

    public int getRequestTimestampSeconds() {
        return requestTimestampSeconds;
    }

    public String getClientId() {
        return clientId;
    }
}
