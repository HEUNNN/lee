package access.ex;

public class ShoppingCartMain {
    public static void main(String[] args) {
        Item item1 = new Item("마늘", 2000, 2);
        Item item2 = new Item("상추", 3000, 4);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(item1);
        shoppingCart.addItem(item2);

        shoppingCart.displayItem();
    }
}
