package RateLimiter;
public interface ILimitStrategy {
    public boolean allowRequest(Client client);
}
