/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* ChadSearchMoreHelp class: outputs when the term doesnt match or it doesnt exist */
/**************************************************************/
import javax.swing.*;
import java.awt.*;

public class ChadSearchMoreHelp extends JFrame {
    Prog5 myP = new Prog5(); //creates new instance of Prog5
    Database myDb = myP.getDatabase(); //gets same database the rest of the program uses

    /**************************************************************/
    /* Method: ChadSearchMoreHelp() */
    /* Purpose: sets up the window for the ChadSearchHelp class */
    /* Parameters: int input */
    /* Returns: nothing */
    /**************************************************************/
    public ChadSearchMoreHelp(int input) {
        super("ChadSearchMoreHelp");
        setSize(250, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //closes the window
        setVisible(true); //makes the window visible
       
        Container Screen1; //sets up the window
        
        Screen1 = getContentPane();
        Screen1.setLayout(new FlowLayout()); //sets layout
       
        JLabel inpLabel; 
        if(input == 1) //this is just to display the correct output
            inpLabel = new JLabel("Sorry number and/or term doesnt exist inside the list");
        else
            inpLabel = new JLabel("Sorry no term match ");

        //this adds these to the container, which is like an object holding all of this
        Screen1.add(inpLabel); //adds inpLabel to the screen
    }
}
