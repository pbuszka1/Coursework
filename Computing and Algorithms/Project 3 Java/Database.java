/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 3 */
/* Database class: manipulates the definition objects, search, print, etc */
/**************************************************************/

import java.util.Scanner;
import java.io.*;
import java.util.LinkedList;

public class Database {
    //for importing Definition class
    Definition myDef = new Definition(0, null);
    String termEntered; //
    String defWeLookingFor; //for case 1, def input
    String newTermInput; //for case 4, term input
    int newNumInput; //for case 4, number input
    String newDefinitionInput; //for case 4, definition input
    String termToRemove; //for case 5, term input
    int numToDelete; // for case 5, number input
    String yesNoDelete; //for case 5, the yes or no to delete
    Definition newDefinition; // object with number an ddefinition in it

    LinkedList<Term> TermsList = new LinkedList<Term>(); //terms linked list

    /**************************************************************/
    /* Method: initializeLinkedList */
    /* Purpose: creates the basic linked list of terms with each term */
    /* having a linked list of definitions */
    /* Parameters: args*/
    /* String target: the inputted file */
    /* Returns: the created linked list */
    /**************************************************************/
    public void initializeLinkedList(String[] args) { //instead of (String[] args)
                //this below is to get the file
                Scanner file = null; //initializes file
                try { //through some testing I think the file reading is wrong, so we will have to fix it
                        file = new Scanner(new File(args[0]));
                        file.useDelimiter("[/\n]"); // "[/\n]"
                }
                catch (ArrayIndexOutOfBoundsException exc) {
                        System.out.println("File could not be opened.");
                        System.exit(1);
                }
                catch (FileNotFoundException exc) {
                        System.out.println("File could not be opened.");
                }

                //this is to create the objects that are being used
                while(file.hasNext()) {
                        //these below are for getting the file
                        String term = file.next(); 
                        int number = file.nextInt();
                        String theDefinition = file.next();
                        Term temp = new Term(term);
                        newDefinition = new Definition(number, theDefinition); //putting number and theDefinition into object
                        
                        if(TermsList.size() == 0) {
                                temp.addDefinition(newDefinition); //creates the first term object
                                TermsList.add(temp);
                        } else {
                                int index = -1; //index for keeping track
                                for(int pos = 0; pos < TermsList.size(); pos++) {
                                        if (TermsList.get(pos).term.equals(term)) { //use this to help sort the definitions in order
                                                index = pos;
                                        }
                                }
                                if(index != -1) {
                                        TermsList.get(index).addingDefs(newDefinition);  //adds def in a certain index of termslist
                                } else {
                                        int pos;
                                        for(pos = 0; pos < TermsList.size(); pos++){
                                                Term current = TermsList.get(pos);
                                                String currentTerm = current.getTerm();

                                                if(term.compareTo(currentTerm) < 0){
                                                        temp.addDefinition(newDefinition);
                                                        TermsList.add(pos, temp);
                                                        break;
                                                }
                                                //this runs the last time the for loop runs
                                                if(pos == TermsList.size() - 1) {
                                                        temp.addDefinition(newDefinition);
                                                        TermsList.add(pos+1, temp);
                                                        break;
                                                }
                                        }
                                }
                        }   
                } 
        }

    /**************************************************************/
    /* Method: print */
    /* Purpose: to print out the list of objects, with the definition lists in the term objects*/
    /* Parameters: TermsList */
    /* String target:  TermsList*/
    /* Returns: nothing */
    /**************************************************************/
     public void Print() {
             for (int pos = 0; pos < TermsList.size(); pos++) {
                     TermsList.get(pos).print();
             }
     }
    
    /**************************************************************/
    /* Method: Search */
    /* Purpose: to search the list of objects and show matching terms to the prefix */
    /* Parameters: termEntered */
    /* String target:  TermsList */
    /* Returns: nothing */
    /**************************************************************/
    public void Search() {
        int notThere = 0; //for the if statement
        for (int pos = 0; pos < TermsList.size(); pos++) {
                String gotTerm = TermsList.get(pos).term;
                if(gotTerm.startsWith(termEntered)) {
                        System.out.println(gotTerm);
                        notThere = 1;
                } 
        }
        if(notThere != 1)  
                System.out.println("Sorry term is not in the list");
    }

    /**************************************************************/
    /* Method: Define() */
    /* Purpose: take an input of a term, and return a definition if it can*/
    /* Parameters: defOfTerm */
    /* String target:  TermsList */
    /* Returns: nothing */
    /**************************************************************/
    public void Define() { 
        int notThere = 0; //for the if statement
        for (int pos = 0; pos < TermsList.size(); pos++) {
                String defOfTerm = TermsList.get(pos).term;
                if(defOfTerm.equals(defWeLookingFor)) {
                        TermsList.get(pos).print();  
                        notThere = 1;    
                } 
        }      
        if(notThere != 1)  
                System.out.println("Sorry term is not in the list");
     }       

     /**************************************************************/
     /* Method: newEntery() */
     /* Purpose: add a new term, number and definition */
     /* or add a number and definition to an exsisiting term */
     /* Parameters: newTermInput, newNumInput, newDefinitionInput */
     /* String target:  TermsList */
     /* Returns: nothing */
     /**************************************************************/
     public void newEntery() {
        int spotAdded = -5; //the spot where it was added, its set as -5 as a way to check correctness
        Term temp = new Term(newTermInput);
        newDefinition = new Definition(newNumInput, newDefinitionInput);
        
        if(TermsList.size() == 0) {
                temp.addDefinition(newDefinition); //can create the first term object
                TermsList.add(temp);
        } else {
                int index = -1; //index for keeping track
                for(int pos = 0; pos < TermsList.size(); pos++) {
                        if (TermsList.get(pos).term.equals(newTermInput)) { //use this to help sort the definitions in order
                                index = pos;
                        }
                }
                if(index != -1) {
                                TermsList.get(index).addingDefs(newDefinition); 
                                spotAdded = index; //adds def in a certain index of termslist
                } else {
                        int pos; //for the position
                         //just to see if it would overwrite something
                        for(pos = 0; pos < TermsList.size(); pos++){
                                Term current = TermsList.get(pos);
                                String currentTerm = current.getTerm();

                                if(newTermInput.compareTo(currentTerm) < 0){
                                        temp.addDefinition(newDefinition);
                                        TermsList.add(pos, temp);
                                        spotAdded = pos;
                                        break;
                                }
                                //this runs the last time the for loop runs
                                if(pos == TermsList.size() - 1) {
                                        temp.addDefinition(newDefinition);
                                        TermsList.add(pos+1, temp);
                                        spotAdded = pos+1;
                                        break;
                                }
                        }
                }
        }
        TermsList.get(spotAdded).print();
    }

    /**************************************************************/
    /* Method: ChadSearch() */
    /* Purpose: allows the user to delete a definition from the menu if it exists */
    /* It first checks and sees if the term and number are in the lists */
    /* Parameters: numToDelete, termToRemove */
    /* String target: number of defList */
    /* Returns: nothing */
    /**************************************************************/
    public void ChadSearch() {
        int placeHolder = -1000; //a placeholder
        for (int pos = 0; pos < TermsList.size(); pos++) {
                String gotTerm = TermsList.get(pos).term; //gets term
                
                //checks if the inputed term matchs one in the termslist, and it cycles through the deflist to see if theres a number match
                if(gotTerm.equals(termToRemove) && (numToDelete == TermsList.get(pos).getNum(numToDelete))) {
                        placeHolder = numToDelete;
                        TermsList.get(pos).printOneDef(placeHolder);
                        break;
                } 
        }
        if(numToDelete != placeHolder) {
                System.out.println("Sorry number and/or term doesnt exist inside the list");
        } else {
                System.out.println("Would you like to delete this definition? (yes or no)");
                Scanner yesOrNoDelete = new Scanner(System.in);
                yesNoDelete = yesOrNoDelete.nextLine();
                DeleteDef();
        }
    }

    /**************************************************************/
    /* Method: DeleteDef() */
    /* Purpose: gives the option to delete the definition of a term */
    /* Parameters: yesNoDelete */
    /* String target: number of defList, then gets the definition at the number */
    /* Returns: nothing */
    /**************************************************************/
    public void DeleteDef() {
        if(yesNoDelete.toLowerCase().equals("yes")) {
                int placeHolder = -1000; //a place holder for the number to delete
                int posToDelete = -1000; //the position to delete
                for (int pos = 0; pos < TermsList.size(); pos++) {
                        String gotTerm = TermsList.get(pos).term;

                        //checks if the inputed term matchs one in the termslist, and it cycles through the deflist to see if theres a number match
                        if(gotTerm.equals(termToRemove) && (numToDelete == TermsList.get(pos).getNum(numToDelete))) {
                                posToDelete = pos;
                                placeHolder = numToDelete;
                                break;
                        } 
                }
                TermsList.get(posToDelete).removeDef(placeHolder);
                System.out.println("The definition has been deleted");

                if(TermsList.get(posToDelete).theDefListSize() == 0) {
                        TermsList.remove(posToDelete);
                        System.out.println("Because that was the last definition, the term has also been deleted");
                }
        } else {
                System.out.println("Ok it won't be deleted then");
        }
    }
}
