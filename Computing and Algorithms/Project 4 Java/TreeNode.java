/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 4 */
/* TreeNode class: manipulates the definition objects, search, print, etc */
/**************************************************************/

public class TreeNode {
    Term datum; //holds the term and definition object
    TreeNode left; //left node
    TreeNode right; //right node

    /**************************************************************/
    /* Method: TreeNode */
    /* Purpose: Search all the records in the current database */
    /* Parameters: */
    /* String target: nothing */
    /* Returns: nothing */
    /**************************************************************/
    public TreeNode() {
        datum = null;
        left = null;
        right = null;
    }

    /**************************************************************/
    /* Method: getDatum */
    /* Purpose: returns datum */
    /* Parameters: */
    /* String target: datum */
    /* Returns: datum */
    /**************************************************************/
    public Term getDatum() {
        return datum;
    }

    /**************************************************************/
    /* Method: getRight */
    /* Purpose: returns right */
    /* Parameters: */
    /* String target: right */
    /* Returns: right */
    /**************************************************************/
    public TreeNode getRight() {
        return right;
    }
  
    /**************************************************************/
    /* Method: getLeft */
    /* Purpose: returns left */
    /* Parameters: */
    /* String target: left */
    /* Returns: left */
    /**************************************************************/
    public TreeNode getLeft() {
        return left;
    }

    /**************************************************************/
    /* Method: setDatum */
    /* Purpose: sets datum */
    /* Parameters: datum*/
    /* String target: datum */
    /* Returns: nothing */
    /**************************************************************/
    public void setDatum(Term datum) {
        this.datum = datum;
    }

    /**************************************************************/
    /* Method: setLeft */
    /* Purpose: sets left treenode */
    /* Parameters: */
    /* String target: left */
    /* Returns: nothing */
    /**************************************************************/
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**************************************************************/
    /* Method: setRight */
    /* Purpose: set right treenode */
    /* Parameters: */
    /* String target: right */
    /* Returns: nothing */
    /**************************************************************/
    public void setRight(TreeNode right) {
        this.right = right;
    }
}
