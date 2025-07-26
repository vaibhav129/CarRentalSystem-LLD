package Customer;

public class Customer {
    private String customerName;
    private String customerEmail;
    private String customerId;

    public Customer(String customerName, String customerEmail, String customerId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        
        System.out.println("Customer created: " + customerName + " (ID: " + customerId + ")");
        System.out.println("   Email: " + customerEmail);
    }

    // Getter methods with meaningful names
    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    // Legacy getter methods for backward compatibility
    public String getId() {
        return getCustomerId();
    }

    public String getLc() {
        return getCustomerEmail();
    }

    public String getName() {
        return getCustomerName();
    }
}
