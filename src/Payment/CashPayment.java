package Payment;

public class CashPayment implements Payment {
    @Override
    public boolean processPayment() {
        System.out.println("💵 Processing payment with Cash...");
        System.out.println("   💰 Counting cash amount...");
        System.out.println("   📝 Generating cash receipt...");
        System.out.println("   ✅ Cash payment processed successfully");
        return true;
    }
}
