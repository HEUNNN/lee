package ref.ex;

import java.util.Scanner;

public class ProductOrderMain3 {
    public static void main(String[] args) {
        // 입력 받기 - Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("입력할 주문의 개수를 입력하세요. 입력: ");
        int input = scanner.nextInt();
        scanner.nextLine(); // 입력 버퍼를 비우기 위한 코드

        ProductOrder[] productOrders = new ProductOrder[input];

        for (int i = 0; i < input; i++) {
            System.out.println((i+1) + "번째 주문 정보를 입력하세요.");

            System.out.println("상품명: ");
            String productName = scanner.nextLine();

            System.out.println("가격: ");
            int price = scanner.nextInt();
            scanner.nextLine();

            System.out.println("수량: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            productOrders[i] = createOrder(productName, price, quantity);
        }

        printOrders(productOrders);

        System.out.println("총 결제 금액: " + getTotalCount(productOrders));
    }

    static ProductOrder createOrder(String productName, int price, int quantity) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.productName = productName;
        productOrder.price = price;
        productOrder.quantity = quantity;
        return productOrder;
    }

    static void printOrders(ProductOrder[] productOrders) {
        for (ProductOrder productOrder : productOrders) {
            System.out.println("상품명: " + productOrder.productName + ", 가격: " + productOrder.price + ", 수량: " + productOrder.quantity);
        }
    }

    static int getTotalCount(ProductOrder[] productOrders) {
        int totalCount = 0;

        for (ProductOrder productOrder : productOrders) {
            totalCount += productOrder.price * productOrder.quantity;
        }

        return totalCount;
    }
}
