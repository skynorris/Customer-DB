///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  InteractiveDBTester.java
// File:             ArrayListItertor.java
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

public class ArrayListIterator<E> implements Iterator<Customer>  {

	//data fields
	int curr;
	int size; 
	ArrayList<Customer> items;

	public ArrayListIterator(ArrayList<Customer> list){
		
		items = list;
		curr = 0 ;
		size = list.size();
	}

	@Override
	public boolean hasNext() {
		
		// make sure not at end of list
		return (curr < size);
	}

	@Override
	public Customer next() throws NoSuchElementException {
		
		//  make sure not at end of list and return curr item
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		Customer temp = items.get(curr);
		curr++;
		return temp;


	}

	public void remove(){
		
		//make sure the list isnt empty and remove item at curr poss
		if(curr == 0){
			new NoSuchElementException();
		}
	items.remove(curr);
	curr --;
	size --; 
	}

}
