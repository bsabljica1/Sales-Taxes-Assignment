package com.sales_taxes;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.sales_taxes.model.Item;

public final class App {

    public static void main(String[] args) {

        List<Item> items = new ArrayList<Item>();
        int itemCount = 1;

        //INFINITE LOOP FOR USER INPUT

        while (true) {

        String name;
        Double price;
        int quantity;
        Boolean imported;
        int type;

        Scanner sc= new Scanner(System.in);

        System.out.println("\nAdd items to your shopping cart!\n");
        System.out.println("Add item number " + itemCount + "\n");

        //INSERTING ITEM NAME
        
        System.out.println("Insert item name: \n");
        name = sc.nextLine();
        
        //INSERTING ITEM PRICE

        System.out.println("\nInsert item price: \n");

        //INFINITE LOOP FOR REENTERING IF PRICE INPUT IS INCORECT (IF IT IS NOT A INTEGER OR DECIMAL NUMBER)

        while (true) {

        String str = sc.nextLine();

        if( str.matches("^\\d+\\.\\d+") || str.matches("^\\d+")) {
            price = Double.parseDouble(str);
            //price = Math.round(price*100.0)/100.0;
            break;
        }
		else
			System.out.println("\nInvalid entry, insert a valid item price:\n");
        }

        //INSERTING ITEM QUANTITY

        System.out.println("\nInsert item quantity: \n");

        //INFINITE LOOP FOR REENTERING QUANTITY IF INT VALUE IS INCORRECT, USING SCANNERS FUNCTION nextInt, AND USING A TRY CATCH BLOCK FOR EXCEPTIONS

        while (true) {

            try {

            int nmb = sc.nextInt();

            quantity = nmb;
            break;

            }
            catch (InputMismatchException n) {

                System.err.println("\nInvalid entry, insert a valid item quantity:\n");
                sc.nextLine();
            }

        }

        //INPUT FOR IS THE ITEM IMPORTED

        System.out.println("\nIs the item imported : (Y/N) [any other key except y/Y is considered as N]\n");
        sc.nextLine();

        String str = sc.nextLine();

        if (str.matches("y") || str.matches("Y"))
            imported = true;
        else
            imported = false;

        //INPUT FOR TYPE OF ITEM

        System.out.println("\nSelect the type of item (select by typing one of the numbers next to the options)\n1. Book\n2. Food\n3. Medical\n4. Other\n");

        //INFINITE LOOP FOR REENTERING IN CASE OF INVALID INPUT

        while (true) {


            //FIRST CHECK IF INPUT IS INTEGER, USING SCANNER FUNCTION nextInt AND A TRY CATCH BLOCK

            boolean isNumber = false;
            int nmb = 0;

            try {

            nmb = sc.nextInt();
            isNumber = true;

            }
            catch (InputMismatchException n) {

                System.err.println("\nInvalid entry, select the type of item (select by typing one of the numbers next to the options)\n1. Book\n2. Food\n3. Medical\n4. Other\n");
                sc.nextLine();
            }

            //IF THE INPUT IS AN INTEGER, CHECK IF IT IS A NUMBER BETWEEN 1 AND 4, IF NOT, GO BACK TO REENTERING A VALUE

            if (isNumber) {
                if (nmb == 1) {
                    type = 1;
                    break;
                }
                else if (nmb == 2) {
                    type = 2;
                    break;
                }
                else if (nmb == 3) {
                    type = 3;
                    break;
                }
                else if (nmb == 4) {
                    type = 4;
                    break;
                }
                else {
                    System.out.println("\nInvalid entry, select the type of item (select by typing one of the numbers next to the options)\n1. Book\n2. Food\n3. Medical\n4. Other\n");
                }
            }

        }

        //CREATING AN ITEM AND ADDING IT TO THE ITEM LIST

        Item item = new Item(name, price, quantity, imported, type);

        items.add(item);

        //ASKING IF ANOTHER ITEM WANTS TO BE ADDED, IF NOT, THE INFINITE WHILE LOOP IS BROKEN

        System.out.println("\nDo you want to add another item : (Y/N) [any other key except y/Y is considered as N]\n");
        sc.nextLine();

        String str1 = sc.nextLine();

        if (str1.matches("y") || str1.matches("Y"))
            itemCount++;
        else
            break;

        }


        System.out.println("\nThe receipt details are listed below: \n");
        System.out.println("Item name   Quantity    Price   Imported    Tax  Taxed Price\n");

        double totalTax = 0;
        double totalPrice = 0;

        //WRITING OUT ALL OF THE ITEMS AND THEIR COMPONENTS

        for (int i = 0; i < items.size(); i++) {
            String importHelp = "No";
            if (items.get(i).getImported())
                importHelp = "Yes";
            System.out.println(items.get(i).getName() + "   " + items.get(i).getQuantity() + "   " + items.get(i).getPrice()
             + "    " + importHelp + "   " + items.get(i).calculateItemTax() + "    " + items.get(i).getTaxedPrice() + "\n" );

             //CALCULATING THE TOTAL TAX AND TAXED PRICE FOR ALL ITEMS
             totalTax += items.get(i).calculateItemTax();
             totalPrice += items.get(i).getTaxedPrice();

        }

        totalTax = Math.round(totalTax*100.0)/100.0;
        totalPrice = Math.round(totalPrice*100.0)/100.0;

        System.out.println("\nThe total tax is: " + totalTax);
        System.out.println("\nThe total price is: " + totalPrice);

    }
}
