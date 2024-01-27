/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* Tree class: creates the tree */
/**************************************************************/
import java.util.NoSuchElementException;
import javax.swing.JTextArea;

public class Tree {
    TreeNode root; //base of tree

    /**************************************************************/
    /* Method: Tree() */
    /* Purpose: creates tree */
    /* Parameters: nothing */
    /* String target:  nothing */
    /* Returns: nothing */
    /**************************************************************/
    public Tree() { root = null; }

    /**************************************************************/
    /* Method: search() */
    /* Purpose: take in a term, and returns true if the */
    /* Parameters: target */
    /* String target:  TermsList */
    /* Returns: boolean true or false */
    /**************************************************************/
    public boolean search(Term target) {
        return search(target, root);
    }

    private boolean search(Term target, TreeNode current) {
        if (current == null) return false;
        if (current.getDatum().getTerm().equals(target.getTerm()))
            return true;
        
        if (current.getDatum().getTerm().compareTo(target.getTerm()) < 0) //to iteriate through the recursive method, to find target
            return search(target, current.getRight());
        else
            return search(target, current.getLeft());
    }

    /**************************************************************/
    /* Method: add() */
    /* Purpose: add target */
    /* Parameters: target */
    /* String target:  TermsList */
    /* Returns: nothing */
    /**************************************************************/
    public void add(Term target) {
        TreeNode current = root;
        TreeNode previous = null;

        while (current != null) { //gets the the position where it should be added
            previous = current;
            if (current.getDatum().getTerm().compareTo(target.getTerm()) < 0)
                current = current.getRight();
            else 
                current = current.getLeft();
        }

        TreeNode leaf = new TreeNode();
        leaf.setDatum(target);
        leaf.setLeft(null);
        leaf.setRight(null);

        if (previous == null)
            root = leaf;
        else if (previous.getDatum().getTerm().compareTo(target.getTerm()) <0) //this is when the leafs are added
            previous.setRight(leaf);
        else
            previous.setLeft(leaf);
    }


    /**************************************************************/
    /* Method: size() */
    /* Purpose: return the size of the tree */
    /* Parameters: Tree */
    /* String target:  TermsList */
    /* Returns: size of tree */
    /**************************************************************/
    public int size() {
        return(size(root));
    }

    private int size(TreeNode Tree) {
        if (Tree == null) //base case
            return (0);
        else 
            return(size(Tree.getLeft()) + 1 + size(Tree.getRight())); //to iteriate through the recursive method, to find the size
    }

    /**************************************************************/
    /* Method: get() */
    /* Purpose: get datum of a term */
    /* Parameters: term */
    /* String target:  target */
    /* Returns: the current.getDatum */
    /**************************************************************/    
    public Term get(String target){
        TreeNode current = root; //sets current equal to root

        while (current != null) {
            if (current.getDatum().getTerm().equals(target)) //if you get the target initially, return it
                 return current.getDatum();

            if (current.getDatum().getTerm().compareTo(target) < 0) //steps through tree to find target
                current = current.getRight();
            else
                current = current.getLeft();
        }
        return null;
    }

    /**************************************************************/
    /* Method: find() */
    /* Purpose: find a term */
    /* Parameters: target */
    /* String target:  target */
    /* Returns: the current.getDatum */
    /**************************************************************/
    public Term find(Term target){
        TreeNode current = root; //sets current equal to root

        while (current != null) {
            if (current.getDatum().getTerm().equals(target.getTerm())) //if you get the target initially, return it
                return current.getDatum();

            if (current.getDatum().getTerm().compareTo(target.getTerm()) < 0) { //steps through tree to find target
                current = current.getRight();
            } else {
                current = current.getLeft();
            } 
        }
        return null; //if target isnt there, it returns null
    }

    /**************************************************************/
    /* Method: printTree() */
    /* Purpose: print out the tree */
    /* Parameters: target */
    /* String target:  current */
    /* Returns: nothing */
    /**************************************************************/
    public void printTree(JTextArea output) { printTree(root, output); } //this may not work, may need to change it, ask Prof

    public void printTree(TreeNode current, JTextArea output) {
        if (current == null) return;
        printTree(current.getLeft(), output);
        current.getDatum().print(output); //prints out the node holding the term, numbers, and definitions
        printTree(current.getRight(), output);
    }

    /**************************************************************/
    /* Method: searching() */
    /* Purpose: find termEntered */
    /* Parameters: termEntered */
    /* String target:  current */
    /* Returns: termEntered */
    /**************************************************************/
    int isThere = 0; //this is to check and see if the term is already there
    public void searching(String termEntered, JTextArea output) { searching(root, termEntered, output); }

    private void searching(TreeNode current, String termEntered, JTextArea output) {
        if (current == null) return;
        searching(current.getLeft(), termEntered, output); //it looks like its not actually reading the tree
        if (current.getDatum().getTerm().startsWith(termEntered)) {
            current.getDatum().printTerm(output); //if statement here
            isThere = 1;
        }
        searching(current.getRight(), termEntered, output); //to iteriate through the recursive method
    }

    /**************************************************************/
    /* Method: remove() */
    /* Purpose: remove target */
    /* Parameters: target */
    /* String target:  target, current */
    /* Returns: nothing */
    /**************************************************************/
    public void remove(Term target) {
        root = remove(target, root);
    }

    private TreeNode remove(Term target, TreeNode current) {
        if(current == null) //base case
            throw new NoSuchElementException();
        
            if (current.getDatum().getTerm().compareTo(target.getTerm()) < 0) { //helps step through to find target
                current.setRight(remove (target, current.getRight()));
                return current;
            } else if (current.getDatum().getTerm().compareTo(target.getTerm()) > 0) {
                current.setLeft(remove(target, current.getLeft()));
            }

            if (current.getLeft() == null) //if it gets to the end, returns other child
                return current.getRight();
            if (current.getRight() == null) //if it gets to the end, returns other child
                return current.getLeft();

            TreeNode heir = current.getLeft();
            while (heir.getRight() != null) //for the heir
                heir = heir.getRight();
            current.setDatum(heir.getDatum());

            current.setLeft(remove(heir.getDatum(), current.getLeft())); //to iteriate through the recursive method 
            
            return current;
    }
}
