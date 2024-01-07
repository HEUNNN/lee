package class1.ex;

public class ProductOrderMain {
    public static void main(String[] args) {
        ProductOrder tofu = new ProductOrder("두부", 2000, 2);
        ProductOrder kimchi = new ProductOrder("김치", 5000, 1);
        ProductOrder coke = new ProductOrder("콜라", 1500, 2);

        int totalPrice = 0;

        ProductOrder[] productOrders = {tofu, kimchi, coke};

        for (ProductOrder productOrder : productOrders) {
            System.out.println("상품명: " + productOrder.productName + ", 가격: " + productOrder.price + ", 수량: " + productOrder.quantity);
            totalPrice += productOrder.price * productOrder.quantity;
        }
        System.out.println("총 결제 금액: " +  totalPrice);
    }
}
