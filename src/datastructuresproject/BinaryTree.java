/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresproject;



/**
 *An implementation of a Binary Tree for an online Phone book
 * @author carlosespejo
 * Initials CE
 */
public class BinaryTree {
    
    Node root;
    
/**
 * Inserts new entry within binary tree
 * @param key
 * @param firstName
 * @param lastName
 * @param emailAddress
 * @param phoneNumber 
 * CE
 */   
public void insertEntry(String firstName, String lastName, 
            String phoneNumber, String emailAddress )
{
    //Create new Node
    Node newNode = (new Node(firstName,lastName
            ,phoneNumber, emailAddress ));
    

if (isEmpty()) //Check if rootNode is empty 
    setRootNode(newNode);

else //if root is not empty, call insertNode() 
    insertNode(getRootNode(), newNode);

System.out.println(newNode.firstName + " " + newNode.lastName
+ " has been added to the database");
} // end add

    
    /**
     * Private method that inserts an new entry in the tree
     * CE
     * @param rootNode The node to start traversal with
     * @param newNode the new Node to be inserted
     * CE
     */
    private void insertNode(Node rootNode, Node newNode)          
    {
        
        assert root != null;
    

if (rootNode.key == newNode.key)
{
    
    rootNode.setData(newNode.getData());
}
else if (rootNode.key > newNode.key)
{
    if (rootNode.hasLeftChild())
      insertNode(rootNode.getLeftChild(), newNode);
    else
       rootNode.setLeftChild(newNode);
}
else
{
    assert rootNode.key < newNode.key;
    if (rootNode.hasRightChild())
       insertNode(rootNode.getRightChild(), newNode);
    else
       rootNode.setRightChild(newNode);
} // end if
              
    }
  
 /**
  * Deletes an entry that matches parameters
  * @param firstName First name of student
  * @param lastName  Last name of student
  * CE
  */
 public void deleteEntry(String firstName, String lastName)
{
int key = getKey(firstName, lastName);
Node oldEntry = null;
Node newRoot = removeEntry(getRootNode(), key ,oldEntry);
setRootNode(newRoot);
//return oldEntry.getData();

System.out.println(firstName + " " + lastName
+ " has been deleted from the database");
} // end remove   

 /**
  * private method that removes the entry 
  * @param rootNode Node to start traversal with
  * @param entryKey key for new entry
  * @param oldEntry new entry
  * @return Node to be removed
  * CE
  */
private Node removeEntry(Node rootNode, int entryKey, Node oldEntry)
{
    
if (rootNode != null)
{
   String[] rootData = rootNode.getData();
   
   if (rootNode.key == entryKey) // entry == root entry
   {
      //oldEntry.setData(rootData);
      rootNode = removeFromRoot(rootNode);
   }
   else if (rootNode.key > entryKey) // entry < root entry
   {
      Node leftChild = rootNode.getLeftChild();
      Node subtreeRoot = removeEntry(leftChild, entryKey, oldEntry);
      rootNode.setLeftChild(subtreeRoot);
   }
   else // entry > root entry
   {
      Node rightChild = rootNode.getRightChild();
      rootNode.setRightChild(removeEntry(rightChild, entryKey, oldEntry));
   } // end if
} // end if
return rootNode;
} // end removeEntry


/**
 * Removes the entry in a given root node of a subtree
 * @param rootNode rootNode is the root node of the subtree.
 * @return Returns the root node of the revised subtree.
 * CE
 */
private Node removeFromRoot(Node rootNode)
{
// Case 1: rootNode has two children
if (rootNode.hasLeftChild() && rootNode.hasRightChild())
{
    // find node with largest entry in left subtree
    Node leftSubtreeRoot = rootNode.getLeftChild();
    Node largestNode = findLargest(leftSubtreeRoot);
    // replace entry in root
    rootNode.setData(largestNode.getData());
    rootNode.setKey();
    // remove node with largest entry in left subtree
    rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
} // end if

// Case 2: rootNode has at most one child
else if (rootNode.hasRightChild())
    rootNode = rootNode.getRightChild();
else
    rootNode = rootNode.getLeftChild();

return rootNode;
} // end removeEntry

// Finds the node containing the largest entry in a given tree.
// rootNode is the root node of the tree.
// Returns the node containing the largest entry in the tree. CE

private Node findLargest(Node rootNode)
{
if (rootNode.hasRightChild())
rootNode = findLargest(rootNode.getRightChild());
return rootNode;
} // end findLargest

// Removes the node containing the largest entry in a given tree.
// rootNode is the root node of the tree.
// Returns the root node of the revised tree. CE
private Node removeLargest(Node rootNode)
{
if (rootNode.hasRightChild())
{
    Node rightChild = rootNode.getRightChild();
    Node root = removeLargest(rightChild);
    rootNode.setRightChild(root);
}
else
    rootNode = rootNode.getLeftChild();
return rootNode;
} // end removeLargest
    

 /**
  * Searches tree for a given match to parameters
  * @param firstName Student first name
  * @param lastName Student last name
  */   
public void lookUpEntry(String firstName, String lastName)
{
    
    Node dataNode = bstSearch(root, getKey(firstName, lastName));
    if (dataNode != null) {
    System.out.println(dataNode.emailAddress + " " + dataNode.phoneNumber);
    }
    else {
        System.out.println( firstName + " " + lastName + 
                " is not in our System.");
    }
}
    
public boolean contains(String firstName, String lastName){
    return bstSearch(root, getKey(firstName, lastName)) != null;
    
}
    
private Node bstSearch(Node searchTree, int key){
    
    if(searchTree == null)
    {
        return null;
    }
    else if(searchTree.key == key)
    {
        
        return searchTree;
    }
    else if(searchTree.key > key)
    {
        return bstSearch(searchTree.left, key);
    }
    else
    {
        return bstSearch(searchTree.right, key);
    }
        
   
}

private int getKey(String firstName, String lastName){
    String newName = firstName.toUpperCase() + lastName.toUpperCase();
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
  * Returns data from Root
  * @return Array of Strings of Student info
  */  
public String[] getRootData()
{
String[] rootData = null;
if (root != null)
   rootData = root.getData();
return rootData;

} // end getRootData

/**
 * Returns empty if root is null.
 * @return true or false depending on state of node
 * CE
 */
public boolean isEmpty()
{
return root == null;
} // end isEmpty

/**
 * Removes root node
 */
public void clear()
{
root = null;
} // end clear


/**
 * Set data for root node
 * @param firstName
 * @param lastName
 * @param emailAddress
 * @param phoneNumber
 * @param key 
 * CE
 */
private void setRootData(String firstName, String lastName, String emailAddress, 
        String phoneNumber, int key)
{
    String[] data = {firstName, lastName, 
        emailAddress, phoneNumber};
    
root.setData(data);
} // end setRootData

private void setRootNode(Node rootNode)
{
root = rootNode;
} // end setRootNode

private Node getRootNode()
{
return root;
} // end getRootNode

public void inorderTraverse()
{
inorderTraverse(root);
} // end inorderTraverse

private void inorderTraverse(Node node)
{
if (node != null)
{
     inorderTraverse(node.getLeftChild());
     //System.out.println(Arrays.toString(node.getData()));
     inorderTraverse(node.getRightChild());
} // end if
} // end inorderTraverse


    

    /**
     * Binary Node class which equals a student
     */
  class Node {
private String firstName;
private String lastName;
private String emailAddress;
private String phoneNumber;
private Node left;
private Node right;
private int key;

/**
 * Constructor for new node
 * @param firstName
 * @param lastName
 * @param emailAddress
 * @param phoneNumber 
 */
private Node(String firstName, String lastName, 
        String phoneNumber, String emailAddress)
{
      
     this.firstName = firstName;
     this.lastName = lastName;
     this.emailAddress = emailAddress;
     this.phoneNumber = phoneNumber;  
     setKey();
    
} // end constructor

/**
 * Returns Email address and Phone number
 * @return 
 */
public String[] getData()
{
    String[] data = {this.firstName, this.lastName, 
        this.emailAddress, this.phoneNumber};
            
     return data;
} // end getData




public void setData(String[] data)
{
     this.firstName = data[0];
     this.lastName = data[1];
     this.emailAddress = data[2];
     this.phoneNumber = data[3]; 
} // end setData

private int getKey(){
    
    return key;
}

private void setKey(){
    key = this.getHashKey();
}

public int getHashKey(){
        String newName = firstName.toUpperCase() + lastName.toUpperCase();
        int hash = 0;
        int g = 31;
        int n = newName.length();
        
        for (int x = 0; x < n; x++)
        {
            hash = g * hash + newName.charAt(x);
        }     
       
        return hash;
    }

public Node getLeftChild()
{
     return left;
} // end getLeftChild

/**
 * set Node's left child to a Node
 * @param leftChild 
 */
public void setLeftChild(Node leftChild)
{
     left = leftChild;
} // end setLeftChild

/**
 * Return true if node has left child.
 * @return 
 */
public boolean hasLeftChild()
{
     return left != null;
} // end hasLeftChild

/**
 * Returns true if node has no children
 * @return 
 */
public boolean isLeaf()
{
     return (left == null) && (right == null);
} // end isLeaf

public Node getRightChild()
{
     return right;
} // end getLeftChild

public void setRightChild(Node rightChild)
{
     right = rightChild;
} // end setLeftChild

/**
 * Return true if node has right child.
 * @return 
 */
public boolean hasRightChild()
{
     return right != null;
} // end hasLeftChild









}
    
    public static void main(String[] args){
        
        BinaryTree binaryAddress = new BinaryTree();
        binaryAddress.insertEntry("Bob", "Smith", "555-235-1111", "bsmith@somewhere.com");
        binaryAddress.insertEntry("Jane", "Williams", "555-235-1112", "jw@something.com");
        binaryAddress.insertEntry("Mohammed", "al-Salam", "555-235-1113", "mas@someplace.com");
        binaryAddress.insertEntry("Pat", "Jones", "555-235-1114", "pjones@homesweethome.com");
        binaryAddress.insertEntry("Billy", "Kidd", "555-235-1115", "billy_the_kid@nowhere.com");
        binaryAddress.insertEntry("H.", "Houdini", "555-235-1116", "houdini@noplace.com");
        binaryAddress.insertEntry("Jack", "Jones", "555-235-1117", "jjones@hill.com");
        binaryAddress.insertEntry("Jill", "Jones", "555-235-1118", "jillj@hill.com");
        binaryAddress.insertEntry("John", "Doe", "555-235-1119", "jdoe@somedomain.com");
        binaryAddress.insertEntry("Jane", "Doe", "555-235-1120", "jdoe@somedomain.com");
        binaryAddress.lookUpEntry("Pat", "Jones");
        binaryAddress.lookUpEntry("Billy", "Kidd");
        binaryAddress.deleteEntry("John", "Doe");
        binaryAddress.insertEntry("Test", "Case", "555-235-1121", "Test_Case@testcase.com");
        binaryAddress.insertEntry("Nadezhda", "Kanachekhovskaya", "555-235-1122", "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru");				
        binaryAddress.insertEntry("Jo", "Wu", "555-235-1123", "wu@h.com");
        binaryAddress.insertEntry("Millard", "Fillmore", "555-235-1124", "millard@theactualwhitehouse.us");
        binaryAddress.insertEntry("Bob", "vanDyke", "555-235-1125", "vandyke@nodomain.com");
        binaryAddress.insertEntry("Upside", "Down", "555-235-1126", "upsidedown@rightsideup.com");
        binaryAddress.lookUpEntry("Jack", "Jones");
        binaryAddress.lookUpEntry("Nadezhda", "Kanachekhovskaya");
        binaryAddress.deleteEntry("Jill", "Jones");
        binaryAddress.deleteEntry("John", "Doe");
        binaryAddress.lookUpEntry("Jill", "Jones");
        binaryAddress.lookUpEntry("John", "Doe");

    }

}
