package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import given.ContactInfo;

/*
 * A phonebook class that should:
 * - Be efficiently (O(log n)) searchable by contact name
 * - Be efficiently (O(log n)) searchable by contact number
 * - Be searchable by e-mai (can be O(n)) 
 * 
 * The phonebook assumes that names and numbers will be unique
 * Extra exercise (not to be submitted): Think about how to remove this assumption
 * 
 * You need to use your own data structures to implement this
 * 
 * Hint: You need to implement a multi-map! 
 * 
 */
public class PhoneBook {

	/*
	 * ADD MORE FIELDS IF NEEDED
	 * 
	 */
	BinarySearchTree<String, ContactInfo> contactName = new BinarySearchTree<>();
	BinarySearchTree<String, ContactInfo> contactNumber = new BinarySearchTree<>();

	public PhoneBook() {
		contactName.setComparator((String o1, String o2) -> o1.compareTo(o2));
		contactNumber.setComparator((String o1, String o2) -> o1.compareTo(o2));
	}
	

	// Returns the number of contacts in the phone book
	public int size() {
		return contactName.size;
	}

	// Returns true is the phone book is empty, false otherwise
	public boolean isEmpty() {
		return contactName.size == 0;
	}

//Adds a new contact overwrites an existing contact with the given info
	// Args should be given in the order of e-mail and address which is handled for
	// you
	// Note that it can also be empty. If you do not want to update a field pass
	// null
	public void addContact(String name, String number, String... args) {
		int numArgs = args.length;
		String email = null;
		String address = null;

		/*
		 * Add stuff here if needed
		 */

		if (numArgs > 0)
			if (args[0] != null)
				email = args[0];
		if (numArgs > 1)
			if (args[1] != null)
				address = args[1];
		if (numArgs > 2)
			System.out.println("Ignoring extra arguments");

		/*
		 * TO BE IMPLEMENTED
		 * 
		 */
		ContactInfo c1 = new ContactInfo(name, number);
		c1.setEmail(email);
		c1.setAddress(address);
		contactName.put(name, c1);
		contactNumber.put(number, c1);
	}

	// Return the contact info with the given name
	// Return null if it does not exists
	// Should be O(log n)!
	public ContactInfo searchByName(String name) {
		return contactName.get(name);
	}

	// Return the contact info with the given phone number
	// Return null if it does not exists
	// Should be O(log n)!
	public ContactInfo searchByPhone(String phoneNumber) {
		return contactNumber.get(phoneNumber);
	}

	// Return the contact info with the given e-mail
	// Return null if it does not exists
	// Can be O(n)
	public ContactInfo searchByEmail(String email) {

		for (BinaryTreeNode<String, ContactInfo> node : contactName.getNodesInOrder()) {
			if (Objects.equals(email, node.getValue().getEmail())) {
				return node.getValue();
			}
		}
		return null;
	}

	// Removes the contact with the given name
	// Returns true if there is a contact with the given name to delete, false
	// otherwise
	public boolean removeByName(String name) {

		ContactInfo ret = contactName.remove(name);
		if (ret == null) {
			return false;
		}
		contactNumber.remove(ret.getNumber());
		return true;
	}

	// Removes the contact with the given name
	// Returns true if there is a contact with the given number to delete, false
	// otherwise
	public boolean removeByNumber(String phoneNumber) {

		ContactInfo ret = contactNumber.remove(phoneNumber);
		if (ret == null) {
			return false;
		}
		contactName.remove(ret.getName());
		return true;
	}

	// Returns the number associated with the name
	public String getNumber(String name) {
		ContactInfo tmp = searchByName(name);
		return tmp == null ? null : tmp.getNumber();
	}

	// Returns the name associated with the number
	public String getName(String number) {
		ContactInfo tmp = searchByName(number);
		return tmp == null ? null : tmp.getName();
	}

	// Update the email of the contact with the given name
	// Returns true if there is a contact with the given name to update, false
	// otherwise
	public boolean updateEmail(String name, String email) {
		ContactInfo ret = searchByName(name);
		ret.setEmail(email);
		return ret == null ? false : true ;
	}

	// Update the address of the contact with the given name
	// Returns true if there is a contact with the given name to update, false
	// otherwise
	public boolean updateAddress(String name, String address) {
		ContactInfo ret = searchByName(name);
		ret.setAddress(address);
		return ret == null ? false : true ;
	}

	// Returns a list containing the contacts in sorted order by name
	public List<ContactInfo> getContacts() {
		List<ContactInfo> contactList = new ArrayList<ContactInfo>();
		for (BinaryTreeNode<String, ContactInfo> node : contactName.getNodesInOrder()) {
			contactList.add(node.getValue());
		}
		return contactList;
		
	}

	// Prints the contacts in sorted order by name
	public void printContacts() {
		
		System.out.println(getContacts());
		}
	}
