package class1.ex;

public class ProductOrder {
    String productName;
    int price;
    int quantity;

    public ProductOrder() {
    }

    public ProductOrder(String productName, int price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
}
