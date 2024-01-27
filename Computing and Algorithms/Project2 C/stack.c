// Parker Buszka
// CS 231 Project 2

#include "stackADT.h"

// adds to the top of the stack by creating a node
// returns the updated stack
stackType *push(dataType data, stackType *stack) {
    // creates the lastNode and nodePointer
    nodeType *lastNode;
    nodeType *nodePointer;

    if (stack == NULL) {
        printf("Cant put null into a list\n");
        exit(1);
    } else {
        // gives nodePointer memory
        nodePointer = (nodeType *) malloc(sizeof(nodeType));
        if (nodePointer != NULL) {
            // gives the nodePointer the data
            nodePointer -> element = data;
            nodePointer -> next = NULL;
            // if the stack is empty it sets the top
            // of the stack to the nodePointer or else'
            // it adjust the positioning for the new
            // top of stack
            if (isEmpty(stack))
                stack -> top = nodePointer;
            else {
                lastNode = stack -> top;
                nodePointer -> next = lastNode;
                stack -> top = nodePointer;
            }
        }
    }
    return stack;
}

// returns the top of stack and removes the data from it, returning 
// that data
dataType pop(stackType *stack) {
    // creates a nodePointer, the topNode and theNodeData to hold
    // the data
    nodeType *nodePointer;
    nodeType *topNode;
    dataType theNodeData;
    if (stack == NULL) {
        printf("Error\n");
        exit(1);
    } else {
        // gets the data of the node we are removing
        // and adjusts the stack to account for a new top
        // one below the previous
        topNode = stack -> top;
        theNodeData = topNode -> element;
        nodePointer = topNode -> next;
        stack -> top = nodePointer;
    }
    return theNodeData;
}

// returns 1 if stack is empty and 0 if it is not
int isEmpty(stackType *stack) {
    if (stack == NULL) 
        return 1;
    else
        return 0;
}

// returns the top data value of the stack
dataType peek(stackType *stack) {
    nodeType *nodePointer;
    nodeType *topNode;
    dataType theNodeData;
    if (stack == NULL) {
        printf("Error\n");
        exit(1);
    } else {
        topNode = stack -> top;
        theNodeData = topNode -> element;
    }
    return theNodeData;
}

// creates the stack in memory
stackType *create() {
    // creates the stack pointer
    stackType *thisStack;
    // allocates memory
    thisStack = (stackType *) malloc(sizeof(stackType));

    // set the top of the stack as null, because the stack
    // is empty
    if (!(thisStack == NULL))
        thisStack -> top = NULL;

    return thisStack;
}

// destroyes the stack by freeing memory
// allocated for it
stackType *destroy(stackType *stack){
    nodeType *currentNode;
    nodeType *nextNode;

    if (stack == NULL) {
        printf("Error\n");
    } else {
        if (!isEmpty(stack)) {
            currentNode = stack -> top;
            nextNode = currentNode -> next;
            while(nextNode != NULL) {
                free((void*) currentNode);
                currentNode = nextNode;
                nextNode = currentNode -> next;
            }
            free((void*) currentNode);
        }
    }
    free((void*) stack);
    stack = NULL;
    return stack;
}