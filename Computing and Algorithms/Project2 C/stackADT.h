// Parker Buszka
// CS 231 Project 2

#include <stdio.h>
#include <stdlib.h>
#include "MyCharType.h"
// #include "MyDoubleType.h"

// creates node for stack and the proper pointers
typedef struct node {
    struct node *next;
    dataType element;
} nodeType;

// creates top node for stack and the proper top pointer
typedef struct stackHeader {
    struct node *top;
} stackType;

// defines function prototypes
stackType *push(dataType, stackType *);
dataType pop(stackType *);
int isEmpty(stackType *);
dataType peek(stackType *);
stackType *create();
stackType *destroy(stackType *);
void printStack(stackType *);

