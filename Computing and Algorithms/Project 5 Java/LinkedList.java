/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* LinkedList class: manipulates the definition objects, search, print, etc */
/**************************************************************/

public class LinkedList {
    Node head; //creates head

    /**************************************************************/
    /* Method: LinkedList() */
    /* Purpose: create linkedlist */
    /* Parameters: none */
    /* String target:  none */
    /* Returns: nothing */
    /**************************************************************/
    public LinkedList() {
        head = null; //sets head equal to null
    }

    /**************************************************************/
    /* Method: size() */
    /* Purpose: gets size of list */
    /* Parameters: none */
    /* String target:  current */
    /* Returns: count */
    /**************************************************************/
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.getNext(); //steps through the list
        }
        return count; //returns the size of list
    }

    /**************************************************************/
    /* Method: add() */
    /* Purpose: adds definition to linkedList */
    /* Parameters: index, item */
    /* String target:  current */
    /* Returns: nothing */
    /**************************************************************/
    public void add(int index, Definition item) { //index is where it is in the linked list, can use the campairsons here, or create a seprate method where it makes the numeriucal compairson then says what index to add
        Node current = head;
        Node prev = null;

        while ((current != null) && (index != 0)) { //gets the the position where it should be added
            index--;
            prev = current;
            current = current.getNext();
        }

        Node splice = new Node(); //creates node
        splice.setDatum(item); //sets node
        splice.setNext(current); //moves to next node

        if (prev == null)
            head = splice;
        else
            prev.setNext(splice);
    }

    /**************************************************************/
    /* Method: remove() */
    /* Purpose: removes definition to linkedList */
    /* Parameters: index */
    /* String target:  current */
    /* Returns: current.getDatum */
    /**************************************************************/
    public Object remove (int index) {
        Node current = head;
        Node prev = null;

        while ((current != null) && (index != 0)) {
            index--;
            prev = current; //steps through list
            current = current.getNext();
        }

        if (current == null)
            throw new IndexOutOfBoundsException();

        if (prev == null) 
            head = current.getNext(); //sets head to current if there is nothing before it
        else
            prev.setNext(current.getNext());

        return current.getDatum();
    }

    /**************************************************************/
    /* Method: get() */
    /* Purpose: get defintion object */
    /* Parameters: index */
    /* String target:  current */
    /* Returns: current.getDatum */
    /**************************************************************/
    public Definition get(int index) {
        //return the definition object with the number in it and def in it
        Node current = head;
        Node prev = null;

        while ((current != null) && (index != 0)) { //finds the definition object
            index--;
            prev = current;
            current = current.getNext();
        }

        return current.getDatum(); //returns the definition object
    }
}
