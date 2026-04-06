package RateLimiter;
public class RateLimiterProxy {
    private final RemoteResource remoteResource;
    private final ILimitStrategy rateLimitStrategy;

    public RateLimiterProxy(ILimitStrategy rateLimitStrategy){
        this.rateLimitStrategy = rateLimitStrategy;
        this.remoteResource = new RemoteResource();
    }
    public void generate(Client client){
        if(rateLimitStrategy.allowRequest(client)){
            remoteResource.generate(client.getClientId());
        }
        else{
            System.out.println("Sorry API Rate Limit is exceeded!");
        }
    }
}
