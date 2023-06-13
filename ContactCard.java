package Main;
import java.util.Date;

import DataStructures.List.DoublyLinkedList;
import DataStructures.List.List;

/**
 * Contact Card Class to hold all the information 
 * regarding one contact inside the directory. The class facilitates
 * the data of the contact users for storing and manipulating. It has getters and setters for all
 * attributes for easy access of the data. A Doubly Linked List is used as the list of favorites, 
 * where the first node is in index 0, the following node at index 1 and so on. This list is used due
 * to easy traversal between nodes.
 * 
 * 
 * @author bermed28 & ricardorivera
 */
public class ContactCard {
	/**
	 * Private attributes for the ID, name, job, phone number and list of friends 
	 * for the contact user
	 */
	
	private int idNumber;
	private String name;
	private String job;
	private String phoneNumber;
	private Date birthday;
	private String email;
	private DoublyLinkedList<ContactCard> favorites;
	
	/**
	 * Constructor for the ContactCard class which holds all the information on
	 * the contact users. A Doubly Linked List is used as the list of favorites, 
	 * where the first node is in index 0, the following node at index 1 and so on. This list is used due
	 * to easy traversal between nodes.
	 * @param idNum users id Number
	 * @param nam	users name
	 * @param jo	users job
	 * @param phoneNum	users phone Number
	 * @param email		users email
	 * @param birth		users birthday
	 * @param f			List of friends
	 */
	
	public ContactCard(int idNum, String nam, String jo, String phoneNum, String email, Date birth, DoublyLinkedList<ContactCard> f) {
		this.idNumber=idNum;
		this.name=nam;
		this.job=jo;
		this.phoneNumber=phoneNum;
		this.email=email;
		this.birthday=birth;
		this.favorites= f;
	}
	
	/**
	 * Constructor class without parameters for default initialization
	 */
	
	public ContactCard() {
		this.idNumber=0;
		this.name="";
		this.job="";
		this.phoneNumber="";
		this.email="";
		this.birthday= null;
		this.favorites= new DoublyLinkedList<ContactCard>();
	}
	
	
	
	/**Setters*/
	
	/**
	 * Standard setter  method for setting a new value to the Id Number
	 * of the contact user
	 * @param e new Id number 
	 */
	
	public void setID(int e) {
		this.idNumber=e;
	}
	
	/**
	 * Standard setter method for setting a new value to the email
	 * of the contact user
	 * @param e new email
	 */
	public void setEmail(String e) {
		this.email=e;
	}
	
	/**
	 * Standard setter method for setting a new value to the name
	 * of the contact user
	 * @param e new name
	 */
	
	public void setName(String e) {
		this.name=e;
	}
	
	/**
	 * Standard setter method for setting a new value to the job
	 * of the contact user
	 * @param e new job 
	 */
	public void setJobTitle(String e) {
		this.job=e;
	}
	
	/**
	 * Standard setter  method for setting a new value to the phone number
	 * of the contact user
	 * @param e new phone number 
	 */
	public void setPhone(String e) {
		this.phoneNumber=e;
	}
	/**
	 * Standard setter  method for setting a new value to the birthday
	 * of the contact user
	 * @param e new birthday
	 */
	public void setBirthDay(Date e) {
		this.birthday=e;
	}
	
	/**
	 * Standard setter  method for setting a new value to the favorites
	 * of the contact user
	 * @param e new birthday
	 */
	public void setFriends(DoublyLinkedList<ContactCard> e ) {
		this.favorites=e;
	}
	
	
	
	
	
	
	/**Getters*/
	
	/**
	 * Standard getter  method for the id number of the contact user
	 * @return id number
	 */
	public int getID() {
		return this.idNumber;
		
	}
	
	/**
	 * Standard getter method for the name of the contact user
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Standard getter for the job of the contact user
	 * @return job
	 */
	public String getJobTitle() {
		return this.job;
	}
	
	/**
	 * Standard getter method for the phone number of the contact user
	 * @return phone number
	 */
	public String getPhone() {
		return this.phoneNumber;
	}
	

	/**
	 * Standard getter method for the birthday of the contact user
	 * @return birthday
	 */
	
	public Date getBirthDay() {
		return this.birthday;
	}
	
	/**
	 * Standard getter  method for the favorites of the contact user
	 * @return favorites list
	 */
	
	public List<ContactCard> getFriends(){
		return this.favorites;
	}
	

	/**
	 * Standard getter method for the birthday of the contact user
	 * @return email
	 */
	
	public String getEmail() {
		return this.email;
	}
	
	
	
	
	
	
}
