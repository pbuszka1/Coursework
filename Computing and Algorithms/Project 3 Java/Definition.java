/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 3 */
/* Definition class: contains memebrs corresponding to the definition, and methods to deal with them, print, get term */
/**************************************************************/

public class Definition {
   String term; //the term
   int number; //in the middle of the line
   String theDefinition; //the definiton of the term
   
     /**************************************************************/
     /* Method: Definition() */
     /* Purpose: set up the Definition constructor */
     /* Parameters: num and definition */
     /* Returns: nothing */
     /**************************************************************/
     public Definition(int num, String definition) {
          number = num;
          theDefinition = definition;
     } 

     /**************************************************************/
     /* Method: getNumber() */
     /* Purpose: to get the number */
     /* Parameters: none*/
     /* int target: number */
     /* Returns: number */
     /**************************************************************/
     public int getNumber() {
          return number;
     }

   /**************************************************************/
   /* Method: getTerm() */
   /* Purpose: return the term */
   /* Parameters: none*/
   /* String target: term */
   /* Returns: term */
   /**************************************************************/
   public String getTerm() {
        return term;
   }  

   /**************************************************************/
   /* Method: getDefinition() */
   /* Purpose: return the definition */
   /* Parameters: none*/
   /* String target: theDefinition */
   /* Returns: theDefinition */
   /**************************************************************/
   public String getDefinition() {
        return theDefinition;
   }
}