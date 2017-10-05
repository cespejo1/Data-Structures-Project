/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresproject;

;

/**
 *A HashTable implementation for an Online phone book
 * @author carlosespejo initials,  CE
 */
public class HashTable {
    LinkedBag[] myArray;
    
    int itemsInArray;
    
    
    /** Call to constructor*/
    public HashTable(){        
        myArray = new LinkedBag[13];
        for (int i = 0; i < myArray.length; i++){
            myArray[i] = new LinkedBag();
        }
    }
    
    /**
     * Inserts new entries into the table
     * @param firstName :Student's first name
     * @param lastName  :Student's last name
     * @param emailAddress :Student's emailAddress
     * @param phoneNumber :Student's Phone number
     * CE
     */
    public void insertEntry(String firstName, String lastName, String phoneNumber, String emailAddress){
        int tableIndex;
       
        //create new Node
        Node newNode = new Node(firstName, lastName,phoneNumber , emailAddress);
        
        
        tableIndex = getHashIndex(firstName, lastName);//Returns hashIndex for table
        myArray[tableIndex].add(newNode);//add entry to list in index
        System.out.println(newNode.firstName + " "+ newNode.lastName
        + " has been added to the database");
    }
    
    /**
     * Deletes an Entry in database. 
     * @param firstName 
     * @param lastName 
     * CE
     */
    
    public void deleteEntry(String firstName, String lastName){
     int tableIndex;
     Node nodeToRemove = null;
     tableIndex = getHashIndex(firstName, lastName);//Returns hashIndex for table
    if(myArray[tableIndex].remove(firstName, lastName) == true){
        System.out.println(firstName + " " + lastName
        + " has been removed");
    }
    else{
     System.out.println(firstName + " " + lastName
        + " is not in the database");
    }
    
    }
    
    /**
     * Looks up data related to first and last name entries
     * @param firstName
     * @param lastName 
     * CE
     */
    public void lookUpEntry(String firstName, String lastName){
        int tableIndex;
        Node reference = null;
        
        tableIndex = getHashIndex(firstName, lastName);//Returns hashIndex for table
        reference = myArray[tableIndex].getReferenceTo(firstName, lastName);
        
        if(reference != null){
        System.out.print("The look up function recovered: ");
        reference.printName();//Print the Data
        }
        else{
            System.out.println(firstName + " " + lastName + " is not in the"
                    + " database.");
        }
   
       
    }
   
    /**
 * Returns the hashKey to be used to find index within HashTable
 * @param first
 * @param last
 * @return 
 * CE
 */
    private int getHashKey(String first, String last){
        String newName = first.toUpperCase() + last.toUpperCase();
        int hash = 0;
        int g = 31;
        int n = newName.length();
        
        for (int x = 0; x < n; x++)
        {
            hash = g * hash + newName.charAt(x);
        }     
        return hash;
    }
    
 /**
 * Finds the index of where new entry should be placed
 * @return 
 * CE
 */
    private int getHashIndex(String first, String last)
{
   int key = getHashKey(first, last);
   int hashIndex = key % myArray.length;
   if (hashIndex < 0)
       hashIndex = hashIndex + myArray.length;
   return hashIndex;
      
} // end getHashIndex 
    
    
    
    
  /* a private Node class to hold the data, CE */  
   private class Node
{

private Node next; // link to next node
private String firstName;
private String lastName;
private String emailAddress;
private String phoneNumber;
private int key;

     /** Call to constructor
     @param firstName Person's first name
     @param lastName Person's last name
     @param emailAddress Person's Email Address
     @param phoneNumber Person's phone number
     * CE
     */

private Node(String firstName, String lastName, String emailAddress, String phoneNumber)
{
  
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
    this.phoneNumber = phoneNumber;
    
} // end constructor



/**
 * Prints out data within node
 * CE
 */
public void printName(){
     
    System.out.println(this.firstName + " " + this.lastName + " " +
            this.emailAddress + " " + this.phoneNumber);
        }

   }// end Node
   
   
   /**
    * A class to link Nodes
    */
   public class LinkedBag
{
private Node firstNode; // reference to first node and latest entered
private int numberOfEntries;

/**
 * Beginning Constructor
 * CE
 */
public LinkedBag()
{
   firstNode = null;
   numberOfEntries = 0;
} // end default constructor
 
    public int getCurrentSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 /** Adds a new entry to this bag.
@param newNode the object to be inserted as a new entry
@return true */
public boolean add(Node newNode) 
{
// add to beginning of chain:

Node currentNode = newNode;
newNode.next = firstNode;
firstNode = newNode;// make new node reference rest of chain
// (firstNode is null if chain is empty)

numberOfEntries++; 
return true;

} // end add

private String remove()
{
String result = null;
if (firstNode != null)
{
   result = firstNode.firstName + " " + firstNode.lastName;
   firstNode = firstNode.next; // remove first node from chain
   numberOfEntries--;
} // end if
return result;
} // end remove

public boolean remove(String firstName, String lastName)
{
boolean result = false;
Node nodeN = getReferenceTo(firstName, lastName);
if (nodeN != null)
{
   nodeN.firstName = firstNode.firstName; // replace located entry with entry
   nodeN.lastName = firstNode.lastName; // replace located entry with entry
   nodeN.emailAddress = firstNode.emailAddress; // replace located entry with entry
   nodeN.phoneNumber = firstNode.phoneNumber; // replace located entry with entry
   // in first node
   remove(); // remove first node
   result = true;
} // end if
return result;
} // end remove 

/**
 * Removes each Node in the list
 * CE
 */
public void clear()
{
while (!isEmpty())
   remove();
} // end clear

/** Counts the number of times a given entry appears in this bag.
@param firstName the entry's first name searched and counted
@param lastName  the last name to be searched and counted
@return the number of times anEntry appears in the bag */
public int getFrequencyOf(String firstName, String lastName)
{
int frequency = 0;
int counter = 0;
Node currentNode = firstNode;
while ((counter < numberOfEntries) && (currentNode != null))
{
 if (firstName.equals(currentNode.firstName) && lastName.equals(currentNode.lastName))
 frequency++;
 counter++;
 currentNode = currentNode.next;
} // end while
return frequency;
} // end getFrequencyOf

/**
 * Search if list contains an entry
 * @param firstName 
 * @param lastName
 * @return 
 */
public boolean contains(String firstName, String lastName)
{
boolean found = false;
Node currentNode = firstNode;
while (!found && (currentNode != null))
{
 if (firstName.equals(currentNode.firstName) && lastName.equals(currentNode.lastName))
    found = true;
 else
    currentNode = currentNode.next;
} // end while
return found;
} // end contains


/**
 * Locates a given entry within this bag.
 * Returns a reference to the node containing the entry, if located,
 * or null otherwise.
 * @param firstName
 * @param lastName
 * @return 
 */
private Node getReferenceTo(String firstName, String lastName)
{
boolean found = false;
Node currentNode = firstNode;
while (!found && (currentNode != null))
{
   if (firstName.equals(currentNode.firstName) && lastName.equals(currentNode.lastName))
     found = true;
   else
     currentNode = currentNode.next;
} // end while
return currentNode;
} // end getReferenceTo

/** Retrieves all entries that are in this bag.
@return a newly allocated array of all the entries in the bag */
public void displayAllEntries()
{

int index = 0;
Node currentNode = firstNode;

while ((index < numberOfEntries) && (currentNode != null))
{
   System.out.println(currentNode.firstName + " " + currentNode.lastName + 
           " " + currentNode.phoneNumber + " " + currentNode.emailAddress);
   index++;
   currentNode = currentNode.next;
} // end while
} // end toArray

   }// end LinkedBag

   
   public static void main(String[] args){
       HashTable myTable = new HashTable();//create new instance       
        
        myTable.insertEntry("Bob", "Smith", "555-235-1111", "bsmith@somewhere.com");
        myTable.insertEntry("Jane", "Williams", "555-235-1112", "jw@something.com");
        myTable.insertEntry("Mohammed", "al-Salam", "555-235-1113", "mas@someplace.com");
        myTable.insertEntry("Pat", "Jones", "555-235-1114", "pjones@homesweethome.com");
        myTable.insertEntry("Billy", "Kidd", "555-235-1115", "billy_the_kid@nowhere.com");
        myTable.insertEntry("H.", "Houdini", "555-235-1116", "houdini@noplace.com");
        myTable.insertEntry("Jack", "Jones", "555-235-1117", "jjones@hill.com");
        myTable.insertEntry("Jill", "Jones", "555-235-1118", "jillj@hill.com");
        myTable.insertEntry("John", "Doe", "555-235-1119", "jdoe@somedomain.com");
        myTable.insertEntry("Jane", "Doe", "555-235-1120", "jdoe@somedomain.com");
        myTable.lookUpEntry("Pat", "Jones");
        myTable.lookUpEntry("Billy", "Kidd");
        myTable.deleteEntry("John", "Doe");
        myTable.insertEntry("Test", "Case", "555-235-1121", "Test_Case@testcase.com");
        myTable.insertEntry("Nadezhda", "Kanachekhovskaya", 
                "555-235-1122", "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru");
        myTable.insertEntry("Jo", "Wu", "555-235-1123", "wu@h.com");
        myTable.insertEntry("Millard", "Fillmore", "555-235-1124", 
                "millard@theactualwhitehouse.us");
        myTable.insertEntry("Bob", "vanDyke", 
                "555-235-1125", "vandyke@nodomain.com");
        myTable.insertEntry("Upside", "Down", "555-235-1126",
                "upsidedown@rightsideup.com");
        myTable.lookUpEntry("Jack", "Jones");
        myTable.lookUpEntry("Nadezhda", "Kanachekhovskaya");
        myTable.deleteEntry("Jill", "Jones");
        myTable.deleteEntry("John", "Doe");
        myTable.lookUpEntry("Jill", "Jones"); 
        myTable.lookUpEntry("John", "Doe");   
        
        
        
   }

} 

    
    
       
