/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* Node class: Makes up the definition list, holds the definition */
/**************************************************************/

public class Node {
    Definition datum; //definition object
    Node next; //node next


    /**************************************************************/
    /* Method: Node() */
    /* Purpose: create Node */
    /* Parameters: */
    /* String target:  */
    /* Returns: nothing */
    /**************************************************************/
    public Node() {
        datum = null;
        next = null;
    }

    /**************************************************************/
    /* Method: getDatum() */
    /* Purpose: return datum */
    /* Parameters: nothing */
    /* String target:  TermsList */
    /* Returns: datum */
    /**************************************************************/
    public Definition getDatum() {
        return datum;
    }

    /**************************************************************/
    /* Method: getNext() */
    /* Purpose: return datum */
    /* Parameters: nothing */
    /* String target:  TermsList */
    /* Returns: datum */
    /**************************************************************/
    public Node getNext() {
        return next;
    }

    /**************************************************************/
    /* Method: setDatum() */
    /* Purpose: sets datum */
    /* Parameters: datum */
    /* String target:  datum */
    /* Returns: nothing */
    /**************************************************************/
    public void setDatum (Definition datum) {
        this.datum = datum;
    }

    /**************************************************************/
    /* Method: setNext() */
    /* Purpose: next */
    /* Parameters: next */
    /* String target:  next */
    /* Returns: nothing */
    /**************************************************************/
    public void setNext (Node next) {
        this.next = next;
    }
}
