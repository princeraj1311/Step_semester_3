import java.util.ArrayList;
import java.util.List;

public class Question5PaymentProcessingForShoppingSystem {
    public static void main(String[] args) {
        Customer customerX = new Customer("Customer X");
        Customer customerY = new Customer("Customer Y");
        Customer customerZ = new Customer("Customer Z");

        Product productA = new Product("Product A", 2, 50.0);
        Product productB = new Product("Product B", 1, 30.0);
        Product productC = new Product("Product C", 1, 40.0);

        Order orderX = new Order(customerX);
        orderX.addProduct(productA);
        orderX.addProduct(productB);

        PaymentMethod creditCard = new CreditCardPayment();
        orderX.pay(creditCard);
        System.out.println("Order status: " + orderX.getStatus());

        Order emptyOrder = new Order(customerY);
        emptyOrder.pay(new PayPalPayment());

        Order orderZ = new Order(customerZ);
        orderZ.addProduct(productC);
        orderZ.pay(new PayPalPayment());
        System.out.println("Order status: " + orderZ.getStatus());
    }
}

enum OrderStatus {
    PENDING,
    PAID,
    FAILED
}

class Customer {
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Product {
    private final String name;
    private final int quantity;
    private final double unitPrice;

    public Product(String name, int quantity, double unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return quantity * unitPrice;
    }
}

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card payment for $" + amount);
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment for $" + amount);
        return false;
    }
}

class BankTransferPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Bank Transfer payment for $" + amount);
        return true;
    }
}

class Order {
    private final Customer customer;
    private final List<Product> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.PENDING;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addProduct(Product product) {
        items.add(product);
    }

    public double getTotalAmount() {
        double total = 0;
        for (Product item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void pay(PaymentMethod paymentMethod) {
        if (isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            status = OrderStatus.FAILED;
            return;
        }

        System.out.println("Payment initiated via " + paymentMethod.getClass().getSimpleName() + " for Order of " + customer.getName() + ".");
        boolean success = paymentMethod.processPayment(getTotalAmount());

        if (success) {
            status = OrderStatus.PAID;
            System.out.println("Payment for order successful. Order status: Paid.");
        } else {
            status = OrderStatus.FAILED;
            System.out.println("Payment for order failed. Order status: Pending.");
        }
    }
}
