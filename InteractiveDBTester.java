///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 1
// Files:            InteractiveDBTester.java, CustomerDatabase.java, Customer.java, ArrayListIterator.java
// Semester:         Summer 2016
//
// Author:           Skyler Norris
// Email:            sgnorris@wisc.edu
// CS Login:         skyler
// Lecturer's Name:  Strominger
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Marius Facktor
// Email:            marius.facktor@doit.wisc.edu
// CS Login:         marius

import java.util.*;
import java.io.*;

public class InteractiveDBTester {
	public static void main(String[] args) throws IOException {

		// *** Add code for steps 1 - 3 of the main method ***
		//check for exactly one command line argument, one text file
		if(args.length!=1 ){
			System.out.println("Please provide input file as command-line argument");
			System.exit(0);
		}


		//Check whether the input file exists and is readable
		if(args[0] == null){
			System.out.println("Error: Cannot acess input file");
			System.exit(0);
		}

		CustomerDatabase db = new CustomerDatabase();

		//read in text file passed in through command line
		File inFile = new File(args[0]);
		
		try {
			//create buffreader to read text from file
			BufferedReader in = new BufferedReader(new FileReader(inFile));
			String line;
			
			//create an array of strings for each line of the text file, array elements seperated by ,
			while ((line = in.readLine()) != null) {
				String delims = "[,]";
				String[] customer = line.split(delims);

				//add customer to database from each line
				db.addCustomer(customer[0]);

				//add products to the accompanying customer from that line
				for(int i=1; i<customer.length; i++){
					db.addProduct(customer[0], customer[i]);
				}

			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		Scanner stdin = new Scanner(System.in);  // for reading console input
		printOptions();
		boolean done = false;

		while (!done) {
			System.out.print("Enter option ( dfhisqr ): ");
			String input = stdin.nextLine();
			input = input.toLowerCase();  // convert input to lower case

			// only do something if the user enters at least one character
			if (input.length() > 0) {
				char choice = input.charAt(0);  // strip off option character
				String remainder = "";  // used to hold the remainder of input
				if (input.length() > 1) {
					// trim off any leading or trailing spaces
					remainder = input.substring(1).trim(); 
				}

				switch (choice) {

				case 'd':
					
					//if product exists, remove from database
					if(db.containsProduct(remainder)){
						db.removeProduct(remainder);
						System.out.println("product discontinued");
					}

					else{
						System.out.println("product not found");
					}

					break;


				case 'f':
					// if customer exists display his/her product list
					if(db.containsCustomer(remainder)){
						System.out.print(remainder + ":");
						for(int i = 0; i<db.getProducts(remainder).size() ; i++){
							System.out.print(db.getProducts(remainder).get(i));
							
							//avoid having a comma after last product
							if(i < db.getProducts(remainder).size()-1){
								System.out.print(",");
							}
						}
						System.out.println();
					}
					else {
						System.out.println("customer not found");
					}

					break;

				case 'h': 
					printOptions();
					break;

				case 'i':
					
					// data fields for products/customer
					ArrayList<String> master = new ArrayList<String>(); //to hold all the products in the store
					Iterator<Customer> itr = db.iterator(); 
					int max = 0;
					int min = 1000;
					
					//data fields for customers/products
					int max2 = 0;
					int min2 = 1000;
					String popular = "";
					//used to hold number of customers/product of each product to calculate a mean
					ArrayList<Integer> mean2 = new ArrayList<Integer>();
					
					//go through each customers wishlist size to try to find a min and max
					while(itr.hasNext()){
						Customer temp = itr.next();
						if(temp.getWishlist().size() > max){
							max = temp.getWishlist().size();
						}
						if(temp.getWishlist().size() < min){
							min = temp.getWishlist().size();
						}
						
						//add all products to list
						master.addAll(temp.getWishlist());
					}

					
					//remove duplicates from master list to create a list of all unique prodcuts in store //
					//Make a copy of combinedList called returningList
					Iterator<String> itr3 = master.iterator();
					List<String> returningList = new ArrayList<String>();
					while (itr3.hasNext()) {
						returningList.add(itr3.next());
					}

					//Iterate through combinedList and returningList.
					//When an String in combinedList is equal to more than one String in 
					//returningList, remove the element from returningList.
					Iterator<String> itr4 = master.iterator();
					while (itr4.hasNext()) {
						String temp = itr4.next();
						Iterator<String> itr5 = returningList.iterator();
						int count = 0;
						while (itr5.hasNext()) {
							String temp2 = itr5.next();
							if (temp.equals(temp2)) {
								count++;
								if (count > 1) {itr5.remove();}
							}
						}
					}
					System.out.println("Customers: " + db.size() + ", Products: " + returningList.size());

					//mean wish list size
					int mean = master.size() / db.size();

					System.out.println("# of products/customer: most " + max + ", least " + min +", average " + mean);
					
					//go through each product to see find min max
					for(int i = 0; i <returningList.size(); i++){
						if(db.getCustomers(returningList.get(i)).size() >= max2){
							max2 = db.getCustomers(returningList.get(i)).size();
						}
						if(db.getCustomers(returningList.get(i)).size() < min2){
							min2 = db.getCustomers(returningList.get(i)).size();
						}
						mean2.add(db.getCustomers(returningList.get(i)).size());// to calulate mean
					}
					
					//loop through product list to granb most popular product name
					for(int i = 0; i <returningList.size(); i++){
						if(db.getCustomers(returningList.get(i)).size() == max2){
							
							//if else loop to prevent , after last prodcut
							if(popular.isEmpty()){
								popular += returningList.get(i);
							}
							else{	popular += ", " + returningList.get(i);
							}
						}
					}
					
					
					//calculate mean
					int count =0;
					for(int i=0; i <mean2.size(); i++){
						count = count + mean2.get(i);
					}
					int average2 = count / mean2.size();

					//display option 3
					System.out.println("# of customers/product: most " + max2 +", least "+ min2 + ", average " + average2);

					//display option 4
					System.out.println("Most popular product: " + popular + " [" +max2 +"]");

					break;

				case 's':
					// check to see if prodcut exists
					if(!db.containsProduct(remainder)){
						System.out.println("product not found");
					}
					else{
						
						// go through customer list to see if they own product
						db.getCustomers(remainder);
						System.out.print(remainder + ":");
						for(int i = 0; i <db.getCustomers(remainder).size(); i++){
							System.out.print(db.getCustomers(remainder).get(i));
							if(i < db.getCustomers(remainder).size()-1){ // prevent comma after last customer
								System.out.print(",");
							}
						}
					System.out.println();
					}
					break;

				case 'q':
					done = true;
					System.out.println("quit");
					break;

				case 'r':
					// check to see if customer exists and remove them if they do
					if(db.containsCustomer(remainder)){
						db.removeCustomer(remainder);
						System.out.println("customer removed");
					}else{
						System.out.println("customer not found");
					}
					break;

				default:  // ignore any unknown commands
					break;
				}
			}
		}

		stdin.close();
	}

	/**
	 * Prints the list of command options along with a short description of
	 * one.  This method should not be modified.
	 */
	private static void printOptions() {
		System.out.println("d <product> - discontinue the given <product>");
		System.out.println("f <customer> - find the given <customer>");
		System.out.println("h - display this help menu");
		System.out.println("i - display information about this customer database");
		System.out.println("s <product> - search for the given <product>");
		System.out.println("q - quit");
		System.out.println("r <customer> - remove the given <customer>");
	}
}