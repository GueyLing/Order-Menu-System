
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;

class DataController {

    static ArrayList<Order> orderList = new ArrayList<Order>();

    public void addOrder(Order o) {

        // add the order to the arrayList
        orderList.add(o);
    }

    //save the order data to the file
    public void saveOrder() throws Exception {
        // creating the writers to save the order records
        try {
            File itemList = new File("orders.txt");
            if (itemList.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("Error.");
        }
        FileWriter fw = new FileWriter("orders.txt");   
        PrintWriter pw = new PrintWriter(fw);
        pw.printf("%-15s%-35s%-85s%-35s\n", "Order ID", "Order Date", "Items", "Amount");

        // iterate the order list
        for (Order o : orderList) {
            if (o != null) {
                //write to file
                pw.printf("%-15s%-35s%-85s%-35.2f\n", o.getOrderID(), o.getOrderDate(), o.getItems(), o.getAmount());
            }
        }
        pw.close();
        System.out.println("Log: " + "Data saved to the orders.txt ");
    }

}
