public class UPIPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI payment of " + amount);
        return true;
    }
}