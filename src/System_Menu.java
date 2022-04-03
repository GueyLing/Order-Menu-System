
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.InputMismatchException;

// custom exception
class InvalidInputException extends Exception {

    InvalidInputException(String s) {
        super(s);
    }
}

public class System_Menu {
    
    static ArrayList<Item> itemList = new ArrayList<Item>();
    static int id = 1;

    // to validate the user input for System Main Menu 
    static void validate(int input) throws InvalidInputException {
        if (input <= 0 || input >= 4) {
            throw new InvalidInputException("Invalid input. The input should be from 1 to 3.");
        }
    }

    // to validate whether the user entered the correct input 'Y','y','N','n'.
    static void validateOption(String choice) throws InvalidInputException {
        if (!"y".equalsIgnoreCase(choice) && !"n".equalsIgnoreCase(choice)) {
            throw new InvalidInputException("Invalid input. Please enter 'Y' or 'y' for yes OR 'N' or 'n' for no. \n");
        }
    }
    
    // to validate item price
    static void validatePrice(double price) throws InvalidInputException {
        if (price<0) {
            throw new InvalidInputException("Invalid input. The price should not be a negative number.");
        }
    }

    public static void main(String args[]) throws Exception {
        reloadItems();
        mainMenu();
    }//end main

    private static void reloadItems() throws Exception {
         
        FileReader fr= new FileReader("item.txt");
        
        //read the data from the file
        Scanner sc = new Scanner(fr);

        while (sc.hasNext()) {
            String line = sc.nextLine();

            String[] words = line.split(", ");

            int itemNo = Integer.parseInt((words[0]));
            String itemDesc = words[1];
            double price = Double.parseDouble(words[2]);

            Item items = new Item(itemNo, itemDesc, price);
            //add the items in the file into the arrayList
            itemList.add(items);
        }
        fr.close();
        sc.close();
    }

    /**
     * 
     * a method to print System Main Menu and prompt user to enter the input
     */
    private static void mainMenu() throws Exception {
        int option = 0;
        boolean ok = false;

        //print the main menu
        System.out.println("Lite Lunch Main Menu");
        System.out.println("1. Order Menu");
        System.out.println("2. Add New Item");
        System.out.println("3. Exit System");

        do {
            try {
                // prompt user to enter option
                option = Utility.getIntegerInput("Please enter your option: ");
                processOption(option);
                validate(option);
                ok = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. The input should be an integer.");
            } catch (Exception m) {
                System.out.println(m);
            } 
        } while (!ok);
    }

    private static void processOption(int option) throws Exception {
        DataController database = new DataController();
        int itemNo = itemList.size() + 1;
        String itemDesc="";
        double price=0.0;
        boolean ok = false;
        String choice = "";
        switch (option) {
            case 1: {
                Order myOrder = new Order();
                orderMenu(database, myOrder);
                break;
            }
            case 2: {
                // user add item
                
                do {
                    itemDesc = Utility.getStringInput("Please enter the item description: ");
                    do{                    
                    try{
                    price = Utility.getDoubleInput("Please enter the item price: ");
                    validatePrice(price);
                    ok=true;}
                    catch(InputMismatchException e){
                            System.out.println("\nInvalid input. The input should be numbers only.");
                            
                            ok=false;
                         } catch (Exception m) {
                        
                        System.out.println(m);
                        ok = false;
                            }
                    }while(!ok);
                    FileWriter fw = new FileWriter("item.txt", true);
                    PrintWriter pw = new PrintWriter(fw);

                    Item items = new Item(itemNo, itemDesc, price);
                    //add the items in the file into the arrayList
                    itemList.add(items);
                    pw.println(itemNo + ", " + itemDesc + ", " + price);
                    itemNo++;
                    pw.close();
                    do {
                        try {
                            choice = Utility.getStringInput("Do you want to add next item [Y/N]?");
                            validateOption(choice);
                            ok = true;
                        } catch (Exception m) {
                            ok = false;
                            System.out.println(m);
                        }

                    } while (!ok);
                } while (choice.equalsIgnoreCase("Y"));

                if (choice.equalsIgnoreCase("N")) {
                    mainMenu();
                }
                break;
            }
            case 3: {
                //end the system
                System.out.println("Thank you for using the order management system.");

            }
        }//end switch

    }//end processOption

    private static void orderMenu(DataController database, Order myOrder) throws Exception {
        ArrayList<Item> itemsPerOrder = new ArrayList<Item>();
        int option = 0;
        boolean ok = false;
        String choice = "",confirmation="";
        double total = 0.0;

        do {

            System.out.println("Lite Lunch Lounge Order Menu");
            //print the order menu
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println(itemList.get(i));
            }

            do {
                try {
                    //prompt user to enter the order
                    option = Utility.getIntegerInput("Please enter your option: ");
                    Item orderedItem = new Item();

                    total += itemList.get(option - 1).getPrice();

                    orderedItem.setItemNo(itemList.get(option - 1).getItemNo());
                    orderedItem.setItemDesc(itemList.get(option - 1).getItemDesc());
                    orderedItem.setPrice(itemList.get(option - 1).getPrice());

                    itemsPerOrder.add(orderedItem);
                    ok = true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid input. The input should be from 1 to " + itemList.size() + ".");
                    ok = false;
                } catch (InputMismatchException e) {
                    ok = false;
                    System.out.println("Invalid input. The input should be an integer.");
                } catch (Exception e) {
                    ok = false;
                    System.out.println("Invalid input. Please enter again.");
                }
            } while (!ok);

            do {
                try {

                    choice = Utility.getStringInput("Do you want to order next item [Y/N]?");
                    validateOption(choice);
                    ok = true;
                } catch (Exception m) {
                    ok = false;
                    System.out.println(m);
                }

            } while (!ok);
        } while (choice.equalsIgnoreCase("Y"));

        if (choice.equalsIgnoreCase("N")) {
            do {
            try {
             confirmation = Utility.getStringInput("Type 'Y' to confirm order or 'N' to cancel order.\n");
                    validateOption(confirmation);
                     ok = true;
                } catch (Exception m) {
                    ok = false;
                    System.out.println("Invalid input. \n");
                }
            } while (!ok);
            
            if (confirmation.equalsIgnoreCase("Y")){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            myOrder.setOrderID("AX" + id++);
            myOrder.setOrderDate(dtf.format(now) + "");
            myOrder.setItems(itemsPerOrder);
            myOrder.setAmount(total);
            database.addOrder(myOrder);
            database.saveOrder();
            printReceipt(database, myOrder, itemsPerOrder);
            mainMenu();}
            
            else
            mainMenu();    
        }//end if (choice.equalsIgnoreCase("N"))
    }//end orderMenu

    private static void printReceipt(DataController database, Order myOrder, ArrayList<Item> itemsPerOrder) {

        System.out.println();
        System.out.println();
        System.out.println("Order");
        printLine(32, '-');

        //print receipt for user
        for (int i = 0; i < itemsPerOrder.size(); i++) {
            System.out.println(itemsPerOrder.get(i).getDesc_Price());
        }

        //free drinks provided if the user ordered main course with starter, dessert or combo
        for (int i = 0; i < itemsPerOrder.size(); i++) {
            if (itemsPerOrder.get(i).getItemNo() == 6) {
                System.out.println("*free 1 coffee");
            }
            if (itemsPerOrder.get(i).getItemNo() == 7) {
                System.out.println("*free 1 soft drink");
            }
            if (itemsPerOrder.get(i).getItemNo() == 8) {
                System.out.println("*free 1 coffee and 1 soft drink");
            }
        }
        printLine(32, '-');
        System.out.printf("%28s%.2f\n", "RM", myOrder.getAmount());
        printLine(32, '-');
        System.out.println();
    }

    /**
     * A method used to draw a line
     *
     * @param size total dashes in a line
     * @param type line pattern
     */
    public static void printLine(int size, char type) {
        for (int i = 0; i < size; i++) {
            System.out.print(type);
        }
        System.out.println();
    }

}//end class
