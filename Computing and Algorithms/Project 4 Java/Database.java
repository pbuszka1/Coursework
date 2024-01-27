/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 4 */
/* Database class: manipulates the definition objects, search, print, etc */
/**************************************************************/

import java.util.Scanner;
import java.io.*;

public class Database { //idk if this is right for the generic
    //for importing Definition class
    Definition myDef = new Definition(0, null); //setting up basic definition object
    String termEntered; //for case 2
    String defWeLookingFor; //for case 1, def input
    String newTermInput; //for case 4, term input
    int newNumInput; //for case 4, number input
    String newDefinitionInput; //for case 4, definition input
    String termToRemove; //for case 5, term input
    int numToDelete; // for case 5, number input
    String yesNoDelete; //for case 5, the yes or no to delete
    Definition newDefinition; // object with number an ddefinition in it
    int indexForList = 0; //this is for the list position,
    //set to zero for first place, cuz we want the index to be the index, its not connected to the number
    String textFileName; //for case 6, file input
    String newDbFileName; //for case 7, file input
    Tree TermsList = new Tree(); //declare binary search tree

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
                        String term = file.next(); //reading file
                        int number = file.nextInt(); //reading file
                        String theDefinition = file.next(); //reading file
                        Term temp = new Term(term); //Term object
                        newDefinition = new Definition(number, theDefinition); //putting number and theDefinition into object


                        //you want to add the index creating part here (for IndexForList)

                        if(!TermsList.search(temp)) { //checks if its in the list, if not it adds it
                                TermsList.add(temp);
                        } 
        
                        if (TermsList.find(temp).theDefs.size() == 0) {
                                TermsList.find(temp).addDefinition(number, newDefinition);
                        } else {
                                TermsList.find(temp).addingDefs(newDefinition);              
                        }
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
        //prefix search is really going to be like print, look at every node in tree, see if it matchs
                TermsList.isThere = 0;
                TermsList.searching(termEntered); 

                if(TermsList.isThere != 1)
                        System.out.println("Sorry that prefix doesnt match any terms in the list");
    }

    /**************************************************************/
    /* Method: Define() */
    /* Purpose: take an input of a term, and return a definition if it can*/
    /* Parameters: defOfTerm */
    /* String target:  TermsList */
    /* Returns: nothing */
    /**************************************************************/
    public void Define() { 
        //int notThere = 0; //for the if statement
                try {
                        String defOfTerm = TermsList.get(defWeLookingFor).getTerm(); //idk if this is working right
                        if(defOfTerm.equals(defWeLookingFor)) {
                                TermsList.get(defWeLookingFor).print();  
                                //notThere = 1;    
                        }   
                } catch (NullPointerException exc) {
                        System.out.println("Sorry term is not in the list");
                }               
     }       

     /**************************************************************/
     /* Method: newEntery() */
     /* Purpose: add a new term, number and definition */
     /* or add a number and definition to an exsisiting term */
     /* Parameters: newTermInput, newNumInput, newDefinitionInput */
     /* String target:  TermsList */
     /* Returns: nothing */
     /**************************************************************/
     public void newEntery() { //this method needs to not allow you to add dublicate numbers and definitions
        Term temp = new Term(newTermInput);
        newDefinition = new Definition(newNumInput, newDefinitionInput);
        int thing = 0;

        if (!TermsList.search(temp)) { //make sure these add in order
                TermsList.add(temp);
                thing = 1;
        } else {
                if (TermsList.get(newTermInput).searchForDef(newNumInput) == false) //im thinking that you try to check and see if the number and definition are already there, prob need a new method
                        thing = 1;
                else
                        System.out.println("Sorry, the number and/or definition is already in the list");             
        }

        if (thing == 1) {
                if (TermsList.find(temp).theDefs.size() == 0) {
                        TermsList.find(temp).addDefinition(newNumInput, newDefinition);
                } else {
                        TermsList.find(temp).addingDefs(newDefinition);              
                }
        }
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
        Term temp = new Term(termToRemove);
        newDefinition = new Definition(numToDelete, null);

        try {
                if (TermsList.get(termToRemove).searchForDef(numToDelete) == true) {
                        System.out.println("Would you like to delete this definition? (yes or no)");
                        Scanner yesOrNoDelete = new Scanner(System.in);
                        yesNoDelete = yesOrNoDelete.nextLine();
                        DeleteDef();
                } else {
                        System.out.println("Sorry number and/or term doesnt exist inside the list");
                }
        } catch (NullPointerException exe) {
                System.out.println("Sorry no term match ");
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
                Term temp = new Term(termToRemove);
                int tempThing = 0;
                
                int posToDelete = -1000;
                int pos = 0;
                for (pos = 0; pos < TermsList.size(); pos++) {
                        String gotTerm = TermsList.get(termToRemove).getTerm();
                        
                        if ((gotTerm.equals(termToRemove)) && (TermsList.get(termToRemove).getNum(numToDelete) == numToDelete)) {
                                posToDelete = TermsList.get(termToRemove).getNum(numToDelete);
                                tempThing = 1;
                                break;
                        }
                }

                if(tempThing == 1) {
                        TermsList.get(termToRemove).removeDef(posToDelete);
                        System.out.println("The definition has been deleted");
                }

                if(TermsList.get(termToRemove).theDefListSize() == 0) {
                        TermsList.remove(temp);
                        System.out.println("Because that was the last definition, the term has also been deleted");
                }

        } else {
                System.out.println("Ok it won't be deleted then");
        }
    }

        /**************************************************************/
        /* Method: WriteFile() */
        /* Purpose:  */
        /* Parameters: textFileName */
        /* String target:  */
        /* Returns: nothing */
        /**************************************************************/
        public void writeTime() { 
                try (FileWriter thing = new FileWriter(textFileName)) {
                        writeTime(TermsList.root, thing);
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } 
        }

        public void writeTime(TreeNode current, FileWriter thing) {
            if (current == null) return;
            writeTime(current.getLeft(), thing);
            current.getDatum().writeToFile(thing);
            writeTime(current.getRight(), thing);
        }

        /**************************************************************/
        /* Method: startOver() */
        /* Purpose:  delete database and create a new one */
        /* Parameters: newDbFileName */
        /* String target:  newDbFileName */
        /* Returns: nothing */
        /**************************************************************/
        public void startOver() {
                Scanner filething = null;
                try {
                        filething = new Scanner(new File(newDbFileName));
                        filething.useDelimiter("[/\n]");

                } catch (FileNotFoundException exc) {
                        
                }
                if(filething == null) {
                        return;
                }
                        

                while(filething.hasNext()) {
                        //these below are for getting the file
                        String term = filething.next(); 
                        int number = filething.nextInt();
                        String theDefinition = filething.next();
                        Term temp = new Term(term);
                        newDefinition = new Definition(number, theDefinition); //putting number and theDefinition into object

                        if(!TermsList.search(temp)) { //checks if its in the list, if not it adds it
                                TermsList.add(temp);
                        } 
        
                        if (TermsList.find(temp).theDefs.size() == 0) {
                                TermsList.find(temp).addDefinition(number, newDefinition);
                        } else {
                                TermsList.find(temp).addingDefs(newDefinition);              
                        }
                } 
        }

        /**************************************************************/
        /* Method: isThere() */
        /* Purpose:  checks if  the file is there */
        /* Parameters: newDbFileName */
        /* String target:  newDbFileName */
        /* Returns: true if there or false if is */
        /**************************************************************/
        public boolean isThere() {
                Scanner filething = null;
                try {
                        filething = new Scanner(new File(newDbFileName));

                        if(filething != null) {
                                return true;
                        }

                } catch (FileNotFoundException exc) {
                        System.out.println("file isnt there");
                        return false;
                }
                return false;
        }


}
