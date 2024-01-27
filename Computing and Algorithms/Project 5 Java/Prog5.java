/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* Prog5 class: interacts with the user */
/**************************************************************/

public class Prog5 {
    static Database myDb;

    /**************************************************************/
    /* Method: getDatabase() */
    /* Purpose: get the database */
    /* Parameters: */
    /* Returns: myDb */
    /**************************************************************/
    public Database getDatabase() {//this is trying to make a method to get it
        return myDb;
    }   

    /**************************************************************/
    /* Method: setDatabase() */
    /* Purpose: sets the database */
    /* Parameters: myDb */
    /* target: myDb */
    /* Returns: nothing */
    /**************************************************************/
    public void setDatabase(Database myDb) {
        this.myDb = myDb;
    }   
    
    /**************************************************************/
    /* Method: main() */
    /* Purpose: enables program to work */
    /* Parameters: String[] args */
    /* Returns: nothing */
    /**************************************************************/
    public static void main(String[] args) {
        GUI gui = new GUI();//for importing GUI class
        myDb = new Database(); //for importing Database class
        myDb.initializeLinkedList(args); //the args would be user input
    }
}
