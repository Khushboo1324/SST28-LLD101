package RateLimiter;

public class Main {
    
    public static void main(String[] args){
        Client clientAtT0 =new Client(0,"123");
        Client clientAtT2 =new Client(2,"123");
        Client clientAtT3A =new Client(3,"123");
        Client clientAtT3B =new Client(3,"123");
        Client clientAtT6 =new Client(6,"123");
        ILimitStrategy limitStrategy =new movingWindowStrategy();
        RateLimiterProxy rateLimiterProxy =new RateLimiterProxy(limitStrategy);
        rateLimiterProxy.generate(clientAtT0);
        rateLimiterProxy.generate(clientAtT2);
        rateLimiterProxy.generate(clientAtT3A);
        rateLimiterProxy.generate(clientAtT3B);
        rateLimiterProxy.generate(clientAtT6);
    }
}
