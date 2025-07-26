package Payment;

public class CreditPayment implements Payment {

    @Override
    public boolean processPayment() {
        System.out.println("💳 Processing payment with Credit Card...");
        System.out.println("   🔐 Validating credit card details...");
        System.out.println("   💰 Processing transaction amount...");
        System.out.println("   ✅ Credit card payment processed successfully");
        return true;
    }
}
