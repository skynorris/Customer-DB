///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  InteractiveDBTester.java
// File:             CustomerDatabase.java
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

public class CustomerDatabase  {

	//Data Fields
	private ArrayList<Customer> database ;


	public CustomerDatabase(){
		database = new ArrayList<Customer>();
	}

	void addCustomer(String c){
	
		//exception check
		if (c == null) {throw new IllegalArgumentException();}
		
		//check to see if customer already exists
		Iterator<Customer> itr = this.iterator(); 
		while(itr.hasNext()){
			Customer temp = itr.next();
			if(temp.getUsername().equals(c)){
				return;
				
			}
		}

		//add customer
		Customer cus = new Customer(c);
		database.add(cus);
	}


	void addProduct(String c, String p){
		
		//null check
		if (c == null || p == null) {throw new IllegalArgumentException();}

		//look for customer
		Iterator<Customer> itr = this.iterator(); 
		while(itr.hasNext()){
			Customer temp = itr.next();
			if(temp.getUsername().equals(c)){
				Iterator<String> itr2 = temp.getWishlist().iterator();
				
				//look to see if product already exists
				while(itr2.hasNext()){
					if(itr2.next().equals(p)){
						return;
					}
				} 

				temp.getWishlist().add(p);
				return;
			}
		}
		
		//if customer doesn't exist
		throw new IllegalArgumentException();

	}

	public boolean containsCustomer(String c){
		
		//exception check
		if (c == null) {throw new IllegalArgumentException();}

		//look for customer
		Iterator<Customer> itr = this.iterator(); 
			while(itr.hasNext()){
			if(itr.next().getUsername().equals(c)){
				return true;
			}
		}


		return false;	
	}

	public boolean containsProduct(String p){

		//exception check
		if (p == null) {throw new IllegalArgumentException();}
		
		//iterate through each customer
		Iterator<Customer> itr = this.iterator(); 
		while(itr.hasNext()){
			Customer temp = itr.next();

			//look through each wishlist and see if the product already exists
			Iterator<String> itr2 = temp.getWishlist().iterator();
			while(itr2.hasNext()){
				if(itr2.next().equals(p)){
					return true;
				}
			} 
		}

		return false;
	}

	boolean hasProduct(String c, String p){

		//null check
		if (c == null || p == null) {throw new IllegalArgumentException();}
		
		//iterateh through each customer
		Iterator<Customer> itr = this.iterator(); 
		while(itr.hasNext()){
			Customer temp = itr.next();

			//look to see if each customer has the product p
			if(temp.getUsername().equals(c)){
				Iterator<String> itr2 = temp.getWishlist().iterator();
				while(itr2.hasNext()){
					String temp1 = itr2.next();
					if(temp1.equals(p)){
						return true;
					}
				} 
				return false;
			}
		}

		//if customer doesnt exist
		throw new IllegalArgumentException();

	}

	public List<String> getCustomers(String p){
		
		//exception check
		if (p == null) {throw new IllegalArgumentException();}
		
		//master list of customers who own product, to be filled
		ArrayList<String> contains = new ArrayList<String>();
		
		//iterate through customers
		Iterator<Customer> itr = this.iterator(); 
		while(itr.hasNext()){
			Customer temp = itr.next();

			//iterate through wishlist, look to see if own product
			Iterator<String> itr2 = temp.getWishlist().iterator();
			while(itr2.hasNext()){
				String temp1 = itr2.next();
				if(temp1.equals(p)){
					//add customer to master list if they own the product
					contains.add(temp.getUsername());
				}
			}
		}
		
		//make sure at least one customer owns the product
		if(contains.isEmpty()){
			return null;
		}
		return contains;
	}

	public List<String> getProducts(String c){
		
		//exception check
		if (c == null) {throw new IllegalArgumentException();}
		
		// look for customer
		Iterator<Customer> itr = this.iterator(); 
		while(itr.hasNext()){
			Customer temp = itr.next();

			//return his/her wish list
			if(temp.getUsername().equals(c)){
				return temp.getWishlist();
			}
		}
		return null;
	}

	public Iterator<Customer> iterator(){
		ArrayListIterator<Customer> itr = new ArrayListIterator<Customer>(database);
		return itr;
	}

	public boolean removeCustomer(String c){
		
		//exception check
		if (c == null) {throw new IllegalArgumentException();}
		
		//look for customer, and remove him/her from database
		Iterator<Customer> itr = this.iterator(); 
		while(itr.hasNext()){
			Customer temp = itr.next();
			if(temp.getUsername().equals(c)){
				database.remove(temp);
				return true;
			}
		}
		return false;
	}

	boolean removeProduct(String p){
		
		//exception check
		if (p == null) {throw new IllegalArgumentException();}
		
		//make sure the product exists
		if(!containsProduct(p)){
			return false;
		}
		
		//look for product and remove from each customer who owns it, iterate through customers
		Iterator<Customer> itr = this.iterator(); 
		while(itr.hasNext()){
			Customer temp = itr.next();
			if(hasProduct(temp.getUsername(), p)){
				temp.getWishlist().remove(p);
			}
		}

		return true;
	}
	
	int size(){
		return database.size();
	}
	
	public static void main(String[] args) {
		// 
	}
}
