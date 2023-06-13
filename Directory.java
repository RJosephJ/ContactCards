package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import DataStructures.List.List;
import DataStructures.List.ArrayList;
import DataStructures.List.DoublyLinkedList;

/**
 * The Directory class is an implementation of the BaseDirectory interface. In this class the users data 
 * taken from the csv file is stored and used. It consists of 4 main methods using the users information 
 * from the file.
 * @author ricardorivera
 *
 */

public class Directory implements BaseDirectory{
	
	/**
	 * Private attributes that contain the data from the csv file 
	 * for facilitating the use of the text file data. An Array List is used for storing the contact users 
	 * where the start of the list is in index 0, and the next is in index 1 and so on. An Array List is used due to the 
	 * simple nature of storing.
	 */
	
	private ArrayList<ContactCard> contacts = new ArrayList<ContactCard>();
	private String path;
	
	/**
	 * The constructor of the Directory class takes no parameters
	 * 
	 */
	
	public Directory() {
		
	}
	
	/**
	 * Implementation of createDirectory that takes in a string representing 
	 * the path of contactDirectoy.csv and parses said csv. 
	 * Once parsed, the Directory class will have filled up the private directory 
	 * ArrayList<ContactCard> field
	 * 
	 * Using a buffer reader the lines of the csv file are split
	 * by comma to ease the initialization of the new users data to be stores in
	 * the private ArrayList contacts
	 * 
	 * The created array from the split allows for easy access to the information of the user
	 * where the first element in index 0 should be the id number, the second element in index 1
	 * should be the name, and so on
	 * 
	 * Through each iteration of the index the values are set to the corresponding attributes
	 * a user needs to be initialized and stored in the contacts list
	 * 
	 * 
	 * 
	 * @param path - Path to contactDirectory.csv
	 * @author ricardorivera
	 */
	
	@Override
	public void createDirectory(String path) {
		// TODO Auto-generated method stub
		String line ="";
		/**
		 * ArrayList<ArrayList<Integer>> holder is used for storing the list of friends id numbers
		 * each user has so that each element of the list is the collection of friends of the user
		 * in contacts at that index. Its used for an easier addition of the friends DoublyLinkedList
		 * the contact user has
		 */
		ArrayList<ArrayList<Integer>> holder = new ArrayList<ArrayList<Integer>>();
		try {
			
			/**
			 * The buffered reader class helps to read the csv file and use its data
			 */
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			while((line=br.readLine())!=null) {
				/**
				 * ArrayList<Integer> friends is used to store the friend's id numbers of a the current user line
				 * to then be stored in the holder ArrayList
				 */
				ArrayList<Integer> friends = new ArrayList<Integer>();
				ContactCard user = new ContactCard();
				String[] lineSplit = line.split(",");
				
				/**
				 * Depending on the index of the array we can tell which data is going to be held 
				 * for the initialization of the user
				 */
				
				/**
				 * If the lineSplit array has less than 5 elements that means that information is missing from
				 * the user
				 */
//				if(lineSplit.length<5) {
//					continue;
//				}
				for(int i=0;i<lineSplit.length;i++) {
					if(i==0) {
						int id =Integer.parseInt(lineSplit[i])  ;
						user.setID(id);
					}
					
					if(i==1) {
						String name=lineSplit[i];
						user.setName(name);
					}
					
					if(i==2) {
						String job= lineSplit[i];
						user.setJobTitle(job);
					}
					
					if(i==3) {
						String phone = lineSplit[i];
						user.setPhone(phone);
					}
					
					if(i==4) {
						String email = lineSplit[i];
						user.setEmail(email);
					}
					
					if(i==5) {
						String[] date = lineSplit[i].split("-");
						@SuppressWarnings("deprecation")
						Date f = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
						user.setBirthDay(f);
					}
					
					if(i>5) {
						int friendc = Integer.parseInt(lineSplit[i]);
						friends.add(friendc);
					}
					
				}
				holder.add(friends);
				this.contacts.add(user);
				
				
				
			}
			/**
			 * Iterate through holder and search the friends of each user
			 * Then we search for that friend in the contact directory ArrayList 
			 * and retrieve the ContactCard data type corresponding to the id number.
			 * We then are able to store the values in a doubly linked list and finish the user
			 * data requirements
			 * 
			 */
			for(int i=0;i<holder.size();i++) {
				DoublyLinkedList<ContactCard> friends = new DoublyLinkedList<ContactCard>();
				for(int y=0; y<holder.get(i).size();y++) {
					friends.add(findContact(holder.get(i).get(y)));
				}
				getContacts().get(i).setFriends(friends);
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Implementation that returns a list of ContactCards that represent all of the cards the contact 
	 * passed as parameter does not have marked as favorite, but his favorites do have marked 
	 * as favorite.
	 * 
	 * Think of it like Facebook’s “People You May Know” feature, 
	 * friends of friends who are not friends with you.
	 * 
	 * If contact does not have any friends, the method returns an empty list.
	 * 
	 * It uses two ArrayList cfriends and recommendedfriends for ease of data storage.
	 * First it gets all of the  friends the contact has and stores them in cfriends in the
	 * first loop. It the next iteration a double for loop is used for getting all the friends the 
	 * contact users favorites have. The loop checks if the friends is already a favorite of the contact
	 * If it isn't and the id is not of the target user, we store it in recommendedFriends.
	 * cFriends is then cleared to save space and is reused for removing any duplicates the recommendedFriends
	 * Array List might have.
	 *  
	 * @param contact - The target contact to find the recommendedFriends of.
	 * @return cFriends - A list of contact cards that represent all of the cards the contact 
	 * passed as parameter does not have marked as favorite, but his favorites do have marked 
	 * as favorite.
	 * @author ricardorivera
	 */

	@Override
	public List<ContactCard> recommendedFriends(ContactCard contact) {
		// TODO Auto-generated method stub

		List<ContactCard> cFriends =  new ArrayList<ContactCard>();
		List<ContactCard> recommendedFriends = new ArrayList<ContactCard>();
		for(int i=0;i<getContacts().size();i++) { 
			if(contact.getFriends().contains(getContacts().get(i))){
				cFriends.add(getContacts().get(i));
			}
		}
		for(int i=0;i<cFriends.size();i++) {
			for(int y=0;y<cFriends.get(i).getFriends().size();y++) {
				if(contact.getFriends().contains(cFriends.get(i).getFriends().get(y))==false) {
					if(cFriends.get(i).getFriends().get(y).getID()!=contact.getID()) {
						
						recommendedFriends.add(cFriends.get(i).getFriends().get(y));
					}
				}
			}
		}
		
		cFriends.clear();
		for(int i=0;i<recommendedFriends.size();i++) {
			if(cFriends.contains(recommendedFriends.get(i))==false) {
				
				cFriends.add(recommendedFriends.get(i));
			}
		}
		
		return cFriends;
	}
	/**
	 * Implementation that returns a list of common friends between 2 contact cards.
	 * 
	 * If c1 or c2 does not have any friends, the method returns an empty list.
	 * 
	 * This method uses an iteration that checks if the c2 ContactCard contains any friends of the 
	 * c1 ContactCard at index i of the c1 ContactCard friend's list. If it finds a common friend it then stores it in the
	 * ArrayList commonFriends to be returned 
	 * @param c1 - Contact 1
	 * @param c2 - Contact 2
	 * @return commonFriends - A list of common friends between c1 and c2
 	 * @author ricardorivera
	 */
	@Override
	public List<ContactCard> commonFriends(ContactCard c1, ContactCard c2) {
		// TODO Auto-generated method stub
		List<ContactCard> commonFriends = new ArrayList<ContactCard>();
		for(int i = 0; i<c1.getFriends().size();i++) {
			if(c2.getFriends().contains(c1.getFriends().get(i))) {
				commonFriends.add(c1.getFriends().get(i));
			}
		}
		
		return commonFriends;
	}
	
	/**
	 * Method that returns a list of contacts who share the same birthday as the contact passed as parameter.
	 * A shared birthday are the dates that share the same month and the same day.
	 * 
	 * This method uses a for loop to iterate through the contact's friend's list to find  if any friends 
	 * share the same month and day in their birthday Date object. If it finds any same birthdays then it adds the contact's friend to the
	 * commonBirthDays ArrayList
	 * @param contact - Target contact that contains the birthday to look for in common with other contacts
	 * @return commonBirthdays- a list of contacts who share the same birthday as contact.
	 * @author ricardorivera
	 */

	@SuppressWarnings("deprecation")
	@Override
	public List<ContactCard> shareBirthdays(ContactCard contact) {
		// TODO Auto-generated method stub
		List<ContactCard> commonBirthDays = new ArrayList<ContactCard>();
		for(int i=0; i<contact.getFriends().size();i++) {
			if(contact.getBirthDay().getMonth()==contact.getFriends().get(i).getBirthDay().getMonth()&&contact.getBirthDay().getDate()==contact.getFriends().get(i).getBirthDay().getDate()) {
				commonBirthDays.add(contact.getFriends().get(i));
			}
			
		}
		return commonBirthDays;
	}
	
	
	/**
	 * Method that returns the private ArryaList contacts
	 * @return contacts
	 */
	public List<ContactCard> getContacts(){
		return this.contacts;
	}
	
	/**
	 * Method that sets the private directory ArrayList contacts to a new value
	 * @param f- new contact ArrayList
	 */
	public void setContacts(List<ContactCard> f) {
		this.contacts=(ArrayList<ContactCard>) f;
	}
	
	/**
	 * Method that returns the ContactCard corresponding to the id given in the directory's 
	 * private ArrayList contacts
	 * @param id-id of the user to be able to find it
	 * @return ContactCard corresponding to the parameter f id
	 */
	public ContactCard findContact(int id) {
		for(int i=0;i<getContacts().size();i++) {
			if(getContacts().get(i).getID()==id) {
				return getContacts().get(i);
			}
		}
		
		return null;
	}
	
	

}

