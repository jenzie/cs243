/**
 * @author: Jenny Zhen
 * @name: BinSet.java
 * @date: 04.18.12
 */
/**
 * $Id: BinSet.java,v 1.4 2012-04-25 02:40:55 jxz6853 Exp $
 * $Revision: 1.4 $
 * $Log: BinSet.java,v $
 * Revision 1.4  2012-04-25 02:40:55  jxz6853
 * Completed.
 *
 * Revision 1.3  2012-04-24 02:17:22  jxz6853
 * Fix contains().
 *
 * Revision 1.2  2012-04-23 23:40:24  jxz6853
 * How to do the second toArray()?
 *
 * Revision 1.1  2012-04-18 17:01:37  jxz6853
 * *** empty log message ***
 *
 */
import java.util.*;

public class BinSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> s = new ArrayList<E>();

    /**
     * Construct an empty BinSet.
     */
    public BinSet(){}

    /**
     * Construct a BinSet from the supplied collection.
     * @param c - the collection of elements used to build the set.
     */
    public BinSet(Collection<? extends E> c){
    	addAll(c);
    }
    
   /**
    * Searches the ArrayList for the element 'e'.
    * @param e the element we're looking for
    * @return the index where e is. -1 is not in set.
    */
    private int binarySearch(E e){
    	if(isEmpty())
			return -1;
    	
    	int start = 0;
    	int end = s.size() - 1;
		int pivot = s.size() / 2;
		int comp = 0;
		
		while(pivot >= start && pivot <= end) {
			comp = e.compareTo(s.get(pivot));
			if(comp == 0)
				return pivot;
			else if(start == end)
				return -1;
			
			if(comp < 0) {
				end = pivot;
				end--;
				pivot = (pivot + start) / 2;
			} else if(comp > 0) {
				start = pivot;
				start++;
				pivot = (pivot + end + 1) / 2;
			} 
		} return -1;
    }

    /**
     * Adds the specified element to this set if it is not already present.
     * @param e - element to be added to this set .
     * @return true if this set did not already contain the specified element
     */
    public boolean add(E e) {
    	if(!contains(e)) {
    		s.add(e);
    		Collections.sort(s);
    		return true;
    	} return false;
    }

    /**
     * Adds all of the elements in the specified collection to this set if 
     * they're not already present.
     * @param c - collection containing elements to be added to this set
     * @return true if this set changed as a result of the call
     */
    public boolean addAll(Collection<? extends E> c) {
    	boolean added = false;
    	for(E key : c) {
    		if(add(key))
    			added = true;
    	} return added;
    }

    /**
     * Removes all of the elements from this set.
     */
    public void clear() {
    	s.clear();
    }
     
    /**
     * Returns true if this set contains the specified element.
     * @param o - element whose presence in this set is to be tested
     * @return true if this set contains the specified element
     */
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
		return -1 != binarySearch((E)o);
    }

    /**
     * Returns true if this set contains all of the elements of the 
     * specified collection.
     * @param c - collection to be checked for containment in this set
     * @return true if this set contains all elements of the specified collection
     */
    public boolean containsAll(Collection<?> c) {
    	return s.containsAll(c);
    }

    /**
     * Returns true if this set contains no elements.
     * @return true if this set contains no elements
     */
    public boolean isEmpty() {
    	return size() == 0;
    }
          
    /**
     * Returns an iterator over the elements in this set.
     * @return an iterator over the elements in this set
     */
    public Iterator<E> iterator() {
		return s.iterator();
    }
    
    /**
     * Removes the specified element from this set if it is present.
     * @param o - object to be removed from this set, if present
     * @return true if this set contained the specified element
     */
    public boolean remove(Object o) {
    	if(contains(o))
    		return s.remove(o);
    	return false;
    }
     
    /**
     * Retains only the elements in this set that are contained in the 
     * specified collection.
     * @param c - collection containing elements to be removed from this set
     * @return true if this set changed as a result of the call
     */
    public boolean retainAll(Collection<?> c) {
		return s.retainAll(c);
    }
    

    /**
     * Returns the number of elements in this set (its cardinality).
     * @return the number of elements in this set (its cardinality)
     */
    public int size() {
    	return s.size();
    }
     
    /**
     * Returns an array containing all of the elements in this set.
     * @return an array containing all the elements in this set
     */
    public Object[] toArray() {
    	Object[] array = new Object[size()];
    	int index = 0;
    	for(E key : s) {
    		array[index] = key;
    		index += 1;
    	} return array;
    }
     
    
    /**
     * Returns an array containing all of the elements in this set; the 
     * runtime type of the returned array is that of the specified array.
     * 
     * @param a - the array into which the elements of this set are to be 
     * stored, if it is big enough; otherwise, a new array of the same 
     * runtime type is allocated for this purpose. 
     * 
     * @return an array containing all the elements in this set
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
		return s.toArray(a);
    }

    /**
     * Returns a string representation of the set.
     * @return a string representation of the set
     */
    public String toString(){
		return s.toString();
    }

    /**
     * Displays a message followed by success or failure indicating
     * whether or not a particular test was successful.
     *
     * @param message The String form of the message.
     * @param       b    A boolean indicating whether the test was successful or not.    
     */

    private static void resultTest(String message, boolean b){
		if (b){
		    System.out.println(message + " success");
		} else {
		    System.out.println(message + " failure");
		}
    }
    
    /**
     * Runs a suite of tests to validate the implementation of BinSet
     * for Integer elements.
     */
    private static void testInteger(){
	Set<Integer> set = new BinSet<Integer>(Arrays.asList(1,3));
	
	resultTest("constructor 1",
		   Arrays.equals(set.toArray(), Arrays.asList(1,3).toArray()));
	
	set.add(2);
	resultTest("add 1",
		   Arrays.equals(set.toArray(), Arrays.asList(1,2,3).toArray()));
	
	resultTest("contains 1", set.contains(1));
	resultTest("contains 2", set.contains(2));
	resultTest("contains 3", set.contains(3));
	resultTest("contains 4", !set.contains(4));
	
	resultTest("size 1", set.size() == 3);
	
	set.clear();
	resultTest("clear/size", set.size() == 0);
	resultTest("clear/isEmpty", set.isEmpty());
	
	set.addAll(Arrays.asList(1,2,3));
	resultTest("addAll 1",set.size() == 3);
	
	resultTest("containsAll 1", set.containsAll(Arrays.asList(3,2)));
	resultTest("containsAll 2", !set.containsAll(Arrays.asList(4,3)));
	
	set.remove(2);
	resultTest("remove 1", 
		   Arrays.equals(set.toArray(), Arrays.asList(1,3).toArray()));
	
	Integer[] a = {1,3};
	int j = 0;
	for (Integer i : set){
	    resultTest("iterator " + i, i.equals(a[j]));
	    j++;
	}
	
	set.retainAll(Arrays.asList(3,4));
	resultTest("retainAll 1", 
		   Arrays.equals(set.toArray(), Arrays.asList(3).toArray()));
	
	resultTest("toArray(array) 1", 
		   Arrays.equals(set.toArray(new Integer[0]), 
				 Arrays.asList(3).toArray()));
    }
    
    /**
     * Runs a suite of tests to validate the implementation of BinSet
     * for Character elements.
     */
    private static void testChar(){
	Set<Character> set = new BinSet<Character>(Arrays.asList('a','c'));
	
	resultTest("constructor 1",
		   Arrays.equals(set.toArray(), Arrays.asList('a','c').toArray()));
	
	set.add('b');
	resultTest("add 1",
		   Arrays.equals(set.toArray(), Arrays.asList('a','b','c').toArray()));
	
	resultTest("contains a", set.contains('a'));
	resultTest("contains b", set.contains('b'));
	resultTest("contains c", set.contains('c'));
	resultTest("contains d", !set.contains('d'));
	
	resultTest("size 1", set.size() == 3);
	
	set.clear();
	resultTest("clear/size", set.size() == 0);
	resultTest("clear/isEmpty", set.isEmpty());
	
	set.addAll(Arrays.asList('a','b','c'));
	resultTest("addAll 1",set.size() == 3);
	
	resultTest("containsAll 1", set.containsAll(Arrays.asList('c','b')));
	resultTest("containsAll 2", !set.containsAll(Arrays.asList('d','c')));
	
	set.remove('b');
	resultTest("remove 1", 
		   Arrays.equals(set.toArray(), Arrays.asList('a','c').toArray()));
	
	Character[] a = {'a','c'};
	int j = 0;
	for (Character i : set){
	    resultTest("iterator " + i, i.equals(a[j]));
	    j++;
	}
	
	set.retainAll(Arrays.asList('c','d'));
	resultTest("retainAll 1", 
		   Arrays.equals(set.toArray(), Arrays.asList('c').toArray()));
	
	resultTest("toArray(array) 1", 
		   Arrays.equals(set.toArray(new Character[0]), 
				 Arrays.asList('c').toArray()));
    }

    /**
     * The main method for BinSet.  It runs any test scaffolding methods such as testInteger.
     *
     * @param args Command line arguments are not used.
     *
     */

    public static void main(String[] args){
	System.out.println("Testing an integer set...");
    testInteger();
	System.out.println("\nTesting a character set...");
	testChar();
   }

}