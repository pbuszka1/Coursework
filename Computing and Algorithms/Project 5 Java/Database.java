/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* Database class: manipulates the definition objects, search, print, etc */
/**************************************************************/
import java.util.Scanner;
import javax.swing.JTextArea;
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
    /* Purpose: creates the basic TermsList, and the definition linked list */
    /* having a linked list of definitions */
    /* Parameters: args*/
    /* String target: the inputted file */
    /* Returns: the created TemrsList */
    /**************************************************************/
    public void initializeLinkedList(String[] args) { //instead of (String[] args)
                //this below is to get the file
                Scanner file = null; //initializes file
                try { 
                        file = new Scanner(new File(args[0]));
                        file.useDelimiter("[/\n]");
                }
                catch (ArrayIndexOutOfBoundsException exc) { //exception handling
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

                        //below is for adding to the TermsList
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
    /* Method: Search() */
    /* Purpose: to search the list of objects and show matching terms to the prefix */
    /* Parameters: termEntered, output */
    /* String target:  TermsList */
    /* Returns: nothing */
    /**************************************************************/
    public void Search(JTextArea output) {
        //prefix search is really going to be like print, look at every node in tree, see if it matchs
        TermsList.isThere = 0;
        TermsList.searching(termEntered, output); 

        if(TermsList.isThere != 1)
                output.setText(output.getText() + "Sorry that prefix doesnt match any terms in the list"); //outputs to JTextArea if no match
    }



    /**************************************************************/
    /* Method: Define() */
    /* Purpose: take an input of a term, and return a definition if it can*/
    /* Parameters: defOfTerm, output */
    /* String target:  TermsList */
    /* Returns: nothing */
    /**************************************************************/
    public void Define(JTextArea output) { 
        try {
                String defOfTerm = TermsList.get(defWeLookingFor).getTerm();
                if(defOfTerm.equals(defWeLookingFor)) {
                        TermsList.get(defWeLookingFor).print(output); //outputs term, and definition objects if there
                }   
        } catch (NullPointerException exc) {
                output.setText(output.getText() + "Sorry term is not in the list"); //wont output correctly
        }               
     }       

     /**************************************************************/
     /* Method: newEntery() */
     /* Purpose: add a new term, number and definition */
     /* or add a number and definition to an exsisiting term */
     /* Parameters: newTermInput, newNumInput, newDefinitionInput, output */
     /* String target:  TermsList */
     /* Returns: nothing */
     /**************************************************************/
     public void newEntery(JTextArea output) { //this method needs to not allow you to add dublicate numbers and definitions
        Term temp = new Term(newTermInput.toLowerCase()); //this makes sure they add in order
        newDefinition = new Definition(newNumInput, newDefinitionInput); //creates newDefinition object
        int thing = 0; //for the thing == 1 if statement

        if (!TermsList.search(temp)) { //these add in order
                TermsList.add(temp);
                thing = 1;
        } else {
                if (TermsList.get(newTermInput).searchForDef(newNumInput) == false) //check and see if the number and definition are already there
                        thing = 1;
                else  
                        output.setText(output.getText() + "Sorry, the number and/or definition is already in the list");           
        }

        if (thing == 1) {
                if (TermsList.find(temp).theDefs.size() == 0) {
                        TermsList.find(temp).addDefinition(newNumInput, newDefinition);
                } else {
                        TermsList.find(temp).addingDefs(newDefinition);              
                }
                output.setText(output.getText() + "It has been added");
        }
     }

    /**************************************************************/
    /* Method: ChadSearch() */
    /* Purpose: allows the user to delete a definition from the menu if it exists */
    /* It first checks and sees if the term and number are in the lists */
    /* Parameters: numToDelete, termToRemove, output */
    /* String target: number of defList */
    /* Returns: nothing */
    /**************************************************************/
     public void ChadSearch(JTextArea output) {
        Term temp = new Term(termToRemove); //creates a temp for term
        newDefinition = new Definition(numToDelete, null);

        try {
                if (TermsList.get(termToRemove).searchForDef(numToDelete) == true) { //finds if the term and then number exist
                        ChadSearchHelp help = new ChadSearchHelp(output);
                } else {
                        ChadSearchMoreHelp moreHelp = new ChadSearchMoreHelp(1); //if term and or number dont exist it opens this window
                }
        } catch (NullPointerException exe) {
                ChadSearchMoreHelp moreHelp = new ChadSearchMoreHelp(0); //if the term doesnt exist
        }
     }

    /**************************************************************/
    /* Method: DeleteDef() */
    /* Purpose: gives the option to delete the definition of a term */
    /* Parameters: yesNoDelete, output */
    /* String target: number of defList, then gets the definition at the number */
    /* Returns: nothing */
    /**************************************************************/
    public void DeleteDef(JTextArea output) {
        if(yesNoDelete.toLowerCase().equals("yes")) {
                Term temp = new Term(termToRemove); //maybe this temp term isnt actually being set to what we want
                int tempThing = 0; //for tempthing
                
                int posToDelete = -1000; //posToDelete set to crazy low number to make sure it works correctly
                int pos = 0;
                for (pos = 0; pos < TermsList.size(); pos++) {
                        String gotTerm = TermsList.get(termToRemove).getTerm(); //the term we want to delete
                        
                        if ((gotTerm.equals(termToRemove)) && (TermsList.get(termToRemove).getNum(numToDelete) == numToDelete)) { //finds the term, to find the definition number to delete
                                posToDelete = TermsList.get(termToRemove).getNum(numToDelete);
                                tempThing = 1;
                                break;
                        }
                }

                if(tempThing == 1) { //removes the number and definition
                        TermsList.get(termToRemove).removeDef(posToDelete);
                        output.setText(output.getText() + "The definition has been deleted");
                }

                if(TermsList.get(termToRemove).theDefListSize() == 0) { //for removing the final term
                        TermsList.remove(temp);
                        output.setText(output.getText() + "\n" + "Because that was the last definition, the term has also been deleted"); //seems to delete the first object, of the origional list?
                }

        } else {
                output.setText(output.getText() + "Ok it won't be deleted then");
                //if they say anything but yes for the term to delete
        }
    }

        /**************************************************************/
        /* Method: WriteFile() */
        /* Purpose:  */
        /* Parameters: textFileName, output */
        /* String target:  */
        /* Returns: nothing */
        /**************************************************************/
        public void writeTime(JTextArea output) { 
                try (FileWriter thing = new FileWriter(textFileName)) { //try catch for exception handling
                        writeTime(TermsList.root, thing, output);
                        output.setText(output.getText() + "File has been written "); //outputs to JTextArea
                } catch (IOException exc) {

                } 
        }

        public void writeTime(TreeNode current, FileWriter thing, JTextArea output) {
            if (current == null) return; //base case
            writeTime(current.getLeft(), thing, output); //goes through tree to find objects to write
            current.getDatum().writeToFile(thing, output); //writes to file
            writeTime(current.getRight(), thing, output); //goes through tree to find objects to write
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
                        filething = new Scanner(new File(newDbFileName)); //sets filething to the file we are reading
                        filething.useDelimiter("[/\n]");

                } catch (FileNotFoundException exc) {
                        
                }
                if(filething == null) { //if its null you return
                        return;
                }
                        
                try { //so it will not randomly throw a null pointer exception
                        while(filething.hasNext()) {
                                //these below are for getting the file
                                String term = filething.next(); //gets term from file
                                int number = filething.nextInt(); //gets number from file
                                String theDefinition = filething.next(); //gets the definition from file
                                Term temp = new Term(term);
                                newDefinition = new Definition(number, theDefinition); //putting number and theDefinition into object

                                if(!TermsList.search(temp)) { //checks if its in the list, if not it adds it
                                        TermsList.add(temp);
                                } 
                
                                if (TermsList.find(temp).theDefs.size() == 0) { //if term is in list, you add to it
                                        TermsList.find(temp).addDefinition(number, newDefinition);
                                } else {
                                        TermsList.find(temp).addingDefs(newDefinition);              
                                }
                        } 
                } catch (NullPointerException exc) {
                        
                }
        }

        /**************************************************************/
        /* Method: isThere() */
        /* Purpose:  checks if  the file is there */
        /* Parameters: newDbFileName, output */
        /* String target:  newDbFileName */
        /* Returns: true if there or false if is */
        /**************************************************************/
        public boolean isThere(JTextArea output) {
                Scanner filething = null;
                try {
                        filething = new Scanner(new File(newDbFileName));

                        if(filething != null) { //makes sure the file you are trying to read exists
                                return true;
                        }

                } catch (FileNotFoundException exc) { //if it doesnt you get this exception
                        output.setText(output.getText() + "file isnt there");
                        return false;
                }
                return false; //a return statement so this can be a boolean method, IDE wants this here to run
        }
}
