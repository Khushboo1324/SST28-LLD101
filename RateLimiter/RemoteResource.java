package RateLimiter;
public class RemoteResource {
    public void generate(String clientId){
        System.out.println("API Calling is Expensive... for client "+ clientId);
    }    
}
