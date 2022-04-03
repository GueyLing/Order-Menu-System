
import java.util.ArrayList;

public class Order {

    private String orderID;
    private String orderDate;
    private ArrayList<Item> items;
    private double amount;

    // Constructor
    public Order() {
        orderID = "";
        orderDate = "";
        items = new ArrayList<Item>();
        amount = 0.0;
    }

    // Overloaded Constructor
    public Order(String id, String date, ArrayList<Item> items, double amount) {
        orderID = id;
        orderDate = date;
        this.items = items;
        this.amount = amount;
    }

    /**
     * The setOrderID method stores a value in the orderID field.
     *
     * @param id Order ID.
     */
    public void setOrderID(String id) {
        orderID = id;
    }

    /**
     * The setOrderDate method stores a value in the orderDate field.
     *
     * @param date Order Date.
     */
    public void setOrderDate(String date) {
        orderDate = date;
    }

    /**
     * The setOrderItems method stores the array list of item in the items field.
     *
     * @param items Items.
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * The setAmount method stores a value in the amount field.
     *
     * @param amount Amount.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * The getOrderID method returns the order ID.
     *
     * @return The value in the orderID field.
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * The getOrderDate method returns the order Date.
     *
     * @return The value in the orderDate field.
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * The getItems method returns the items.
     *
     * @return The value in the items field.
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * The getAmount method returns the amount.
     *
     * @return The value in the amount field.
     */
    public double getAmount() {
        return amount;
    }
}
