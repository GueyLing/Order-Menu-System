
public class Item {

    private int itemNo;
    private String itemDesc;
    private double price;

    // Constructor
    public Item() {
        itemNo = 0;
        itemDesc = "";
        price = 0.0;
    }

    // Overloaded Constructor
    public Item(int no, String desc, double price) {
        itemNo = no;
        itemDesc = desc;
        this.price = price;
    }

    /**
     * @return item number, item description and price.
     */
    @Override
    public String toString() {
        String record = itemNo + ")";
        return String.format("%-4s%-26sRM%.2f", record, itemDesc, price);
    }

    /**
     * The setItemNo method stores a value in the itemNo field.
     *
     * @param no Item number.
     */
    public void setItemNo(int no) {
        itemNo = no;
    }

    /**
     * The setItemDesc method stores a value in the itemDesc field.
     *
     * @param desc Item description.
     */
    public void setItemDesc(String desc) {
        itemDesc = desc;
    }

    /**
     * The setPrice method stores a value in the price field.
     *
     * @param price Price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * The getItemNo method returns the item number.
     *
     * @return The value in the itemNo field.
     */
    public int getItemNo() {
        return itemNo;
    }

    /**
     * The getItemDesc method returns the item description.
     *
     * @return The value in the itemDesc field.
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * The getPrice method returns the price.
     *
     * @return The value in the price field.
     */
    public double getPrice() {
        return price;
    }

    /**
     * The getDesc_Price method returns item description and price.
     *
     * @return The item description and price in a string format.
     */
    public String getDesc_Price() {
        return String.format("%-26sRM%.2f", itemDesc, price);
    }
}// end class
