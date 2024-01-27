# Parker Buszka
# CS 481 Assignment 2

import numpy as np
import copy

# Creates node for tree class
class Node:
    def __init__(self, data):
        self.data = data
        self.children = []
        self.parent = None

# creates tree from nodes
class Tree:
    def add_child(self, parent, child):
        parent.children.append(child)
        child.parent = parent
    def __init__(self, node):
        self.root = node

# check if the board is done
def is_done(board):
    count = 0
    for i in range(len(board)):
        for j in range(len(board[i])):
            if board[i][j] == 1:
                count+=1
    if count == 1:
        return True
    else:
        return False

# everything below checks if a move is possible, and if it is it can do the move
def diagonal_up_left(board, row, column):
    temp_board = board
    try:
        if temp_board[row][column] == 1 and temp_board[row-1][column-1] == 1 and temp_board[row-2][column-2] == 0:
            temp_board[row-2][column-2] = 1
            temp_board[row-1][column-1] = 0
            temp_board[row][column] = 0
            return 1
        else:
            return -1
    except:
        return -1

def diagonal_up_right(board, row, column):
    temp_board = board
    try:
        if temp_board[row][column] == 1 and temp_board[row-1][column+1] == 1 and temp_board[row-2][column+2] == 0:
            temp_board[row-2][column+2] = 1
            temp_board[row-1][column+1] = 0
            temp_board[row][column] = 0
            return 1
        else:
            return -1
    except:
        return -1

def diagonal_down_left(board, row, column):
    temp_board = board
    try:
        if temp_board[row][column] == 1 and temp_board[row+1][column-1] == 1 and temp_board[row+2][column-2] == 0:
            temp_board[row+2][column-2] = 1
            temp_board[row+2][column+2] = 0
            temp_board[row][column] = 0
            return 1
        else:
            return -1
    except:
        return -1

def diagonal_down_right(board, row, column):
    temp_board = board
    try: 
        if temp_board[row][column] == 1 and temp_board[row+1][column+1] == 1 and temp_board[row+2][column+2] == 0:
            temp_board[row+2][column+2] = 1
            temp_board[row+1][column+1] = 0
            temp_board[row][column] = 0
            return 1
        else:
            return -1
    except:
        return -1

def horizontal_right(board, row, column):
    temp_board = board
    try:
        if temp_board[row][column] == 1 and temp_board[row][column+2] == 1 and temp_board[row][column+4] == 0:
            temp_board[row][column+4] = 1
            temp_board[row][column+2] = 0
            temp_board[row][column] = 0
            return 1
        else:
            return -1
    except:
        return -1

def horizontal_left(board, row, column):
    temp_board = board
    try:
        if temp_board[row][column] == 1 and temp_board[row][column-2] == 1 and temp_board[row][column-4] == 0:
            temp_board[row][column-4] = 1
            temp_board[row][column-2] = 0
            temp_board[row][column] = 0
            return 1
        else:
            return -1
    except:
        return -1

# creates an array of the possible moves at each stage of the game
def possible_move(board):
    moves = []
    for i in range(len(board)):
        for j in range(len(board[i])):
            temp_board = copy.deepcopy(board)
            if diagonal_up_left(temp_board, i, j) != -1:
                moves.append(temp_board)
            elif diagonal_up_right(temp_board, i, j) != -1:
                moves.append(temp_board)
            elif diagonal_down_right(temp_board, i, j) != -1:
                moves.append(temp_board)
            elif diagonal_down_left(temp_board, i, j) != -1:
                moves.append(temp_board)
            elif horizontal_left(temp_board, i, j) != -1:
                moves.append(temp_board)
            elif horizontal_right(temp_board, i, j) != -1:
                moves.append(temp_board)
    return moves

# adds nodes to the tree
def add_node(new_game_board, parent, tree):
    node = Node(new_game_board)
    tree = Tree.add_child(tree, parent, node)
    return node

# prints out the board in a nice looking way
def pretty_printer(board):
    not_bad_board = []
    for i in range(len(board)):
        for j in range(len(board[i])):
            if board[i][j] != -1:
                not_bad_board.append(board[i][j])
    print("     %s" % not_bad_board[0])
    print("    %s %s" % (not_bad_board[1],not_bad_board[2]))
    print("   %s %s %s" % (not_bad_board[3],not_bad_board[4],not_bad_board[5]))
    print("  %s %s %s %s" % (not_bad_board[6],not_bad_board[7],not_bad_board[8],not_bad_board[9]))
    print(" %s %s %s %s %s" % (not_bad_board[10],not_bad_board[11],not_bad_board[12],not_bad_board[13],not_bad_board[14]))

# solves the marble solitaire game
def depth_first_search(board, node, tree):
    game_boards = possible_move(copy.deepcopy(board))
    
    for i in range (len(game_boards)):
        child = add_node(game_boards[i], node, tree)
        check = is_done(game_boards[i])
        pretty_printer(game_boards[i])
        print("\n")
        if check == True:
            print("You got a Dub and beat the game!")
            exit()
        else:
            depth_first_search(game_boards[i], child, tree)

# is the main method
if __name__ == "__main__":
    board_test = np.array([[-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1],
                      [-1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1],
                      [-1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1],
                      [-1, -1, -1, 1, -1, 0, -1, 1, -1, -1, -1],
                      [-1, -1, 1, -1, 1, -1, 1, -1, 1, -1, -1],
                      [-1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1],
                      [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1]]
                     )   

    root = Node(board_test)
    tree = Tree(root)
    
    pretty_printer(board_test)
    depth_first_search(board_test, root, tree)