/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 4 */
/* Term class: goes into tree, holds definition list */
/**************************************************************/

import java.io.FileWriter;
import java.io.IOException;

/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 3 */
/* Term class: creates term object which has a list of definitions in it */
/**************************************************************/

public class Term {
    String term; //the term
    LinkedList theDefs = new LinkedList(); //definition linked list

    /**************************************************************/
    /* Method: Term() */
    /* Purpose: sets term object*/
    /* Parameters: term */
    /* object target: term */
    /* Returns: nothing */
    /**************************************************************/
    public Term(String term) {
        this.term = term;
    }

    /**************************************************************/
    /* Method: addDefinition() */
    /* Purpose: adds definitions to the definitions list */
    /* Parameters: theDefinition */
    /* object target: theDefs */
    /* Returns: nothing */
    /**************************************************************/
    public void addDefinition(int pos, Definition theDefinition) {
        theDefs.add(pos, theDefinition);
    }

    /**************************************************************/
    /* Method: print() */
    /* Purpose: prints out the terms with there corresponding definitions below */
    /* Parameters: */
    /* Returns: output of terms and definitions */
    /**************************************************************/
    public void print() {
        System.out.print(term + ", ");
        for(int pos = 0; pos < theDefs.size(); pos++) {
            System.out.print("\n" + theDefs.get(pos).number + ", " + theDefs.get(pos).theDefinition);
        }
        System.out.println();
    }

    /**************************************************************/
    /* Method: writeToFile() */
    /* Purpose: write to a inputedfile */
    /* Parameters: fileThing */
    /* String target:  fileThing */
    /* Returns: nothing */
    /**************************************************************/
    public void writeToFile(FileWriter fileThing) {
        try {
            String stuff = "";
            for(int pos = 0; pos < theDefs.size(); pos++) {
                String theNum = String.valueOf(theDefs.get(pos).number);
                stuff = (term + "/" + theNum + "/" + theDefs.get(pos).theDefinition);

                fileThing.write("\n" + stuff);
            }
        } catch (IOException exc) {
            System.out.println("error");
        }
       
    }

    /**************************************************************/
    /* Method: printTerm() */
    /* Purpose: prints term */
    /* Parameters: none */
    /* Returns: nothing */
    /**************************************************************/
    public void printTerm() {
        System.out.println(term);
    }

    /**************************************************************/
    /* Method: getTerm() */
    /* Purpose: gets term */
    /* Parameters: none */
    /* Returns: term */
    /**************************************************************/
    public String getTerm() {
        return term;
    }

    /**************************************************************/
    /* Method: addingDefs() */
    /* Purpose: add definitions to the definition list */
    /* Parameters: newDefinition */
    /* object target: newDefinition object */
    /* Returns: nothing */
    /**************************************************************/
    public void addingDefs(Definition newDefinition) {
        int pos; //the position
        int num = newDefinition.getNumber(); //the number
        for(pos = 0; pos < theDefs.size(); pos++) {
            Definition current = theDefs.get(pos); //the current definition, i dont know if this cast is right
            int currentNum = current.getNumber(); //the current number

            if(num < currentNum) {
                theDefs.add(pos, newDefinition);
                return;
            }
        }
        theDefs.add(pos, newDefinition);
    }

    /**************************************************************/
    /* Method: getNum() */
    /* Purpose: get number from theDefs list and allows for the TermsList to access it*/
    /* Parameters: input */
    /* int target: number of the definition list */
    /* Returns: input, or large number to see if it doesnt retun an input */
    /**************************************************************/
    public int getNum(int input) { //is not returning what it should
        for(int pos = 0; pos < theDefs.size(); pos++) {
            if(input == theDefs.get(pos).number) { //may need to use get().number
                return input;
            }
        }
        return -1000; //comically large to tell if it doesnt return an input
    }

    /**************************************************************/
    /* Method: removeDef() */
    /* Purpose: remove a definition from the definition list*/
    /* Parameters: input */
    /* int target: number of the definition list */
    /* Returns: nothing */
    /**************************************************************/
    public void removeDef(int input) {
        for(int pos = 0; pos < theDefs.size(); pos++) {
            if(input == theDefs.get(pos).number) {
                theDefs.remove(pos);
            }
        }
    }

    /**************************************************************/
    /* Method: printOneDef() */
    /* Purpose: prints out term with a number and a definition */
    /* Parameters: placeHolder */
    /* Returns: nothing */
    /**************************************************************/
    public void printOneDef(int placeHolder) {
        int pos = -1; //to find where on the defs list is the number and definition
        
        for(int lie = 0; lie < theDefs.size(); lie++) {
            if(placeHolder == theDefs.get(lie).number) {
                pos = lie;
            }
        }
        System.out.print(term + ", ");
        System.out.print("\n" + theDefs.get(pos).number + ", " + theDefs.get(pos).theDefinition);
        System.out.println();
    }

    /**************************************************************/
    /* Method: theDefListSize() */
    /* Purpose: return the size of the definition list */
    /* Parameters: none */
    /* Returns: num */
    /**************************************************************/
    public int theDefListSize() {
        int num = -1;
        if(theDefs.size() == 0) {
            num = 0;
        }
        return num; 
    }

    /**************************************************************/
    /* Method: searchForDef() */
    /* Purpose: search for a definition */
    /* Parameters: num */
    /* Returns: boolean */
    /**************************************************************/
    public boolean searchForDef(int num) {
        Node current = theDefs.head;

        while (current != null) {
            if (current.getDatum().getNumber() == num) 
                return true;

            current = current.getNext();
        }
        return false;
    }
}
